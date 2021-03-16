package services;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import beans.BiblioSearchType;
import beans.Constants;
import beans.Data;
import beans.Item;
import beans.ItemTypes;
import beans.Patron;
import beans.ReadingHistory;
import beans.SpecialRequest;
import beans.SubItem;
import beans.bibliosingledata;
import dao.ItemDao;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

@Service
public class ItemServiceImpl implements ItemService {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	ItemDao itemDao;

	@Autowired
	UtilService utilService;

	@Override
	public bibliosingledata getBiblioItem(BiblioSearchType search) {
		List<Data> data = itemDao.GetBiblioItem(search.getSearchType(), search.getId());
		if (data == null || data.size() == 0)
			return null;
		return ConstructBiblioObject(data);
	}

	private bibliosingledata ConstructBiblioObject(List<Data> data) {
		Map<Integer, Item> itemMap = new HashMap<>();
		bibliosingledata biblio = new bibliosingledata();
		for (Data d : data) {
			biblio.setBiblionumber(d.getBiblionumber());
			biblio.setTitle(d.getTitle());
			biblio.setAuthor(d.getAuthorname());

			Item item = itemMap.getOrDefault(d.getItemnumber(), new Item());
			item.setBookcover(d.getBookcover());
			item.setItemnumber(d.getItemnumber());
			item.setKeyword(d.getKeyword());
			item.setBarcode(d.getBarcode());
			item.setItemCallNumber(d.getItemcallnumber());
			item.setCollection(d.getCollection());

			List<SubItem> sList = item.getSubItems();
			if (sList == null) {
				sList = new ArrayList<SubItem>();
			}
			if (d.getResourceId() != 0) {
				SubItem sItem = new SubItem();
				sItem.setResourceId(d.getResourceId());
				sItem.setResourceurl(d.getResourceUrl());
				sItem.setAccesslevel(d.getAccesslevel());
				sItem.setAccesspages((d.getPages() == null || d.getPages() == "") ? 0 : Integer.parseInt(d.getPages()));
				sItem.setDownloadable(d.getDownloadable());
				sList.add(sItem);
			}

			item.setSubItems(sList);
			itemMap.put(d.getItemnumber(), item);
		}
		List<Item> itemlist = new ArrayList<Item>(itemMap.values());
		biblio.setItems(itemlist);
		return biblio;
	}

	@Override
	public void updateBiblioItem(bibliosingledata biblio) throws IOException {
		List<Item> items = biblio.getItems();
		items.stream().map(x -> {
			if (x.getBookcoverfile() != null && x.getBookcoverfile().getSize() > 0) {
				x.setBookcover(uploadBookcover(x.getBookcoverfile()));
			}
			return x;
		}).collect(Collectors.toList());
		itemDao.UpdateItemData(items);
		for (Item item : items) {
			if (item.getSubItems() != null) {
				for (SubItem sitem : item.getSubItems()) {
					String type = item.getCollection();
					if (type == null || "".equals(type.trim())) {
						type = Constants.UNCATEGORIZED_RESOURCE;
					}
					if (sitem.getResourcefile() != null && sitem.getResourcefile().getSize() > 0) {
						deleteOldResource(Constants.RESTRICTED_RESOURCES, sitem.getOldresourceurl());
						sitem.setResourceurl(
								uploadResource(item.getItemnumber(), biblio.getBiblionumber(), sitem, type));
					}

					boolean isRestriced = (sitem.getResourceurl().endsWith(".docx")
							|| sitem.getResourceurl().endsWith(".pdf")) && (sitem.getAccesslevel() == 2);
					boolean isChanged = sitem.isChanged_access();

					if (isRestriced || (isRestriced && isChanged)) {
						deleteOldResource(Constants.PUBLIC_RESOURCES, sitem.getOldresourceurl());
						uploadPublicDoc(sitem.getResourceurl(), type, sitem.getAccesspages());
					}
					sitem.setItemnumber(item.getItemnumber());
					if (sitem.getResourceId() == -1) {
						itemDao.InsertResourceItem(sitem);
					} else if (sitem.getResourceId() != 0) {
						itemDao.UpdateResourceItem(sitem);
					}
				}
			}
		}
		List<Integer> ids = items.stream().map(x -> x.getItemnumber()).collect(Collectors.toList());
		utilService.rebuildIndexes(ids);
	}

