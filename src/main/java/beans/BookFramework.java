package beans;

import java.util.List;

public class BookFramework {

	private String title;
	private String author;
	private String notes;
	private String publicationyear;
	private String place;
	private String editionstatement;
	private String subject;
	private String content;
	private String summary;
	private String timestamp;
	private String datecreated;
	private int serial;
	private int biblionumber;
	private String isbn;
	private String itemtype;
	private int biblioitemnumber;
	private String label;
	private String type;
	private boolean required;
	private String path;
	private List<ItemTypes> itemtypes;

	public BookFramework() {
	}

	public BookFramework(String title, String author, String notes, String publicationyear, String place,
			String editionstatement, String subject, String content, String summary, String timestamp,
			String datecreated, int serial, int biblionumber, String isbn, String itemtype, int biblioitemnumber) {
		this.title = title;
		this.author = author;
		this.notes = notes;
		this.publicationyear = publicationyear;
		this.place = place;
		this.editionstatement = editionstatement;
		this.subject = subject;
		this.content = content;
		this.summary = summary;
		this.timestamp = timestamp;
		this.datecreated = datecreated;
		this.serial = serial;
		this.biblionumber = biblionumber;
		this.isbn = isbn;
		this.itemtype = itemtype;
		this.biblioitemnumber = biblioitemnumber;
	}

	public BookFramework(String label, String type, boolean required, String path) {
		this.type = type;
		this.required = required;
		this.label = label;
		this.path = path;
	}

	public BookFramework(String label, String type, boolean required, String path, List<ItemTypes> itemtypes) {
		super();
		this.label = label;
		this.type = type;
		this.required = required;
		this.path = path;
		this.itemtypes = itemtypes;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPublicationyear() {
		return publicationyear;
	}

	public void setPublicationyear(String publicationyear) {
		this.publicationyear = publicationyear;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getEditionstatement() {
		return editionstatement;
	}

	public void setEditionstatement(String editionstatement) {
		this.editionstatement = editionstatement;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public int getBiblionumber() {
		return biblionumber;
	}

	public void setBiblionumber(int biblionumber) {
		this.biblionumber = biblionumber;
	}

	public String getIsbn() {
		return isbn;
	}

	public List<ItemTypes> getItemtypes() {
		return itemtypes;
	}

	public void setItemtypes(List<ItemTypes> itemtypes) {
		this.itemtypes = itemtypes;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public int getBiblioitemnumber() {
		return biblioitemnumber;
	}

	public void setBiblioitemnumber(int biblioitemnumber) {
		this.biblioitemnumber = biblioitemnumber;
	}

}
