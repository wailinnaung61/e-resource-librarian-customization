package beans;

import java.io.File;

public class Fileitems {
	
	String accesspage;
	File fileobj;
	int itemnumber;
	String accesslink;
	String authortitle;
	String authorortitle;
	String collection;
	String itemtype;
	
	public Fileitems() {}
	

	public Fileitems(String accesspage, File fileobj, int itemnumber, String accesslink, String authortitle,
			String authorortitle, String collection,String itemtype) {
		super();
		this.accesspage = accesspage;
		this.fileobj = fileobj;
		this.itemnumber = itemnumber;
		this.accesslink = accesslink;
		this.authortitle = authortitle;
		this.authorortitle = authorortitle;
		this.collection = collection;
		this.itemtype=itemtype;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public String getAuthortitle() {
		return authortitle;
	}
	public void setAuthortitle(String authortitle) {
		this.authortitle = authortitle;
	}
	public String getAuthorortitle() {
		return authorortitle;
	}
	public void setAuthorortitle(String authorortitle) {
		this.authorortitle = authorortitle;
	}
	public String getAccesslink() {
		return accesslink;
	}
	public void setAccesslink(String accesslink) {
		this.accesslink = accesslink;
	}
	public int getItemnumber() {
		return itemnumber;
	}
	public void setItemnumber(int itemnumber) {
		this.itemnumber = itemnumber;
	}
	public String getAccesspage() {
		return accesspage;
	}
	public void setAccesspage(String accesspage) {
		this.accesspage = accesspage;
	}
	public File getFileobj() {
		return fileobj;
	}
	public void setFileobj(File fileobj) {
		this.fileobj = fileobj;
	}
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	
}
