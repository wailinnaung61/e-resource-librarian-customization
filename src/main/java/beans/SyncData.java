package beans;

import java.util.Date;

public class SyncData {

	int biblionumber, publicationyear1, number, downloadable, accesslevel, itemnumber, biblioitemnumber, serial,
			itemcount, bm_id;
	String place, publicationyear, subtitle, size, pages, authorname, title, price, notes, itemtype, collection,
			barcode, booksellerid, homebranch, itemcallnumber, publishercode, editionstatement, timestamp, datecreated,
			keyword, isbn, enumchron, bi_notes, i_timestamp, bm_format, bm_schema, bm_metadata, bm_timestamp, itype,
			subject, content, summary, i_collection;

	Date publisheddate;

	public SyncData() {
	}

	public SyncData(String title, String authorname, String notes, String timestamp, String datecreated, int serial,
			int biblionumber, int itemnumber, String booksellerid, String homebranch, String itemcallnumber,
			String barcode, String enumchron, String i_timestamp, String isbn, String publicationyear,
			String publishercode, String itemtype, String collection, int biblioitemnumber, String editionstatement,
			String place, String bi_notes, int bm_id, String bm_format, String bm_schema, String bm_metadata,
			String bm_timestamp, String itype, String subject, String content, String summary, Date publisheddate, String i_collection) {
		this.title = title;
		this.authorname = authorname;
		this.notes = notes;
		this.timestamp = timestamp;
		this.datecreated = datecreated;
		this.serial = serial;
		this.biblionumber = biblionumber;
		this.itemnumber = itemnumber;
		this.booksellerid = booksellerid;
		this.homebranch = homebranch;
		this.itemcallnumber = itemcallnumber;
		this.barcode = barcode;
		this.enumchron = enumchron;
		this.i_timestamp = i_timestamp;
		this.isbn = isbn;
		this.publicationyear = publicationyear;
		this.publishercode = publishercode;
		this.itemtype = itemtype;
		this.collection = collection;
		this.biblioitemnumber = biblioitemnumber;
		this.editionstatement = editionstatement;
		this.place = place;
		this.bi_notes = bi_notes;
		this.bm_id = bm_id;
		this.bm_format = bm_format;
		this.bm_schema = bm_schema;
		this.bm_metadata = bm_metadata;
		this.bm_timestamp = bm_timestamp;
		this.itype = itype;
		this.subject = subject;
		this.content = content;
		this.summary = summary;
		this.publisheddate = publisheddate;
		this.i_collection = i_collection;
	}

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

	public String getBi_notes() {
		return bi_notes;
	}

	public void setBi_notes(String bi_notes) {
		this.bi_notes = bi_notes;
	}

	public String getI_timestamp() {
		return i_timestamp;
	}

	public void setI_timestamp(String i_timestamp) {
		this.i_timestamp = i_timestamp;
	}

	public int getBm_id() {
		return bm_id;
	}

	public void setBm_id(int bm_id) {
		this.bm_id = bm_id;
	}

	public String getBm_format() {
		return bm_format;
	}

	public void setBm_format(String bm_format) {
		this.bm_format = bm_format;
	}

	public String getBm_schema() {
		return bm_schema;
	}

	public void setBm_schema(String bm_schema) {
		this.bm_schema = bm_schema;
	}

	public String getBm_metadata() {
		return bm_metadata;
	}

	public void setBm_metadata(String bm_metadata) {
		this.bm_metadata = bm_metadata;
	}

	public String getBm_timestamp() {
		return bm_timestamp;
	}

	public void setBm_timestamp(String bm_timestamp) {
		this.bm_timestamp = bm_timestamp;
	}

	public String getItype() {
		return itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getPublisheddate() {
		return publisheddate;
	}

	public void setPublisheddate(Date publisheddate) {
		this.publisheddate = publisheddate;
	}
	
	public String getI_collection() {
		return i_collection;
	}

	public void setI_collection(String i_collection) {
		this.i_collection = i_collection;
	}

}