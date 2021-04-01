package controllers;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.com.bytecode.opencsv.CSVReader;
import beans.Collection;
import beans.Fileitems;
import beans.ItemTypes;
import services.ItemService;

@Controller
public class ImportExcelController implements ServletContextAware {

	@Autowired
	ItemService itemService;

	private ServletContext servletContext;

	List<ItemTypes> itemTypesList = new ArrayList<ItemTypes>();
	List<Collection> collectionList=new ArrayList<Collection>();

	@GetMapping(value = "/importitemtype")
	public String importItemType(Model m, HttpServletRequest req, HttpServletResponse res) {
		m.addAttribute("fileItems", new Fileitems());
		return "importitemtype";
	}

	@PostMapping(value = "/saveExcelItemtype")
	public String saveExcelItemType(@RequestParam("file") MultipartFile multipartfile, HttpServletRequest req,
			HttpServletResponse res, RedirectAttributes redir) throws IOException {

		String extension = "";

		String fileName = uploadExcelFile(multipartfile);

		int index = fileName.lastIndexOf('.');
		if (index > 0) {
			extension = fileName.substring(index + 1);
		}

		if (extension.equals("xlsx")) {

			String excelPath = servletContext.getRealPath("/resources/excels/" + fileName);

			FileInputStream file = new FileInputStream(excelPath);

			BufferedInputStream excelBIS = new BufferedInputStream(file);

			XSSFWorkbook excelImportToJTable = new XSSFWorkbook(excelBIS);

			XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);

			for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {

				Cell cell2 = excelSheet.getRow(row).getCell(0);

				cell2.setCellType(CellType.STRING);
				XSSFRow excelRow = excelSheet.getRow(row);
				XSSFCell excelItemtypes = excelRow.getCell(0);
				XSSFCell excelItemcodes = excelRow.getCell(1);

				ItemTypes itemTypes = new ItemTypes();

				itemTypes.setName(excelItemtypes.toString());
				itemTypes.setCode(excelItemcodes.toString());

				itemTypesList.add(itemTypes);
			}
			itemService.saveItemTypebyExcelorCSV(itemTypesList);
			return "redirect:/viewitemtype";
		}
		
		else if (extension.equals("csv")) {
			
			List<ItemTypes> itemTypesList=new ArrayList<ItemTypes>();
			 
			CSVReader reader = new CSVReader(
					new FileReader(servletContext.getRealPath("/resources/excels/" + fileName)));
			
			String [] nextLine;
			int iteration=0;
			
	        while ((nextLine = reader.readNext()) != null) {
	        	if(iteration==0)
	        	{
	        		iteration++;
	        		continue;
	        	}
	        	
	            ItemTypes itemTypes=new ItemTypes();
	            itemTypes.setName(nextLine[0].toString());
	            itemTypes.setCode(nextLine[1].toString());
	            
	            itemTypesList.add(itemTypes);
	            
	        }
	        
	        itemService.saveItemTypebyExcelorCSV(itemTypesList);
			return "redirect:/viewitemtype";
	       
		}

