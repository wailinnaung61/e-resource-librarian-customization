
package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import beans.BiblioFrameworkType;
import beans.BookFramework;
import config.Frameworks;
import services.SingleBiblioDataService;

@Controller
public class SinglebibliodataController {

	@Autowired
	private Frameworks frameWorks;
	
	@Autowired
	private SingleBiblioDataService singleBiblioDataService;

	@GetMapping("/addsinglebibliodata")
	public String addSingleBiblioData(Model m, @ModelAttribute("searchFramework") BiblioFrameworkType searchFramework,
			HttpServletRequest req) {
		
		if (searchFramework.getSearchFramework() != null) {

			if (searchFramework.getSearchFramework().equalsIgnoreCase("book")) {
				m.addAttribute("savedata",new BookFramework());	
				m.addAttribute("data", frameWorks.bookFrameworks());
			}
		}
		m.addAttribute("searchFramework", searchFramework == null ? new BiblioFrameworkType() : searchFramework);
		return "addsinglebibliodata";
	}
	
	@PostMapping("/savebibliodata")
	public String saveSingleBiblioData(Model m,@ModelAttribute("savedata")BookFramework bookFramework,
				HttpServletRequest req,RedirectAttributes redir)
	{		
		List<BookFramework> listFramework=new ArrayList<BookFramework>();
		if(bookFramework != null)
		{
		listFramework.add(bookFramework);
		singleBiblioDataService.saveBiblioData(listFramework);
		
		}
		
		return null;
	}

}
