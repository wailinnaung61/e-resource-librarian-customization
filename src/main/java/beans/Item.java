package beans;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Item {
	public int itemnumber;
	public String bookcover;
	public MultipartFile bookcoverfile;
	public String keyword;
	public List<SubItem> subItems;
	public String collection;
	public String barcode;
	public String itemCallNumber;
	String booksellerid;
	private String itemtype;
	private String homebranch;


	public int getItemnumber() {
		return itemnumber;
	}

	public void setItemnumber(int itemnumber) {
		this.itemnumber = itemnumber;
	}

	public String getBookcover() {
		return bookcover;
	}

	public void setBookcover(String bookcover) {
		this.bookcover = bookcover;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<SubItem> getSubItems() {
		return subItems;
	}

	public void setSubItems(List<SubItem> subItems) {
		this.subItems = subItems;
	}

	public MultipartFile getBookcoverfile() {
		return bookcoverfile;
	}

	public void setBookcoverfile(MultipartFile bookcoverfile) {
		this.bookcoverfile = bookcoverfile;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getItemCallNumber() {
		return itemCallNumber;
	}

	public void setItemCallNumber(String itemCallNumber) {
		this.itemCallNumber = itemCallNumber;
	}
	
	public String getBooksellerid() {
		return booksellerid;
	}
	 
	public void setBooksellerid(String booksellerid) {
		this.booksellerid = booksellerid;
	}
	
	public String getHomebranch() {
		return homebranch;
	}
	public void setHomebranch(String homebranch) {
		this.homebranch = homebranch;
	}
	
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
}
