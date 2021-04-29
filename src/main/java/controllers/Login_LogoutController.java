package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.UserDao;

@Controller
public class Login_LogoutController {
	@Autowired
	UserDao dao;
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/";  
     }  

	@RequestMapping("/detail")
	public String userview(Model m, HttpServletRequest req, HttpServletResponse res) {
		return "detail";
	}

	@RequestMapping("/")
	public String userviewmain(Model m, HttpServletRequest req, HttpServletResponse res) {
		return "Adminview";
	}

	@RequestMapping("/advancepage")
	public String advancepage(Model m, HttpServletRequest req, HttpServletResponse res) {
		return "advancepage";
	}

	@RequestMapping("/searchresult")
	public String searchresult(Model m, HttpServletRequest req, HttpServletResponse res) {
		return "searchresult";
	}
	
	@GetMapping("testpage")
	public String LoginPage()
	{
		return "testpage";
	}
}
