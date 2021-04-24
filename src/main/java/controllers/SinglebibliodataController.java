
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
import dao.ItemDao;
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
	ItemDao itemDao;

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

			m.addAttribute("bibliodata", singleBiblioDataDao.biblioLatestinfo());
			m.addAttribute("itemdata", itemDatas);
			m.addAttribute("itemtypes", itemtype);
			m.addAttribute("collections", collection);
			m.addAttribute("data", new Data());
		}
		return "additem";
	}

	@PostMapping("/saveitems")
	public String saveItems(Model m, HttpServletRequest req, HttpServletResponse res, @ModelAttribute("data") Data data,
			RedirectAttributes redir) {
		if (data != null && data.getBiblionumber() != null) {
			singleBiblioDataService.addItem(data);
			List<Collection> collection = itemService.getCollections();
			List<ItemTypes> itemtype = itemService.getItemTypes();
			itemDatas = singleBiblioDataService.getItemsbyBiblioData(data.getBiblionumber());

			m.addAttribute("itemtypes", itemtype);
			m.addAttribute("collections", collection);
			m.addAttribute("bibliodata", data.biblionumber);
			m.addAttribute("itemdata", itemDatas);
			return "additem";

		} else {
			redir.addFlashAttribute("bibliodata", null);
			redir.addFlashAttribute("alert", "Record can't found");
			return "redirect:/additem";
		}
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

	@GetMapping("/additemsearch")
	public String addItemSearch(Model m, HttpServletRequest req, HttpServletResponse res, RedirectAttributes redir) {

		int checkBiblioNumber = 0;
		int biblioNumber = Integer.parseInt(req.getParameter("biblionumber"));
		checkBiblioNumber = singleBiblioDataService.checkBiblioNumber(biblioNumber);

		if (checkBiblioNumber > 0) {
			List<Collection> collection = itemService.getCollections();
			List<ItemTypes> itemtype = itemService.getItemTypes();
			itemDatas = singleBiblioDataService.getItemsbyBiblioData(biblioNumber);

			m.addAttribute("bibliodata", biblioNumber);
			m.addAttribute("itemdata", itemDatas);
			m.addAttribute("itemtypes", itemtype);
			m.addAttribute("collections", collection);
			m.addAttribute("data", new Data());
			m.addAttribute("message", "Record founded");
			return "additem";
		} else {
			redir.addFlashAttribute("bibliodata", null);
			redir.addFlashAttribute("alert", "Record can't found");
			return "redirect:/additem";
		}
	}

	@GetMapping(value = "/deleteitem")
	public String deleterole(@RequestParam("itemId") int itemID, @RequestParam("biblionumber") int biblioNumber,
			RedirectAttributes redir, HttpServletRequest req, RedirectAttributes redirectAttributes) throws Exception {

		// singleBiblioDataService.deleteItem(itemID);
		List<Integer> deleteItemNumbers = new ArrayList<Integer>();
		deleteItemNumbers.add(itemID);
		itemDao.deleteUnlinkedResources(deleteItemNumbers);

		redirectAttributes.addAttribute("biblionumber", biblioNumber);
		return "redirect:/additemsearch";
	}

	@GetMapping(value = "/importitem")
	public String importItem(Model m, HttpServletRequest req, HttpServletResponse res) {
		return "importitem";
	}

	@GetMapping(value = "/importitemsearch")
	public String importItemSearch2(Model m, HttpServletRequest req, HttpServletResponse res,
			RedirectAttributes redir) {

		int checkBiblioNumber = 0;
		int biblioNumber = Integer.parseInt(req.getParameter("biblionumber"));

		checkBiblioNumber = singleBiblioDataService.checkBiblioNumber(biblioNumber);

		if (checkBiblioNumber > 0) {

			itemDatas = singleBiblioDataService.getItemsbyBiblioData(biblioNumber);

			m.addAttribute("message", "Record founded");
			m.addAttribute("bibliodata", biblioNumber);
			m.addAttribute("itemdata", itemDatas);
			return "importitem";
		} else {
			redir.addFlashAttribute("alert", "Record can't found");
			redir.addFlashAttribute("bibliodata", null);
			return "redirect:/importitem";
		}
	}

	@GetMapping(value = "/deleteimportitem")
	public String deleteImportItem(@RequestParam("itemId") int itemID, @RequestParam("biblionumber") int biblioNumber,
			RedirectAttributes redir, HttpServletRequest req, RedirectAttributes redirectAttributes) throws Exception {
		
		List<Integer> deleteItemNumbers = new ArrayList<Integer>();
		deleteItemNumbers.add(itemID);
		itemDao.deleteUnlinkedResources(deleteItemNumbers);

		redirectAttributes.addAttribute("biblionumber", biblioNumber);
		return "redirect:/importitemsearch";

	}

}
