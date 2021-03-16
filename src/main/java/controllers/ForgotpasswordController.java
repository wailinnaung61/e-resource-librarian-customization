package controllers;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import beans.UserBean;
import dao.UserDao;

@Controller

public class ForgotpasswordController {

	static String emailToRecipient, emailSubject, emailMessage;
	static final String emailFromRecipient = "nlnptreply@nlmnpt.gov.mm";
	static int num;

	@Autowired
	private JavaMailSender mailSenderObj;

	@Autowired
	UserDao dao;

	@RequestMapping("/validatecode")
	public String validte(Model m) {
		m.addAttribute("command", new UserBean());
		return "validatecode";
	}

	@RequestMapping("/forgotpassword")
	public String forgotpassword(@ModelAttribute("user") UserBean user, Model m, HttpServletRequest req,
			HttpServletResponse res) {

		String email = dao.getemailbyusernameforforgotpassword(user.getUsername());
		m.addAttribute("command", new UserBean());
		m.addAttribute("email", email);
		return "forgotpassword";
	}

	@RequestMapping("/getcode")
	public String getcode(@ModelAttribute("user") UserBean user, Model m) {

		emailToRecipient = user.getEmail();
		Random rand = new Random();
		num = rand.nextInt(9000000) + 1000000;

		// Reading Email Form Input Parameters

		emailSubject = "Nay Pyi Taw Library Account Password Reset Code";

		emailMessage = "Please use this code to reset the password for the Nay Pyi Taw Library E-Resource account "
				+ user.getEmail() + ".\n\nHere is your code: " + num + "\n\nThanks,\nNay Pyi Taw Library Team";
		emailToRecipient = user.getEmail();

		// Logging The Email Form Parameters For Debugging Purpose
		System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= "
				+ emailMessage + "\n");

		mailSenderObj.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMsgHelperObj.setTo(emailToRecipient);
				mimeMsgHelperObj.setFrom(emailFromRecipient);
				mimeMsgHelperObj.setText(emailMessage);
				mimeMsgHelperObj.setSubject(emailSubject);

			}
		});
		System.out.println("\nMessage Send Successfully.... !\n");
		m.addAttribute("command", new UserBean());
		m.addAttribute("mail", emailToRecipient);
		return "validatecode";
	}

	@RequestMapping("/resetpassword")
	public String passwordreset(@ModelAttribute("user") UserBean user, Model m, RedirectAttributes redir) {

		System.out.println(user.getCode());
		System.out.println(num);

		if (user.getCode() != num) {
			System.out.println("unequal");
			redir.addFlashAttribute("error", "Wrong Code Number");
			return "redirect:/validatecode";

		} else {
			System.out.println("equal");
			m.addAttribute("command", new UserBean());
			return "resetpassword";
		}
	}

	/*
	 * @RequestMapping("/resetchange") public String
	 * resetchange(@ModelAttribute("user") UserBean user){
	 * System.out.println(user.getChangepassword()); Md5PasswordEncoder encoderMD5 =
	 * new Md5PasswordEncoder(); String newpassword =
	 * encoderMD5.encodePassword(user.getChangepassword(), null);
	 * System.out.println(emailToRecipient);
	 * dao.resetchangepassword(newpassword,emailToRecipient); return "redirect:/"; }
	 */

	/*
	 * @RequestMapping(value="/savefile",method=RequestMethod.POST) public void
	 * upload(@RequestParam CommonsMultipartFile file,HttpSession session){ String
	 * path=session.getServletContext().getRealPath("/"); String
	 * filename=file.getOriginalFilename();
	 * 
	 * System.out.println(path+" "+filename);
	 * 
	 * }
	 */

}
