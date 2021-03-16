package beans;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class bibliosingledata {

	private int itemnumber;
	private int biblionumber;
	private int biblioitemnumber;
	private String barcode;
	private String booksellerid;
	private String homebranch;
	private String itemcallnumber;
	private String collectiontitle;
	private String publishercode;
	private String editionstatement;
	private String place;
	private String author;
	private String title;
	private String notes;
	private String timestamp;
	private String datecreated;
	private String keyword;
	private String itemtype;
	private String publishedyear;
	private String publicationyear;
	private String isbn;
	List<Item> items;
	
	public int getItemnumber() {
		return itemnumber;
	}
	public void setItemnumber(int itemnumber) {
		this.itemnumber = itemnumber;
	}
	public int getBiblionumber() {
		return biblionumber;
	}
	public void setBiblionumber(int biblionumber) {
		this.biblionumber = biblionumber;
	}
	public int getBiblioitemnumber() {
		return biblioitemnumber;
	}
	public void setBiblioitemnumber(int biblioitemnumber) {
		this.biblioitemnumber = biblioitemnumber;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
	public String getItemcallnumber() {
		return itemcallnumber;
	}
	public void setItemcallnumber(String itemcallnumber) {
		this.itemcallnumber = itemcallnumber;
	}
	public String getCollectiontitle() {
		return collectiontitle;
	}
	public void setCollectiontitle(String collectiontitle) {
		this.collectiontitle = collectiontitle;
	}
	public String getPublishercode() {
		return publishercode;
	}
	public void setPublishercode(String publishercode) {
		this.publishercode = publishercode;
	}
	public String getEditionstatement() {
		return editionstatement;
	}
	public void setEditionstatement(String editionstatement) {
		this.editionstatement = editionstatement;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getDatecreated() {
		return datecreated;
	}
	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	public String getPublishedyear() {
		return publishedyear;
	}
	public void setPublishedyear(String publishedyear) {
		this.publishedyear = publishedyear;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPublicationyear() {
		return publicationyear;
	}
	public void setPublicationyear(String publicationyear) {
		this.publicationyear = publicationyear;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}