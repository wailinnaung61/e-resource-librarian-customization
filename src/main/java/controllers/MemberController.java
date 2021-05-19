package controllers;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import beans.Members;
import services.ItemService;

@Controller
public class MemberController {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	ItemService itemService;

	@GetMapping(value = "/members/addmembers")
	public String addMember(Model m, HttpServletRequest req, HttpServletResponse res) {
		m.addAttribute("member", new Members());
		return "addmember";

	}

	@PostMapping(value = "/members/savemember")
	public String saveMember(@ModelAttribute Members member, Model m, HttpServletRequest req, HttpServletResponse res,RedirectAttributes redir)
			throws ParseException {
		
		itemService.saveMember(member, passwordEncoder().encode(member.getPassword()));
		return "redirect:/members/viewmember";

	}

	@GetMapping(value = "/members/viewmember")
	public String viewMember(Model m, HttpServletRequest req, HttpServletResponse res) {
		List<Members> list = itemService.getMembers();
		m.addAttribute("list", list);
		return "viewmember";
	}

	@GetMapping(value = "/members/deletemember")
	public String deleterole(@RequestParam("memberId") int memberID, RedirectAttributes redir) throws Exception {
		itemService.deleteMember(memberID);
		return "redirect:/members/viewmember";
	}
	
	@GetMapping(value = "/members/updatemember")
	public String editrole(@RequestParam("memberId")int memberID, Model m, HttpServletRequest req, HttpServletResponse re) {
		Members members = itemService.getmemberById(memberID);
		m.addAttribute("members", members);
		return "editmember";
	}
	
	@PostMapping(value = "/members/editsavemember")
	public String editsaverole(@ModelAttribute("members") Members members,RedirectAttributes redir) throws ParseException {
		itemService.updateMember(members);
		return "redirect:/members/viewmember";
	}
	
	
	
	

}
