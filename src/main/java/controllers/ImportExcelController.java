package controllers;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import beans.Data;
import beans.Fileitems;
import beans.ItemTypes;
import services.ItemService;
import services.SingleBiblioDataService;

@Controller
public class ImportExcelController implements ServletContextAware {

	@Autowired
	ItemService itemService;

	@Autowired
	private SingleBiblioDataService singleBiblioDataService;

	private ServletContext servletContext;

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
			List<ItemTypes> itemTypesList = new ArrayList<ItemTypes>();

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

			List<ItemTypes> itemTypesList = new ArrayList<ItemTypes>();

			CSVReader reader = new CSVReader(
					new FileReader(servletContext.getRealPath("/resources/excels/" + fileName)));

			String[] nextLine;
			int iteration = 0;

			while ((nextLine = reader.readNext()) != null) {
				if (iteration == 0) {
					iteration++;
					continue;
				}

				ItemTypes itemTypes = new ItemTypes();
				itemTypes.setName(nextLine[0].toString());
				itemTypes.setCode(nextLine[1].toString());

				itemTypesList.add(itemTypes);

			}

			itemService.saveItemTypebyExcelorCSV(itemTypesList);
			return "redirect:/viewitemtype";

		}

		return null;
	}

	@GetMapping(value = "/importcollection")
	public String importCollection(Model m, HttpServletResponse res, HttpServletRequest req) {

		m.addAttribute("fileItems", new Fileitems());
		return "importcollection";
	}

	@PostMapping(value = "/savecollection")
	public String saveCollection(@RequestParam("file") MultipartFile multipartfile, HttpServletRequest req,
			HttpServletResponse res, RedirectAttributes redir) throws IOException {

		String extension = "";

		String fileName = uploadExcelFile(multipartfile);

		int index = fileName.lastIndexOf('.');
		if (index > 0) {
			extension = fileName.substring(index + 1);
		}

		if (extension.equals("xlsx")) {
			List<Collection> collectionList = new ArrayList<Collection>();

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

			List<Collection> collectionList = new ArrayList<Collection>();

			CSVReader reader = new CSVReader(
					new FileReader(servletContext.getRealPath("/resources/excels/" + fileName)));

			String[] nextLine;
			int iteration = 0;

			while ((nextLine = reader.readNext()) != null) {
				if (iteration == 0) {
					iteration++;
					continue;
				}

				Collection collection = new Collection();
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

	@PostMapping(value = "/saveExcelItem")
	public String saveExcelItem(@RequestParam("file") MultipartFile multipartfile, HttpServletRequest req,
			HttpServletResponse res, RedirectAttributes redir, Model m) throws IOException {
		String recordNumber = req.getParameter("biblionumber");

		if (recordNumber != null && !recordNumber.isEmpty()) {

			// start process
			String extension = "";
			String fileName = uploadExcelFile(multipartfile);
			int index = fileName.lastIndexOf('.');
			if (index > 0) {
				extension = fileName.substring(index + 1);
			}

			if (extension.equals("xlsx")) {

				List<Data> dataList = new ArrayList<Data>();

				String excelPath = servletContext.getRealPath("/resources/excels/" + fileName);

				FileInputStream file = new FileInputStream(excelPath);

				BufferedInputStream excelBIS = new BufferedInputStream(file);

				XSSFWorkbook excelImportToJTable = new XSSFWorkbook(excelBIS);

				XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);

				for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {

					Cell cell2 = excelSheet.getRow(row).getCell(0);

					cell2.setCellType(CellType.STRING);
					XSSFRow excelRow = excelSheet.getRow(row);
					XSSFCell bookSellerid = excelRow.getCell(0);
					XSSFCell homeBranch = excelRow.getCell(1);
					XSSFCell itemCallNumber = excelRow.getCell(2);
					XSSFCell barcode = excelRow.getCell(3);
					XSSFCell itemTypeCode = excelRow.getCell(4);
					XSSFCell publishedDate = excelRow.getCell(5);
					XSSFCell collectionCode = excelRow.getCell(6);

					Data data = new Data();

					data.setBooksellerid(bookSellerid.toString());
					data.setHomebranch(homeBranch.toString());
					data.setItemcallnumber(itemCallNumber.toString());
					data.setBarcode(barcode.toString());
					data.setItemtype(itemTypeCode.toString());
					data.setPublicationyear(publishedDate.toString());
					data.setCollection(collectionCode.toString());

					dataList.add(data);

				}

				// save excel data
				int count = itemService.saveItemsbyExcelorCSV(dataList, Integer.parseInt(recordNumber));
				
				// get itemsData
				List<Data> itemDatas = singleBiblioDataService.getItemsbyBiblioData(Integer.parseInt(recordNumber));

				m.addAttribute("message", count + " items has been added");
				m.addAttribute("bibliodata", recordNumber);
				m.addAttribute("itemdata", itemDatas);
				return "importitem";
			}

			// end process
		} else {
			redir.addFlashAttribute("alert", "Record can't found");
			redir.addFlashAttribute("bibliodata", null);
			return "redirect:/importitem";
		}
		return null;

	}

	public void setServletContext(ServletContext servletContext) {

		this.servletContext = servletContext;
	}
}
