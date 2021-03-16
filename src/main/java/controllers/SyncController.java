package controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import beans.Data;
import services.SyncService;

@Controller
public class SyncController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	SyncService syncService;
	
	@PostMapping(value = "/sync/biblioitems", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Data>> syncBiblioItemsbyItemList(@RequestParam("syncType") String syncType,
			@RequestParam("item") Integer[] itemList) {
		try {
			List<Data> dataList = syncService.SyncUpdatedItemList(syncType, Arrays.asList(itemList));
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(dataList);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping(value = "/sync/biblioitems/{biblionumber}/{syncType}")
	public RedirectView syncBiblioItembyItem(@PathVariable("biblionumber") int bnumber,
			@PathVariable("syncType") String syncType, RedirectAttributes redir) {
		try {
			syncService.SyncUpdatedItemList(syncType, Collections.singletonList(bnumber));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		String url = String.format("/biblioitemdetail?searchType=%s&id=%d", 1, bnumber);
		return new RedirectView(url, true);
	}

	@GetMapping(value = "/sync/biblioitems")
	public RedirectView syncBiblioItems(RedirectAttributes redir) {
		try {
			int itemNumber = syncService.SyncNewBiblioItems();
			redir.addFlashAttribute("itemnumber", itemNumber);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return new RedirectView("/bibliolist", true);
	}

	@GetMapping(value = "/sync/itemtypes")
	public RedirectView syncItemType(RedirectAttributes redir) {
		try {
			syncService.syncItemTypes();
			redir.addFlashAttribute("synced", true);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return new RedirectView("/itemtypelist", true);
	}
	
	@GetMapping(value = "/sync/patrons")
	public RedirectView syncPatrons(RedirectAttributes redir) {
		try {
			String last_sync = syncService.syncPatrons();
			redir.addAttribute("last_sync", last_sync);
		} catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return new RedirectView("/patronlist", true);
	}
}
