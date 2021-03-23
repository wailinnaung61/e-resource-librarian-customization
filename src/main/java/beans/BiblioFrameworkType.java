package beans;

public class BiblioFrameworkType {

	private String cd;
	private String book;
	private String searchFramework;

	public BiblioFrameworkType() {
	}

	public BiblioFrameworkType(String cd, String book, String searchFramework) {
		this.cd = cd;
		this.book = book;
		this.searchFramework = searchFramework;
	}

	public BiblioFrameworkType(String cd, String book) {
		this.cd = cd;
		this.book = book;
	}

	public String getSearchFramework() {
		return searchFramework;
	}

	public void setSearchFramework(String searchFramework) {
		this.searchFramework = searchFramework;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

}
