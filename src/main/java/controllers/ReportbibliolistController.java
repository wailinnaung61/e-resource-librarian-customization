package controllers;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import beans.Item;
import beans.bibliosingledata;
import dao.UserDao;
import services.ItemService;

@Controller
public class ReportbibliolistController {

	@Autowired
	UserDao dao;
	@Autowired
	ItemService itemService;
	private Logger logger = LogManager.getLogger(BibliolistController.class);

	@RequestMapping("/reportbibliolist")
	public String bibliolist(Model m, HttpServletRequest req, HttpServletResponse res) {
		List<bibliosingledata> forshow = constructBiblioData(dao.getsinglebibliodata());
		m.addAttribute("list", forshow);
		return "reportbibliolist";
	}
	
	@DeleteMapping("/biblio/delete/{id}")
	public ResponseEntity<String> deletebiblio(@PathVariable("id")int biblionumber) {
		try {
			itemService.deleteBiblio(biblionumber);
			logger.info("Biblio deleted : {}", biblionumber);
		} catch (Exception ex) {
			logger.error("Error Occured {}", ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().body("Regenerated resources");
	}	
	private List<bibliosingledata> constructBiblioData(List<bibliosingledata> data){
		Map<Integer, bibliosingledata> bMap = new HashMap<>();
		for (bibliosingledata b : data) {
			bibliosingledata btmp = bMap.getOrDefault(b.getBiblionumber(), b);
			List<Item> items = btmp.getItems() == null ? new ArrayList<Item>() : btmp.getItems();
			Item i = new Item();
			i.setItemnumber(b.getItemnumber());
			i.setBarcode(b.getBarcode());
			i.setBooksellerid(b.getBooksellerid());
			i.setHomebranch(b.getHomebranch());
			i.setItemCallNumber(b.getItemcallnumber());
			i.setKeyword(b.getKeyword());
			i.setItemtype(b.getItemtype());
			items.add(i);
			btmp.setItems(items);
			bMap.put(b.getBiblionumber(), btmp);
		}
		return new ArrayList<bibliosingledata>(bMap.values());
	}

	/*
	 * @RequestMapping("/reportbibliolist") public String bibliolist1(Model
	 * m,HttpServletRequest req,HttpServletResponse res){
	 * 
	 * 
	 * System.out.println("Tutorial 04"); System.out.println("----------");
	 * 
	 * // Create an instance of the class that exports Excel files, having two
	 * sheets ElementDocument workbook = new ElementDocument(2);
	 * 
	 * // Set the sheet names workbook.easy_getSheetAt(0).setSheetName("First tab");
	 * workbook.easy_getSheetAt(1).setSheetName("Second tab");
	 * 
	 * // Get the table of data for the first worksheet ExcelTable xlsFirstTable =
	 * ((ExcelWorksheet)workbook.easy_getSheetAt(0)).easy_getExcelTable();
	 * 
	 * // Add data in cells for report header for (int column=0; column<5; column++)
	 * { xlsFirstTable.easy_getCell(0,column).setValue("Column " + (column + 1));
	 * xlsFirstTable.easy_getCell(0,column).setDataType(DataType.STRING); }
	 * 
	 * // Add data in cells for report values for (int row=0; row<100; row++) { for
	 * (int column=0; column<5; column++) {
	 * xlsFirstTable.easy_getCell(row+1,column).setValue("Data " + (row + 1) + ", "
	 * + (column + 1));
	 * xlsFirstTable.easy_getCell(row+1,column).setDataType(DataType.STRING); } }
	 * 
	 * // Export the XLSX file System.out.
	 * println("Writing file: C:\\Samples\\Tutorial04 - export data to Excel.xlsx");
	 * workbook.
	 * easy_WriteXLSXFile("C:\\Samples\\Tutorial04 - export data to Excel.xlsx");
	 * 
	 * // Confirm export of Excel file if (workbook.easy_getError().equals(""))
	 * System.out.println("File successfully created."); else
	 * System.out.println("Error encountered: " + workbook.easy_getError());
	 * 
	 * // Dispose memory workbook.Dispose(); }
	 */

}
