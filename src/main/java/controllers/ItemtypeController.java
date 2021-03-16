package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/additemtypes")
	public String AddRole(Model m, HttpServletRequest req, HttpServletResponse res) {

		m.addAttribute("itemtypes", new ItemTypes());
		return "additemtype";
	}

	@PostMapping(value = "/saveItemtype")
	public String saveitemtype(@ModelAttribute("itemtypes") ItemTypes itemtypes, RedirectAttributes redir) {

		int count = itemService.getItemtypesByItemName(itemtypes.getName());
		if (count > 0) {
			redir.addFlashAttribute("alert", "Itemtype name already exist.Please Use another Itemtype Name!");
			return "redirect:/additemtypes";
		}
		
		itemService.saveItemType(itemtypes);
		return "redirect:/additemtypes";
	}
//
//	// For View Itemtype
//	@RequestMapping("/Viewitemtype")
//	public String viewitemtype(Model m, HttpServletRequest req, HttpServletResponse res) {
//		List<UserBean> list = dao.getitemtypes();
//		m.addAttribute("list", list);
//		return "Viewitemtype";
//	}
//
//	// For Clicking deleteItemtype in Viewuser
//	@RequestMapping(value = "/deleteitemtype/{itemID}", method = RequestMethod.GET)
//	public String deleterole(@PathVariable int itemID) {
//		dao.deleteitemtype(itemID);
//		return "redirect:/Viewitemtype";
//	}
//
//	// For Clicking EditItemtype in View itemtype
//	@RequestMapping(value = "/edititemtype/{itemID}")
//	public String editrole(@PathVariable int itemID, Model m, HttpServletRequest req, HttpServletResponse re) {
//		UserBean user = dao.getitemtypeById(itemID);
//		m.addAttribute("command", user);
//		return "edititemtype";
//	}
//
//	// For EditSave Itemtype Button
//	@RequestMapping(value = "/editsaveitemtype", method = RequestMethod.POST)
//	public String editsaverole(@ModelAttribute("user") UserBean user) {
//		dao.updateitemtype(user);
//		return "redirect:/Viewitemtype";
//	}

}
