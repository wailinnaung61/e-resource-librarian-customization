package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import beans.ItemTypes;
import dao.UserDao;
import services.ItemService;

@Controller
public class ItemtypeController {

	@Autowired
	UserDao dao;

	@Autowired
	ItemService itemService;

	@GetMapping("/itemtypes/additemtypes")
	public String AddRole(Model m, HttpServletRequest req, HttpServletResponse res) {

		m.addAttribute("itemtypes", new ItemTypes());
		return "additemtype";
	}

	@PostMapping(value = "/itemtypes/saveItemtype")
	public String saveitemtype(@ModelAttribute("itemtypes") ItemTypes itemtypes, RedirectAttributes redir) {

		int count = itemService.getItemtypesByItemName(itemtypes.getName());
		if (count > 0) {
			redir.addFlashAttribute("alert", "Itemtype name already exist.Please Use another Itemtype Name!");
			return "redirect:/itemtypes/additemtypes";
		}
		
		itemService.saveItemType(itemtypes);
		return "redirect:/itemtypes/viewitemtype";
	}

	
	@GetMapping("/itemtypes/viewitemtype")
	public String viewitemtype(Model m, HttpServletRequest req, HttpServletResponse res) {
		List<ItemTypes> list = itemService.getItemTypes();
		m.addAttribute("list", list);
		return "viewitemtype";
	}
	
	
	@GetMapping(value = "/itemtypes/edititemtype")
	public String editrole(@RequestParam("itemtypeId")int itemID, Model m, HttpServletRequest req, HttpServletResponse re) {
		ItemTypes itemTypes = itemService.getitemtypeById(itemID);
		m.addAttribute("itemtypes", itemTypes);
		return "edititemtype";
	}
	
	@PostMapping(value = "/itemtypes/editsaveitemtype")
	public String editsaverole(@ModelAttribute("itemtypes") ItemTypes itemtypes,RedirectAttributes redir) {
		itemService.updateItemType(itemtypes);
		return "redirect:/itemtypes/viewitemtype";
	}
	
	@GetMapping(value = "/itemtypes/deleteitemtype")
	public String deleterole(@RequestParam("itemtypeId")int itemID,RedirectAttributes redir) throws Exception {
		itemService.deleteItemtype(itemID);
		return "redirect:/itemtypes/viewitemtype";
	}




}
