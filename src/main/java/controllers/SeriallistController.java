package controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import beans.Data;
import dao.UserDao;

@Controller
public class SeriallistController {

	@Autowired
	UserDao dao;

	@RequestMapping("/seriallist")
	public String bibliolist(Model m) {

		List<Data> forshow = dao.seriallistview();
		m.addAttribute("list", forshow);
		return "seriallist";
	}

	@RequestMapping("/serialviewdetail/{biblionumber}")
	public String serialviewdetail(@PathVariable int biblionumber, Model m) {
		List<Data> forshow = dao.seriallistdetail(biblionumber);
		m.addAttribute("list", forshow);
		return "serialviewdetail";
	}
}
