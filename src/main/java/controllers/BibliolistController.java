package controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import beans.BiblioSearchType;
import beans.ItemTypes;
import beans.SpecialRequest;
import beans.bibliosingledata;
import dao.UserDao;
import services.ItemService;
import services.SyncService;

@Controller
public class BibliolistController {

	private Logger logger = LogManager.getLogger(BibliolistController.class);

	@Autowired
	UserDao dao;

	@Autowired
	SyncService syncService;

	@Autowired
	ItemService itemService;

	@GetMapping(value = "/itemtypelist")
	public String itemtypelist(Model model, HttpServletRequest req) {
		try {
			boolean synced = false;
			Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(req);
			if (flashMap != null) {
				synced = (boolean) flashMap.get("synced");
			}
			model.addAttribute("synced", synced);
			List<ItemTypes> itemlist = itemService.getItemTypesList();
			model.addAttribute("itemlist", itemlist);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return "itemtypelist";
	}

	@GetMapping("/biblioitemdetail")
	public String bibliodetail(Model m, @ModelAttribute("searchBean") BiblioSearchType searchBean,
			HttpServletRequest req) {
		try {
			bibliosingledata data = null;
			if (searchBean.getId() != 0) {
				data = itemService.getBiblioItem(searchBean);
			}
			m.addAttribute("data", data);
			m.addAttribute("searchBean", searchBean == null ? new BiblioSearchType() : searchBean);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return "biblioitemdetail";
	}

	@PostMapping("/biblioitemdetail")
	public RedirectView updatebiblio(@ModelAttribute("searchBean") BiblioSearchType searchBean,
			@ModelAttribute("data") bibliosingledata biblio, RedirectAttributes redirectAddr, HttpServletRequest req) {
		try {
			itemService.updateBiblioItem(biblio);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		String url = String.format("/biblioitemdetail?searchType=%s&id=%d", searchBean.getSearchType(),
				searchBean.getId());
		return new RedirectView(url, true);
	}

	@RequestMapping("/biblioitemdetail/delete/resource/{resourceId}")
	public ResponseEntity<String> deleteResourceById(@PathVariable("resourceId") int id, HttpServletRequest req) {
		try {
			itemService.deleteResourceById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping("/bibliolist")
	public String bibliolist(Model m, HttpServletRequest req) {
		try {
			Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(req);
			if (flashMap != null) {
				int itemNumber = (int) flashMap.get("itemnumber");
				m.addAttribute("data", itemService.getSyncedBiblioList(itemNumber));
			}

			int count = syncService.GetNewBiblioItemCount();
			m.addAttribute("bcount", count);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return "bibliolist";
	}

	@GetMapping("/patronlist")
	public String patronlist(Model m, HttpServletRequest req) {
		try {
			Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(req);
			String last_sync = null;
			if (flashMap != null) {
				last_sync = (String) flashMap.get("last_sync");
				m.addAttribute("synced", true);
			}

			m.addAttribute("data", itemService.getPatronList(last_sync));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return "patronlist";
	}

	@GetMapping("/specialrequests")
	public String specialRequests(Model m, HttpServletRequest req) {
		try {
			List<SpecialRequest> itemList = itemService.getSpecialRequests();
			m.addAttribute("data", itemList);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return "specialrequests";
	}

	@GetMapping(value = "/specialrequest/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> specialRequestCount() {
		int count = itemService.getPendingSpecialRequestCount();
		return ResponseEntity.status(HttpStatus.OK).body(count);
	}

	@PostMapping("/update/specialrequest")
	public ResponseEntity<String> updateSpecialRequest(@RequestParam(name = "id") int id,
			@RequestParam(name = "status") int status, HttpServletRequest req) {
		try {
			itemService.updateSpecialRequestById(id, status);
			return ResponseEntity.accepted().body("ok");
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@GetMapping("/regenerate/resources")
	public ResponseEntity<String> regenerateResources(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "count", defaultValue = "10") int count) {
		try {
			logger.info("Regenerating resources");
			itemService.checkAndRegenerateResources(offset, count);
		} catch (Exception ex) {
			logger.error("Failed to regenerate resources : {}", ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().body("Regenerated resources");
	}
}