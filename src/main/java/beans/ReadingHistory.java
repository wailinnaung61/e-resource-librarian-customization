package beans;

public class ReadingHistory {
	private String borrowername;
	private String title;
	private String itemtype;
	private int readcount;
	private String readTS;
	private String agerange;
	private String ipaddress;

	public String getBorrowername() {
		return borrowername;
	}

	public void setBorrowername(String borrowername) {
		this.borrowername = borrowername;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getReadTS() {
		return readTS;
	}

	public void setReadTS(String readTS) {
		this.readTS = readTS;
	}

	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getAgerange() {
		return agerange;
	}

	public void setAgerange(String agerange) {
		this.agerange = agerange;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
}
