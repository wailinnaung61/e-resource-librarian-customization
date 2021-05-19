
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
import beans.Collection;
import services.ItemService;

@Controller
public class CollectionController {

	@Autowired
	ItemService itemService;

	@GetMapping("collections/addcollection")
	public String AddCollection(Model m, HttpServletRequest req, HttpServletResponse res) {

		m.addAttribute("collection", new Collection());
		return "addcollection";
	}


	@PostMapping(value = "collections/saveCollection")
	public String saveRole(@ModelAttribute("collection") Collection collection, RedirectAttributes redir) {
		int count = itemService.getCollectionByCollectionName(collection.getName());

		if (count > 0) {
			redir.addFlashAttribute("alert", "Collection name already exist.Please Use another Collection Name!");
			return "redirect:/collections/addcollection";
		}
		itemService.saveCollection(collection);
		    return "redirect:/collections/viewcollection";
	}

	@GetMapping("collections/viewcollection")
	public String viewcollection(Model m, HttpServletRequest req, HttpServletResponse res) {
		List<Collection> list = itemService.getCollections();
		m.addAttribute("list", list);
		return "viewcollection";
	}
	
	
	@GetMapping(value = "/collections/deletecollection")
	public String deleterole(@RequestParam("collectionId")int collectionID,RedirectAttributes redir) throws Exception {
		itemService.deleteCollection(collectionID);
		return "redirect:/collections/viewcollection";
	}
	
	@GetMapping(value = "/collections/editcollection")
	public String editrole(@RequestParam("collectionId")int collectionID, Model m, HttpServletRequest req, HttpServletResponse re) {
		Collection collections = itemService.getCollectionById(collectionID);
		m.addAttribute("collection", collections);
		return "editcollection";
	}
	
	@PostMapping(value = "collections/editsavecollection")
	public String editsaverole(@ModelAttribute("collection") Collection collection,RedirectAttributes redir) {
		itemService.updateCollection(collection);
		return "redirect:/collections/viewcollection";
	}
	
	
	

}
