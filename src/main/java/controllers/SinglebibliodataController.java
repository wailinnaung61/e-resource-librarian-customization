
package controllers;

import java.util.ArrayList;
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

import beans.BiblioFrameworkType;
import beans.BookFramework;
import beans.Collection;
import beans.Data;
import beans.ItemTypes;
import config.Frameworks;
import dao.SingleBiblioDataDao;
import services.ItemService;
import services.SingleBiblioDataService;

@Controller
public class SinglebibliodataController {

	@Autowired
	private Frameworks frameWorks;

	@Autowired
	SingleBiblioDataDao singleBiblioDataDao;

	@Autowired
	ItemService itemService;

	@Autowired
	private SingleBiblioDataService singleBiblioDataService;

	List<Data> itemDatas = new ArrayList<>();

	@GetMapping("/addsinglebibliodata")
	public String addSingleBiblioData(Model m, @ModelAttribute("searchFramework") BiblioFrameworkType searchFramework,
			HttpServletRequest req) {

		if (searchFramework.getSearchFramework() != null) {

			if (searchFramework.getSearchFramework().equalsIgnoreCase("book")) {
				m.addAttribute("savedata", new BookFramework());
				m.addAttribute("data", frameWorks.bookFrameworks());
			}
		}
		m.addAttribute("searchFramework", searchFramework == null ? new BiblioFrameworkType() : searchFramework);
		return "addsinglebibliodata";
	}

	@PostMapping("/savebibliodata")
	public String saveSingleBiblioData(Model m, @ModelAttribute("savedata") BookFramework bookFramework,
			HttpServletRequest req, RedirectAttributes redir) {
		// save bibliodata declare
		List<BookFramework> listFramework = new ArrayList<BookFramework>();

		// collection selectbox
		List<Collection> collection = itemService.getCollections();

		// itemtype selectbox
		List<ItemTypes> itemtype = itemService.getItemTypes();

		if (bookFramework != null) {
			// save bibliodata
			listFramework.add(bookFramework);
			singleBiblioDataService.saveBiblioData(listFramework);

			itemDatas = singleBiblioDataService.getItemsbyBiblioData(singleBiblioDataDao.biblioLatestinfo());

			m.addAttribute("itemdata", itemDatas);
			m.addAttribute("bibliodata", singleBiblioDataDao.biblioLatestinfo());
			m.addAttribute("itemtypes", itemtype);
			m.addAttribute("collections", collection);
			m.addAttribute("data", new Data());
		}
		return "additem";
	}

	@PostMapping("/saveitems")
	public String saveItems(Model m, HttpServletRequest req, HttpServletResponse res, @ModelAttribute("data") Data data,
			RedirectAttributes redir) {
		if (data != null) {
			singleBiblioDataService.addItem(data);
		}

		List<Collection> collection = itemService.getCollections();
		List<ItemTypes> itemtype = itemService.getItemTypes();

		itemDatas = singleBiblioDataService.getItemsbyBiblioData(data.getBiblionumber());

		m.addAttribute("itemdata", itemDatas);
		m.addAttribute("itemtypes", itemtype);
		m.addAttribute("collections", collection);
		m.addAttribute("bibliodata", data.biblionumber);
		return "additem";
	}

	@GetMapping("/additem")
	public String addItemforBiblioSingleData(Model m, HttpServletRequest req, HttpServletResponse res) {
		List<Collection> collection = itemService.getCollections();
		List<ItemTypes> itemtype = itemService.getItemTypes();
		m.addAttribute("itemtypes", itemtype);
		m.addAttribute("collections", collection);
		m.addAttribute("data", new Data());
		return "additem";
	}

	@PostMapping("/additemsearch")
	public String addItemSearch(Model m, HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("data") Data data, RedirectAttributes redir) {

		List<Collection> collection = itemService.getCollections();
		List<ItemTypes> itemtype = itemService.getItemTypes();

		itemDatas = singleBiblioDataService.getItemsbyBiblioData(data.getBiblionumber());

		boolean checkitemDatas = itemDatas.isEmpty();

		if (checkitemDatas == false) {
			m.addAttribute("itemdata", itemDatas);
			m.addAttribute("itemtypes", itemtype);
			m.addAttribute("collections", collection);
			return "additem";
		} else {
			redir.addFlashAttribute("bibliodata", null);
			redir.addFlashAttribute("alert", "Record can't found");
			return "redirect:/additem";
		}
	}

	@GetMapping(value = "/deleteitem")
	public String deleterole(@RequestParam("itemId") int itemID, RedirectAttributes redir) throws Exception {
		singleBiblioDataService.deleteItem(itemID);
		return "redirect:/additem";
	}

	@GetMapping(value = "/importitem")
	public String importItem(Model m, HttpServletRequest req, HttpServletResponse res) {
		m.addAttribute("data", new Data());
		return "importitem";
	}

	@PostMapping(value = "/importitemsearch")
	public String importItemSearch(Model m, HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("data") Data data, RedirectAttributes redir) {

		itemDatas = singleBiblioDataService.getItemsbyBiblioData(data.getBiblionumber());

		boolean checkitemDatas = itemDatas.isEmpty();

		if (checkitemDatas == false) {
			m.addAttribute("itemdata", itemDatas);
			m.addAttribute("bibliodata",data.getBiblionumber());
			return "importitem";
		} else {
			redir.addFlashAttribute("bibliodata", null);
			redir.addFlashAttribute("alert", "Record can't found");
			return "redirect:/importitem";
		}
	}

	@GetMapping(value = "/deleteimportitem")
	public String deleteImportItem(@RequestParam("itemId") int itemID, RedirectAttributes redir) throws Exception {
		singleBiblioDataService.deleteItem(itemID);
		return "redirect:/importitem";
	}
	
}
