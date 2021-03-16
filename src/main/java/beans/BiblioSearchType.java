package beans;

public class BiblioSearchType {
	String searchType;
	int id;
	public BiblioSearchType() {
		
	}
	public BiblioSearchType(String searchType, int id) {
		super();
		this.searchType = searchType;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
}
