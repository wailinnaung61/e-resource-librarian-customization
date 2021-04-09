package controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import beans.UserBean;
import config.Utility;
import net.bytebuddy.utility.RandomString;
import services.ForgotPasswordService;

@Controller
@RequestMapping("/reset")
public class ForgotpasswordController {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@GetMapping("/forgotpassword")
	public String showForgotPasswordForm() {
		return "forgotpassword";
	}

	@PostMapping("/forgotpassword")
	public String processForgotPassword(Model model, HttpServletRequest req, HttpServletResponse res) throws Exception {
		String email = req.getParameter("email");
		String token = RandomString.make(30);

		try {
			forgotPasswordService.updateResetPasswordToken(token, email);
			String resetPasswordLink = Utility.getSiteURL(req) + "/reset/resetpassword?token=" + token;
			sendEmail(email, resetPasswordLink);
			model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

		} catch (Error ex) {
		} catch (UnsupportedEncodingException | MessagingException e) {
		}

		return "forgotpassword";
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("e-resources@admin.com", "E-resources");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to reset your password";

		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	@GetMapping("/resetpassword")
	public String showResetPasswordForm(@RequestParam("token") String token, Model model, RedirectAttributes redir) {
		UserBean users = forgotPasswordService.getByResetPasswordToken(token);
		model.addAttribute("token", token);

		if (users == null) {
			model.addAttribute("alert", "Invalid Token");
			return "forgotpassword";
		} else {
			return "resetpassword";
		}
	}

	@PostMapping("/resetpassword")
	public String processResetPassword(HttpServletRequest request, Model model, RedirectAttributes redir) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		UserBean users = forgotPasswordService.getByResetPasswordToken(token);

		if (users == null) {
			model.addAttribute("alert", "Invalid Token");
			return "resetpassword";
		} else {
			forgotPasswordService.updatePassword(users,passwordEncoder().encode( password));

			model.addAttribute("message", "You have successfully changed your password.");
		}

		return "resetpassword";
	}

}
