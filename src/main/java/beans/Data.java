package beans;

import java.io.Serializable;

public class Data implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 4522041425854383242L;
	int biblionumber,publicationyear1,number,downloadable,accesslevel,itemnumber,biblioitemnumber,serial,itemcount, resourceId;
    String place,publicationyear,subtitle,size,pages,authorname,title,price,notes,itemtype,
    collection,barcode,booksellerid,homebranch,itemcallnumber,publishercode,editionstatement,timestamp,datecreated,
    keyword,isbn,enumchron, resourceUrl, bookcover;
    
    public Data()
    {}
	
	public int getBiblionumber() {
		return biblionumber;
	}
	public void setBiblionumber(int biblionumber) {
		this.biblionumber = biblionumber;
	}
	public int getPublicationyear1() {
		return publicationyear1;
	}
	public void setPublicationyear1(int publicationyear1) {
		this.publicationyear1 = publicationyear1;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getDownloadable() {
		return downloadable;
	}
	public void setDownloadable(int downloadable) {
		this.downloadable = downloadable;
	}
	public int getAccesslevel() {
		return accesslevel;
	}
	public void setAccesslevel(int accesslevel) {
		this.accesslevel = accesslevel;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPublicationyear() {
		return publicationyear;
	}
	public void setPublicationyear(String publicationyear) {
		this.publicationyear = publicationyear;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public int getItemnumber() {
		return itemnumber;
	}
	public void setItemnumber(int itemnumber) {
		this.itemnumber = itemnumber;
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
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public String getEnumchron() {
		return enumchron;
	}
	public void setEnumchron(String enumchron) {
		this.enumchron = enumchron;
	}
	public int getItemcount() {
		return itemcount;
	}
	public void setItemcount(int itemcount) {
		this.itemcount = itemcount;
	}
	
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	
	public String getBookcover() {
		return bookcover;
	}
	public void setBookcover(String bookcover) {
		this.bookcover = bookcover;
	}
}

