package controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import beans.ReadingHistory;
import services.ItemService;

@Controller
public class ReportController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/report/readinghistory")
	public String readinghistory(Model m, HttpServletRequest req,
			@RequestParam(name = "from", required = false) String from,
			@RequestParam(name = "to", required = false) String to) {
		List<ReadingHistory> history = itemService.getReadingHistory(from, to);
		m.addAttribute("data", history);
		return "readinghistory";
	}

	@GetMapping("/report/itemspopularity")
	public String itemspopularity(Model m, HttpServletRequest req,
			@RequestParam(name = "from", required = false) String from,
			@RequestParam(name = "to", required = false) String to) {

		List<ReadingHistory> history = itemService.getItemsPopularityReport(from, to);
		Map<String, List<ReadingHistory>> historyByAge = itemService.getItemsPopularityByAgeReport(from, to);
		m.addAttribute("data", history);
		m.addAttribute("databyAge", historyByAge);
		return "itemspopularity";
	}

	@GetMapping("/report/patronaccess")
	public String patronaccessreport(Model m, HttpServletRequest req,
			@RequestParam(name = "from", required = false) String from,
			@RequestParam(name = "to", required = false) String to) {
		m.addAttribute("reportbycategory", itemService.getPatronAccessReportByCategory(from, to));
		m.addAttribute("reportbycity", itemService.getPatronAccessReportByCity(from, to));
		return "patronaccessreport";
	}
}
