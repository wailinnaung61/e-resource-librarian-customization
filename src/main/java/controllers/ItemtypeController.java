package controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import beans.UserBean;
import dao.UserDao;

@Controller
public class ItemtypeController {

	@Autowired
	UserDao dao;

	// For Add Itemtype
	@RequestMapping("/Additemtype")
	public String AddRole(Model m, HttpServletRequest req, HttpServletResponse res) {
		m.addAttribute("command", new UserBean());
		return "Additemtype";
	}

	// For Clicking Create Itemtype Button
	@RequestMapping(value = "/saveitemtype", method = RequestMethod.POST)
	public String saveitemtype(@ModelAttribute("user") UserBean user, RedirectAttributes redir) {

		// System.out.println(user.getItemtypes()+user.getItemcode());
		int count = dao.itemtypebyitemtype(user.getItemtypes());

		if (count > 0) {
			redir.addFlashAttribute("alert", "Itemtype name already exist.Please Use another Itemtype Name!");
			return "redirect:/Additemtype";
		}
		dao.saveitemtype(user);
		return "redirect:/Viewitemtype";
	}

	// For View Itemtype
	@RequestMapping("/Viewitemtype")
	public String viewitemtype(Model m, HttpServletRequest req, HttpServletResponse res) {
		List<UserBean> list = dao.getitemtypes();
		m.addAttribute("list", list);
		return "Viewitemtype";
	}

	// For Clicking deleteItemtype in Viewuser
	@RequestMapping(value = "/deleteitemtype/{itemID}", method = RequestMethod.GET)
	public String deleterole(@PathVariable int itemID) {
		dao.deleteitemtype(itemID);
		return "redirect:/Viewitemtype";
	}

	// For Clicking EditItemtype in View itemtype
	@RequestMapping(value = "/edititemtype/{itemID}")
	public String editrole(@PathVariable int itemID, Model m, HttpServletRequest req, HttpServletResponse re) {
		UserBean user = dao.getitemtypeById(itemID);
		m.addAttribute("command", user);
		return "edititemtype";
	}

	// For EditSave Itemtype Button
	@RequestMapping(value = "/editsaveitemtype", method = RequestMethod.POST)
	public String editsaverole(@ModelAttribute("user") UserBean user) {
		dao.updateitemtype(user);
		return "redirect:/Viewitemtype";
	}

}