	private void deleteOldResource(String resourcePath, String oldresourceurl) {
		try {
			File f = new File(Constants.RESOURCE_BASE_URL + resourcePath + "/" + oldresourceurl);
			f.delete();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	public String uploadBookcover(MultipartFile file) {
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		fileName = uploadFile(file, Constants.BOOK_COVER_RESOURCE, fileName);
		return Constants.BOOK_COVER_RESOURCE + fileName;
	}

	public String uploadResource(int itemNumber, int bNumber, SubItem item, String type) {
		MultipartFile file = item.getResourcefile();
		String oFileName = file.getOriginalFilename();
		if (oFileName.length() > 200) {
			String fileType = oFileName.substring(oFileName.lastIndexOf("."));
			oFileName = oFileName.substring(0, 200) + "." + fileType;
		}
		String fileName = String.format("%d_%d_%s", bNumber, itemNumber, oFileName);
		String fullResourceUrl = Constants.RESTRICTED_RESOURCES + type + "/";
		String storedFileName = type + "/" + fileName;

		uploadFile(file, fullResourceUrl, fileName);
		if (fileName.endsWith(".docx")) {
			fileName = fileName.replace(".docx", ".pdf");
			uploadDocxFile(file, Constants.RESTRICTED_RESOURCES + type + "/", fileName);
		}

		return storedFileName;
	}

	public String uploadFile(MultipartFile file, String fileURL, String fileName) {
		try {
			File directory = new File(Constants.RESOURCE_BASE_URL + fileURL);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			File targetFile = new File(Constants.RESOURCE_BASE_URL + fileURL + fileName);
			BufferedInputStream fis = new BufferedInputStream(file.getInputStream());
			FileOutputStream outStream = new FileOutputStream(targetFile);
			int count = 0;
			byte[] bufferedBytes = new byte[1024];
			while ((count = fis.read(bufferedBytes)) != -1) {
				outStream.write(bufferedBytes, 0, count);
			}
			outStream.close();
			/*
			 * Path path = Paths.get(Constants.RESOURCE_BASE_URL + fileURL + fileName);
			 * 
			 * byte[] bytes = file.getBytes(); Files.write(path, bytes);
			 */

			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String uploadDocxFile(MultipartFile file, String fileURL, String fileName) {
		try {
			XWPFDocument doc = new XWPFDocument(new ByteArrayInputStream(file.getBytes()));
			PdfOptions pdfOptions = PdfOptions.create();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PdfConverter.getInstance().convert(doc, out, pdfOptions);
			FileUtils.writeByteArrayToFile(new File(Constants.RESOURCE_BASE_URL + fileURL + fileName),
					out.toByteArray());
			doc.close();
			out.close();
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void uploadPublicDoc(String resourceURL, String type, int accessPages) throws IOException {
		PDDocument pdf = null;
		PDDocument doc = null;
		List<PDDocument> pages = null;
		try {
			pdf = PDDocument.load(new File(Constants.RESOURCE_BASE_URL + Constants.RESTRICTED_RESOURCES + resourceURL),
					MemoryUsageSetting.setupTempFileOnly());
			doc = new PDDocument();
			Splitter splitter = new Splitter();
			pages = splitter.split(pdf);
			for (int i = 0; i < accessPages; i++) {
				if (i < pages.size()) {
					doc.addPage(pages.get(i).getPage(0));
				}
			}
			String filePath = Constants.RESOURCE_BASE_URL + Constants.PUBLIC_RESOURCES;
			File directory = new File(filePath + type + "/");
			if (!directory.exists()) {
				directory.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(new File(filePath + resourceURL));
			doc.save(fos);

			logger.info("Saved public pdf : {}", filePath + resourceURL);
		} catch (Exception ex) {
			logger.error("Error Occurred Uploading {},` {}", resourceURL, ex);
		} finally {
			if (doc != null) {
				doc.close();
			}
			if (pdf != null) {
				pdf.close();
			}
			if (pages != null && pages.size() > 0) {
				for (PDDocument p : pages) {
					p.close();
				}
			}
		}
	}

	@Override
	public void deleteResourceById(int id) throws Exception {
		itemDao.deleteResource(id);
	}

	@Override
	public List<ItemTypes> getItemTypesList() {
		return itemDao.getItemTypesList();
	}

	@Override
	public List<Data> getSyncedBiblioList(int itemNumber) {
		return itemDao.getSyncedBiblioList(itemNumber);
	}

	@Override
	public List<Patron> getPatronList(String last_sync) {
		return itemDao.getPatronList(last_sync);
	}

	@Override
	public List<SpecialRequest> getSpecialRequests() {
		return itemDao.getSpecialRequests();
	}

	@Override
	public void updateSpecialRequestById(int id, int status) throws Exception {
		if (status < 0 || status > 1)
			throw new Exception("Unsupported Status");
		String status_str = status == 0 ? "approved" : "declined";
		itemDao.updateSpecialRequestById(id, status_str);
	}

	@Override
	public List<ReadingHistory> getReadingHistory(String from, String to) {
		return itemDao.getReadingHistory(formatDate(from), formatDate(to));
	}

	@Override
	public List<Patron> getPatronAccessReportByCity(String from, String to) {
		return itemDao.getPatronAccessReportByCity(formatDate(from), formatDate(to));
	}

	@Override
	public List<Patron> getPatronAccessReportByCategory(String from, String to) {
		return itemDao.getPatronAccessReportByCategory(formatDate(from), formatDate(to));
	}

	@Override
	public int getPendingSpecialRequestCount() {
		return itemDao.getPendingSpecialRequestCount();
	}

	@Override
	public List<ReadingHistory> getItemsPopularityReport(String from, String to) {
		return itemDao.getItemsPopularityReport(formatDate(from), formatDate(to));
	}

	@Override
	public Map<String, List<ReadingHistory>> getItemsPopularityByAgeReport(String from, String to) {
		return itemDao.getItemsPopularityByAgeByAgeReport(formatDate(from), formatDate(to));
	}

	public String formatDate(String date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat new_df = new SimpleDateFormat("yyy-MM-dd");
		df.setLenient(false);
		try {
			String new_date = new_df.format(df.parse(date));
			return new_date;
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public void checkAndRegenerateResources(int offset, int count) {
		List<SubItem> items = itemDao.getResourceUrlList(offset, count);
		for (SubItem subItem : items) {
			try {
				File f = new File(Constants.RESOURCE_BASE_URL + Constants.PUBLIC_RESOURCES + subItem.resourceurl);
				String type = subItem.collection;
				if (type == null || "".equals(type.trim())) {
					type = Constants.UNCATEGORIZED_RESOURCE;
				}
				if (!f.exists()) {
					uploadPublicDoc(subItem.resourceurl, type, subItem.accesspages);
				}
			} catch (Exception ex) {
				logger.error("Error occured regenerating resource : {}", ex.getMessage());
			}
		}
	}

	@Override
	public void deleteBiblio(int biblionumber) {
		List<Data> dList = itemDao.GetItemsByBiblioNumbers(Collections.singletonList(biblionumber));
		List<Integer> deleteItemNumbers = dList.stream().map(Data::getItemnumber).collect(Collectors.toList());
		if (deleteItemNumbers.size() > 0) {
			itemDao.deleteUnlinkedResources(deleteItemNumbers);
		}
		itemDao.deleteBiblio(biblionumber);
		utilService.removeStaticResources(dList);
		utilService.deleteSearchIndex(biblionumber);
	}

	@Override
	public int getItemtypesByItemName(String name) {
		return itemDao.getItemtypesByItemName(name);
	}

	@Override
	public void saveItemType(ItemTypes itemtypes) {
		itemDao.saveItemType(itemtypes);

	}

}
