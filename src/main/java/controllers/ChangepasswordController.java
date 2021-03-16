
package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import beans.UserBean;
import dao.UserDao;

@Controller
public class ChangepasswordController {

	@Autowired
	UserDao dao;

	// For ChangePassword Form
	@RequestMapping("/changepassword")
	public String changepassword(Model m) {
		m.addAttribute("command", new UserBean());
		return "changepassword";
	}

	// For ChangePassword Button
	@RequestMapping(value = "/changepasswordprocess", method = RequestMethod.POST)
	public String changepasswordprocess(@ModelAttribute("user") UserBean user, RedirectAttributes redir) {
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = u.getUsername();

		try {
			dao.CheckOldPass(username, user.getPassword());
			dao.updatepassword(username, user.getChangepassword());
			redir.addFlashAttribute("successful", "Your Password changes successful");
		} catch (Exception ex) {
			redir.addFlashAttribute("alert", "*Your old Password Wrong!!*");
		}
		return "redirect:/changepassword";
	}
}
