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
public class UsersController {

	@Autowired
	UserDao dao;

	// For View Role Form
	@RequestMapping("/userform")
	public String showform(Model m, HttpServletRequest req, HttpServletResponse res) {
		List<UserBean> list = dao.getRolename();
		m.addAttribute("command", new UserBean());
		m.addAttribute("list", list);
		return "userform";
	}

	// For Clicking Create User Button
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("user") UserBean user, RedirectAttributes redir) {
		int count = dao.usernamebyusername(user.getUsername());

		if (count > 0) {
			redir.addFlashAttribute("alert", "User name already exist.Please Use another username!");
			return "redirect:/userform";
		} else {
			dao.save(user);
			return "redirect:/viewuser";
		}
	}

	@RequestMapping("/viewuser")
	public String viewuser(Model m, HttpServletRequest req, HttpServletResponse res) {
		List<UserBean> list = dao.getUsers();
		m.addAttribute("list", list);
		return "viewuser";
	}

	// For Clicking Edituser in View User
	@RequestMapping(value = "/edituser/{id}")
	public String edit(@PathVariable int id, Model m, HttpServletRequest req, HttpServletResponse res) {
		List<UserBean> list = dao.getRolename();
		UserBean user = dao.getUserById(id);
		m.addAttribute("command", user);
		m.addAttribute("list", list);
		return "usereditform";
	}

	// Clicking EditSave Button
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("user") UserBean user) {
		dao.update(user);
		return "redirect:/viewuser";
	}

	// For Clicking deleteUser in Viewuser
	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		dao.delete(id);
		return "redirect:/viewuser";
	}

}
