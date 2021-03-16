package controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class RoleController {

	@Autowired
	UserDao dao;

	// For Clicking Create Role Button
	@RequestMapping(value = "/saverole", method = RequestMethod.POST)
	public String saverole(@ModelAttribute("user") UserBean user, RedirectAttributes redir) {

		int count = dao.rolenamebyrolename(user.getRolename());

		if (count > 0) {
			redir.addFlashAttribute("alert", "Role name already exist.Please Use another Rolename!");
			return "redirect:/AddRole";
		} else {
			dao.saverole(user);
			// redir.addFlashAttribute("successful", "Role Created Successful!");
			return "redirect:/ViewRole";
		}
	}

	// For Add Role
	@RequestMapping("AddRole")
	public String AddRole(Model m, HttpServletRequest req, HttpServletResponse res) {
		m.addAttribute("command", new UserBean());
		return "AddRole";
	}

	// For View Role
	@RequestMapping("/ViewRole")
	public String viewrole(Model m, HttpServletRequest req, HttpServletResponse res) {
		List<UserBean> list = dao.getRoles();
		m.addAttribute("list", list);
		return "viewrole";
	}

	// For Clicking Editrole in View Role
	@RequestMapping(value = "/editrole/{roleid}")
	public String editrole(@PathVariable int roleid, Model m, HttpServletRequest req, HttpServletResponse re) {
		UserBean user = dao.getRoleById(roleid);
		m.addAttribute("command", user);
		return "roleedit";
	}

	// For EditSave Role Button
	@RequestMapping(value = "/editsaverole", method = RequestMethod.POST)
	public String editsaverole(@ModelAttribute("user") UserBean user) {
		dao.updaterole(user);
		return "redirect:/ViewRole";
	}

	// For Clicking deleteRole in Viewuser
	@RequestMapping(value = "/deleterole/{roleid}", method = RequestMethod.GET)
	public String deleterole(@PathVariable int roleid) {
		dao.deleterole(roleid);
		return "redirect:/ViewRole";
	}
}
