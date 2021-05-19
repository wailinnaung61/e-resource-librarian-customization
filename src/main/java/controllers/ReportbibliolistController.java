package controllers;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import beans.Item;
import beans.bibliosingledata;
import dao.UserDao;
import services.ItemService;

@Controller
public class ReportbibliolistController {

	@Autowired
	UserDao dao;
	@Autowired
	ItemService itemService;
	private Logger logger = LogManager.getLogger(BibliolistController.class);

	@RequestMapping("/report/reportbibliolist")
	public String bibliolist(Model m, HttpServletRequest req, HttpServletResponse res) {
		List<bibliosingledata> forshow = constructBiblioData(dao.getsinglebibliodata());
		m.addAttribute("list", forshow);
		return "reportbibliolist";
	}
	
	@DeleteMapping("/biblio/delete/{id}")
	public ResponseEntity<String> deletebiblio(@PathVariable("id")int biblionumber) {
		try {
			itemService.deleteBiblio(biblionumber);
			logger.info("Biblio deleted : {}", biblionumber);
		} catch (Exception ex) {
			logger.error("Error Occured {}", ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().body("Regenerated resources");
	}	
	private List<bibliosingledata> constructBiblioData(List<bibliosingledata> data){
		Map<Integer, bibliosingledata> bMap = new HashMap<>();
		for (bibliosingledata b : data) {
			bibliosingledata btmp = bMap.getOrDefault(b.getBiblionumber(), b);
			List<Item> items = btmp.getItems() == null ? new ArrayList<Item>() : btmp.getItems();
			Item i = new Item();
			i.setItemnumber(b.getItemnumber());
			i.setBarcode(b.getBarcode());
			i.setBooksellerid(b.getBooksellerid());
			i.setHomebranch(b.getHomebranch());
			i.setItemCallNumber(b.getItemcallnumber());
			i.setKeyword(b.getKeyword());
			i.setItemtype(b.getItemtype());
			items.add(i);
			btmp.setItems(items);
			bMap.put(b.getBiblionumber(), btmp);
		}
		return new ArrayList<bibliosingledata>(bMap.values());
	}


}