		return null;
	}
	
	@GetMapping(value= "/importcollection")
	public String importCollection(Model m,HttpServletResponse res,HttpServletRequest req)
	{
		
		m.addAttribute("fileItems", new Fileitems());
		return "importcollection";
	}
	
	@PostMapping(value="/savecollection")
	public String saveCollection(@RequestParam("file") MultipartFile multipartfile, HttpServletRequest req,
			HttpServletResponse res, RedirectAttributes redir) throws IOException {

		String extension = "";

		String fileName = uploadExcelFile(multipartfile);

		int index = fileName.lastIndexOf('.');
		if (index > 0) {
			extension = fileName.substring(index + 1);
		}

		if (extension.equals("xlsx")) {

			String excelPath = servletContext.getRealPath("/resources/excels/" + fileName);

			FileInputStream file = new FileInputStream(excelPath);

			BufferedInputStream excelBIS = new BufferedInputStream(file);

			XSSFWorkbook excelImportToJTable = new XSSFWorkbook(excelBIS);

			XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);

			for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {

				Cell cell2 = excelSheet.getRow(row).getCell(0);

				cell2.setCellType(CellType.STRING);
				XSSFRow excelRow = excelSheet.getRow(row);
				XSSFCell excelItemtypes = excelRow.getCell(0);
				XSSFCell excelItemcodes = excelRow.getCell(1);

				Collection collection = new Collection();

				collection.setName(excelItemtypes.toString());
				collection.setCode(excelItemcodes.toString());

				collectionList.add(collection);
			}
			itemService.saveCollectionbyExcelorCSV(collectionList);
			return "redirect:/viewcollection";
		}
		
		else if (extension.equals("csv")) {
			
			List<Collection> collectionList=new ArrayList<Collection>();
			 
			CSVReader reader = new CSVReader(
					new FileReader(servletContext.getRealPath("/resources/excels/" + fileName)));
			
			String [] nextLine;
			int iteration=0;
			
	        while ((nextLine = reader.readNext()) != null) {
	        	if(iteration==0)
	        	{
	        		iteration++;
	        		continue;
	        	}
	        	
	            Collection collection=new Collection();
	            collection.setName(nextLine[0].toString());
	            collection.setCode(nextLine[1].toString());
	            
	            collectionList.add(collection);
	            
	        }
	        
	        itemService.saveCollectionbyExcelorCSV(collectionList);
			return "redirect:/viewcollection";
	       
		}

		return null;
	}

	private String uploadExcelFile(MultipartFile multipartFile) {
		try {
			byte[] bytes = multipartFile.getBytes();

			Path path = Paths
					.get(servletContext.getRealPath("/resources/excels/" + multipartFile.getOriginalFilename()));
			Files.write(path, bytes);
			return multipartFile.getOriginalFilename();
		} catch (Exception e) {
			return null;
		}
	}

	public void setServletContext(ServletContext servletContext) {

		this.servletContext = servletContext;
	}
	/*
	 * private ServletContext servletContext;
	 * 
	 * @Autowired UserDao dao; public ArrayList<Data> list = new ArrayList<Data>();
	 * public Data e;
	 * 
	 * // For Import Excel
	 * 
	 * @RequestMapping("importexcel") public String importexcel(Model m,
	 * HttpServletRequest req, HttpServletResponse res) { m.addAttribute("command",
	 * new Fileitems()); return "importexcel"; }
	 * 
	 * // For Excel Import Clicking import Button // Start
	 * 
	 * @RequestMapping(value = "process") public String process(Model
	 * m, @RequestParam("file") MultipartFile multipartfile, RedirectAttributes
	 * redir) throws Exception { String fileName = uploadExcelFile(multipartfile);
	 * System.out.println("File Name: " + fileName); String excelPath =
	 * servletContext.getRealPath("/resources/excels/" + fileName);
	 * System.out.println("Excel Path: " + excelPath);
	 * 
	 * FileInputStream file = new FileInputStream(excelPath);
	 * 
	 * // Create Workbook instance holding reference to .xlsx file XSSFWorkbook
	 * workbook = new XSSFWorkbook(file);
	 * 
	 * // Get first/desired sheet from the workbook XSSFSheet sheet =
	 * workbook.getSheetAt(0);
	 * 
	 * // I've Header and I'm ignoring header for that I've +1 in loop for (int i =
	 * sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) { e = new
	 * Data(); Row ro = sheet.getRow(i); for (int j = ro.getFirstCellNum(); j <=
	 * ro.getLastCellNum(); j++) { Cell ce = ro.getCell(j,
	 * Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
	 * 
	 * // For Itemnumber if (j == 0) { if (ce.getCellType() == ce.CELL_TYPE_STRING)
	 * e.setItemnumber(Integer.parseInt(ce.getStringCellValue())); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC) e.setItemnumber((int)
	 * ce.getNumericCellValue()); }
	 * 
	 * // For Biblionumber if (j == 1) { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING)
	 * e.setBiblionumber(Integer.parseInt(ce.getStringCellValue())); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC) e.setBiblionumber((int)
	 * ce.getNumericCellValue()); }
	 * 
	 * // For biblioitemnumber if (j == 2) { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING)
	 * e.setBiblioitemnumber(Integer.parseInt(ce.getStringCellValue())); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC) e.setBiblioitemnumber((int)
	 * ce.getNumericCellValue()); }
	 * 
	 * // for serial if (j == 3) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setSerial(0); } else { if (ce.getCellType() ==
	 * ce.CELL_TYPE_NUMERIC) e.setSerial((int) ce.getNumericCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_STRING)
	 * e.setSerial(Integer.parseInt(ce.getStringCellValue())); } }
	 * 
	 * // For barcodde if (j == 4) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setBarcode("NULL"); } else { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING) e.setBarcode(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC) e.setBarcode(String.valueOf((int)
	 * ce.getNumericCellValue())); } }
	 * 
	 * // For booksellerid if (j == 5) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setBooksellerid("NULL"); } else { String hello =
	 * ce.getStringCellValue(); String result = hello.replaceAll("^\"|\"$", "");
	 * e.setBooksellerid(result);
	 * 
	 * } }
	 * 
	 * // For homebranch if (j == 6) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setHomebranch("NULL"); } else { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING) e.setHomebranch(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC)
	 * e.setHomebranch(String.valueOf((int) ce.getNumericCellValue())); } }
	 * 
	 * // For Collection Code if (j == 7) { if (ce == null || ce.getCellTypeEnum()
	 * == CellType.BLANK) { e.setCollection("NULL"); } else { if (ce.getCellType()
	 * == ce.CELL_TYPE_STRING) e.setCollection(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC)
	 * e.setCollection(String.valueOf((int) ce.getNumericCellValue())); } }
	 * 
	 * // For isbn if (j == 8) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setIsbn("NULL"); } else { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING) e.setIsbn(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC) e.setIsbn(String.valueOf((int)
	 * ce.getNumericCellValue())); } }
	 * 
	 * // For itemcallnumber if (j == 9) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setItemcallnumber("NULL"); } else { if (ce.getCellType()
	 * == ce.CELL_TYPE_STRING) e.setItemcallnumber(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC)
	 * e.setItemcallnumber(String.valueOf((int) ce.getNumericCellValue())); } }
	 * 
	 * // For itemtype if (j == 10) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setItemtype("NULL"); } else { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING) e.setItemtype(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC) e.setItemtype(String.valueOf((int)
	 * ce.getNumericCellValue())); } }
	 * 
	 * // For Author if (j == 11) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) {
	 * 
	 * e.setAuthorname("NULL"); } else { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING) e.setAuthorname(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC)
	 * e.setAuthorname(String.valueOf((int) ce.getNumericCellValue())); } }
	 * 
	 * // For Title if (j == 12) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) {
	 * 
	 * e.setTitle("NULL"); } else { if (ce.getStringCellValue().contains("'")) {
	 * String s = ce.getStringCellValue().replace("'", "''"); System.out.println(s);
	 * e.setTitle(s); } else { e.setTitle(ce.getStringCellValue()); } } }
	 * 
	 * // For Publisher Code if (j == 13) { if (ce == null || ce.getCellTypeEnum()
	 * == CellType.BLANK) { e.setPublishercode("NULL"); } else { if
	 * (ce.getStringCellValue().contains("'")) { String s =
	 * ce.getStringCellValue().replace("'", "''"); System.out.println(s);
	 * e.setPublishercode(s); } else { e.setPublishercode(ce.getStringCellValue());
	 * } } }
	 * 
	 * // For publication year if (j == 14) { if (ce == null || ce.getCellTypeEnum()
	 * == CellType.BLANK) { e.setPublicationyear("NULL"); } else { if
	 * (ce.getCellType() == ce.CELL_TYPE_STRING)
	 * e.setPublicationyear(ce.getStringCellValue()); else if (ce.getCellType() ==
	 * ce.CELL_TYPE_NUMERIC) e.setPublicationyear(String.valueOf((int)
	 * ce.getNumericCellValue())); } }
	 * 
	 * // For EditionStatement if (j == 15) { if (ce == null || ce.getCellTypeEnum()
	 * == CellType.BLANK) { e.setEditionstatement("NULL"); } else { if
	 * (ce.getCellType() == ce.CELL_TYPE_STRING)
	 * e.setEditionstatement(ce.getStringCellValue()); else if (ce.getCellType() ==
	 * ce.CELL_TYPE_NUMERIC) e.setEditionstatement(String.valueOf((int)
	 * ce.getNumericCellValue())); } }
	 * 
	 * // For Place if (j == 16) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setPlace("NULL"); } else { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING) e.setPlace(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC) e.setPlace(String.valueOf((int)
	 * ce.getNumericCellValue())); } }
	 * 
	 * // For keyword if (j == 17) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setKeyword("NULL"); } else { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING) e.setKeyword(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC) e.setKeyword(String.valueOf((int)
	 * ce.getNumericCellValue())); } }
	 * 
	 * // For Note if (j == 18) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) {
	 * 
	 * e.setNotes("NULL"); } else { if (ce.getCellType() == ce.CELL_TYPE_STRING)
	 * e.setNotes(ce.getStringCellValue()); else if (ce.getCellType() ==
	 * ce.CELL_TYPE_NUMERIC) e.setNotes(String.valueOf((int)
	 * ce.getNumericCellValue())); } }
	 * 
	 * // For enumchron if (j == 19) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setEnumchron("NULL"); } else { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING) e.setEnumchron(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC)
	 * e.setEnumchron(String.valueOf((int) ce.getNumericCellValue())); } }
	 * 
	 * // For Timestamp if (j == 20) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setTimestamp("NULL"); } else { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING) e.setTimestamp(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC)
	 * e.setTimestamp(String.valueOf((int) ce.getNumericCellValue())); } }
	 * 
	 * // For datecreated if (j == 21) { if (ce == null || ce.getCellTypeEnum() ==
	 * CellType.BLANK) { e.setDatecreated("NULL"); } else { if (ce.getCellType() ==
	 * ce.CELL_TYPE_STRING) e.setDatecreated(ce.getStringCellValue()); else if
	 * (ce.getCellType() == ce.CELL_TYPE_NUMERIC)
	 * e.setDatecreated(String.valueOf((int) ce.getNumericCellValue())); } }
	 * 
	 * } list.add(e);
	 * 
	 * }
	 * 
	 * for (Data d : list) { System.out.println("Itemnumber: " + d.getItemnumber() +
	 * "\n" + "Biblionumber: " + d.getBiblionumber() + "\n" + "Biblioitemnumber: " +
	 * d.getBiblioitemnumber() + "\n" + "Serial" + d.getSerial() + "\n" +
	 * "Barcode: " + d.getBarcode() + "\n" + "Booksellerid: " + d.getBooksellerid()
	 * + "\n" + "Homebranch: " + d.getHomebranch() + "\n" + "Itemcallnumber: " +
	 * d.getItemcallnumber() + "\n" + "Collection: " + d.getCollection() + "\n" +
	 * "Publisher Code: " + d.getPublishercode() + "\n" + "Edition Statement: " +
	 * d.getEditionstatement() + "\n" + "Place: " + d.getPlace() + "\n" + "Author: "
	 * + d.getAuthorname() + "\n" + "Title: " + d.getTitle() + "\n" + "Timestamp: "
	 * + d.getTimestamp() + "\n" + "Date Created: " + d.getDatecreated() + "\n" +
	 * "Keyword: " + d.getKeyword() + "\n" + "Itemtype: " + d.getItemtype() + "\n" +
	 * "Published year: " + d.getPublicationyear() + "\n" + "Isbn: " + d.getIsbn() +
	 * "\n" + "Enum: " + d.getEnumchron() + "\n");
	 * 
	 * }
	 * 
	 * workbook.close();
	 * 
	 * return "importexcel";
	 * 
	 * }
	 * 
	 * private String uploadExcelFile(MultipartFile multipartFile) { try { byte[]
	 * bytes = multipartFile.getBytes();
	 * 
	 * Path path = Paths .get(servletContext.getRealPath("/resources/excels/" +
	 * multipartFile.getOriginalFilename())); Files.write(path, bytes); return
	 * multipartFile.getOriginalFilename(); } catch (Exception e) { return null; } }
	 * 
	 * // End
	 * 
	 * @RequestMapping("/uploadrecord") public String uploadrecord(Model m,
	 * HttpServletRequest req, HttpServletResponse res) {
	 * 
	 * HttpSession session = req.getSession(); if (session.getAttribute("loginname")
	 * == null) { return "redirect:/"; } else { String message =
	 * dao.saveexceldata(list); list.clear();
	 * 
	 * List<Data> forshow = dao.getDatas();
	 * 
	 * String search = "<div class='form-group mb-2'>" +
	 * "<input type='text' class='form-control' placeholder='Author,Title' name='authortitle'>"
	 * + "</div>" + "<div class='form-group mx-sm-3 mb-2'>" +
	 * "<select class='custom-select bg-light' id='inputGroupSelect02' name='authorortitle'>"
	 * + "<option value='Author' selected>Author</option>" +
	 * "<option value='Title'>Title</option>" + "</select>" + "</div>" +
	 * "<button type='submit' class='btn mb-2' style='border-radius:10px;width:100px;color:white;background-color:#475B9E;'>Search</button>"
	 * ;
	 * 
	 * if (message != null) { m.addAttribute("message", message); } else {
	 * m.addAttribute("search", search); m.addAttribute("list", forshow); }
	 * 
	 * return "importexcel"; }
	 * 
	 * }
	 * 
	 * // for checkbox download checkbox
	 * 
	 * @RequestMapping(value = "downloaddetails", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public ModelAndView details(@RequestParam Integer
	 * downloadable, @RequestParam Integer itemnumber, HttpServletRequest request,
	 * HttpServletResponse response) { System.out.println(downloadable + "\n" +
	 * itemnumber);
	 * 
	 * dao.updatedownload(downloadable, itemnumber);
	 * 
	 * return null;
	 * 
	 * }
	 * 
	 * // for radio button partial full
	 * 
	 * @RequestMapping(value = "radiodetails", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public ModelAndView rdodetails(@RequestParam String
	 * accesslevel, @RequestParam int itemnumber, HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * if (accesslevel.equals("full")) { int i = 1; dao.updateradio(itemnumber, i);
	 * } else { int i = 2; dao.updateradio(itemnumber, i); }
	 * 
	 * return null;
	 * 
	 * }
	 * 
	 * // for fileupload in import excel
	 * 
	 * @RequestMapping(value = "/fileupload", method = RequestMethod.POST) public
	 * String uploadFile(@ModelAttribute("fileitems") Fileitems fileitems,
	 * 
	 * @RequestParam CommonsMultipartFile file) {
	 * 
	 * int itemnumber = fileitems.getItemnumber(); String accesslink =
	 * fileitems.getAccesslink(); int accesspage =
	 * Integer.parseInt(fileitems.getAccesspage()); String collections =
	 * fileitems.getCollection();
	 * 
	 * String collection = collections.replaceAll("\\s", "");
	 * 
	 * System.out.println(collection);
	 * 
	 * System.out.println(file.isEmpty()); if (file.isEmpty() == false) {
	 * 
	 * // Upload PDF files String filename; File dirPath1 = new
	 * File("/mnt/e_resources/" + collection); if (dirPath1.mkdir()) {
	 * 
	 * System.out.println("Directory created successfully");
	 * 
	 * filename = file.getOriginalFilename(); try { byte[] bytes = file.getBytes();
	 * 
	 * // BufferedOutputStream bout=new BufferedOutputStream(new //
	 * FileOutputStream("resources/e_resources/"+collection+"/"+filename)); Path
	 * path = Paths.get("/mnt/e_resources/" + collection + "/" +
	 * file.getOriginalFilename()); Files.write(path, bytes);
	 * 
	 * } catch (Exception e) { System.out.println(e); }
	 * 
	 * } else {
	 * 
	 * filename = file.getOriginalFilename();
	 * 
	 * try { byte barr[] = file.getBytes();
	 * 
	 * Path path = Paths.get("/mnt/e_resources/" + collection + "/" +
	 * file.getOriginalFilename()); Files.write(path, barr);
	 * 
	 * } catch (Exception e) { System.out.println(e); }
	 * 
	 * } // End Upload PDF String url = "/mnt/e_resources/" + collection + "/" +
	 * filename; dao.savePDFinformation(url, accesspage, itemnumber);
	 * 
	 * }
	 * 
	 * else
	 * 
	 * { dao.savePDFinformation(accesslink, accesspage, itemnumber); }
	 * 
	 * return "redirect:/uploadrecord"; }
	 * 
	 * // for search in importexcel
	 * 
	 * @RequestMapping(value = "/searchauthorandtitle", method = RequestMethod.POST)
	 * public String searchauthortitle(@ModelAttribute("fileitems") Fileitems
	 * fileitems, Model m) throws UnsupportedEncodingException {
	 * 
	 * System.out.println(fileitems.getAuthorortitle());
	 * 
	 * System.out.println(fileitems.getAuthortitle()); String search =
	 * "<div class='form-group mb-2'>" +
	 * "<input type='text' class='form-control' placeholder='Author,Title' name='authortitle'>"
	 * + "</div>" + "<div class='form-group mx-sm-3 mb-2'>" +
	 * "<select class='custom-select bg-light' id='inputGroupSelect02' name='authorortitle'>"
	 * + "<option value='Author' selected>Author</option>" +
	 * "<option value='Title'>Title</option>" + "</select>" + "</div>" +
	 * "<button type='submit' class='btn mb-2' style='border-radius:10px;width:100px;color:white;background-color:#475B9E;'>Search</button>"
	 * ;
	 * 
	 * if (fileitems.getAuthortitle().equals("")) { return "redirect:/uploadrecord";
	 * }
	 * 
	 * else {
	 * 
	 * // user choose Author and click if
	 * (fileitems.getAuthorortitle().equals("Author")) {
	 * System.out.println("Hello Author"); List<Data> forshow =
	 * dao.getDatasbyauthor(fileitems.getAuthortitle()); for (Data d : forshow) {
	 * 
	 * m.addAttribute("search", search); m.addAttribute("list", forshow); return
	 * "importexcel";
	 * 
	 * } } // user choose title and click else { System.out.println("Hello Title");
	 * List<Data> forshow = dao.getDatasbytitle(fileitems.getAuthortitle()); for
	 * (Data d : forshow) {
	 * 
	 * m.addAttribute("search", search); m.addAttribute("list", forshow); return
	 * "importexcel";
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * m.addAttribute("search", search); return "importexcel"; }
	 * 
	 * @RequestMapping(value = "/imageupload", method = RequestMethod.POST) public
	 * String uploadimage(@ModelAttribute("fileitems") Fileitems fileitems,
	 * 
	 * @RequestParam CommonsMultipartFile file) { String fileName =
	 * uploadbookcover(file); String imagePath =
	 * servletContext.getRealPath("/resources/bookcovers/" + fileName);
	 * 
	 * String imagepath = "resources/bookcovers/" + fileName;
	 * 
	 * System.out.println("Image Path: " + imagePath);
	 * 
	 * System.out.println("Hello: " + imagepath);
	 * 
	 * dao.saveimageurl(fileitems.getItemnumber(), imagepath);
	 * 
	 * return "redirect:/uploadrecord"; }
	 * 
	 * private String uploadbookcover(MultipartFile multipartFile) { try { byte[]
	 * bytes = multipartFile.getBytes();
	 * 
	 * Path path = Paths .get(servletContext.getRealPath("/resources/bookcovers/" +
	 * multipartFile.getOriginalFilename())); Files.write(path, bytes); return
	 * multipartFile.getOriginalFilename(); } catch (Exception e) { return null; } }
	 * 
	 * public void setServletContext(ServletContext servletContext) {
	 * 
	 * this.servletContext = servletContext; }
	 */

}
