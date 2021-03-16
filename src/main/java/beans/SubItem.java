package beans;

import org.springframework.web.multipart.MultipartFile;

public class SubItem {

	public int resourceId;

	public int accesslevel;

	public int accesspages;

	public int downloadable;

	public String resourceurl;

	public MultipartFile resourcefile;

	public int itemnumber;

	public String collection;

	public boolean changed_access;

	public String oldresourceurl;

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getAccesslevel() {
		return accesslevel;
	}

	public void setAccesslevel(int accesslevel) {
		this.accesslevel = accesslevel;
	}

	public int getAccesspages() {
		return accesspages;
	}

	public void setAccesspages(int accesspages) {
		this.accesspages = accesspages;
	}

	public int getDownloadable() {
		return downloadable;
	}

	public void setDownloadable(int downloadable) {
		this.downloadable = downloadable;
	}

	public String getResourceurl() {
		return resourceurl;
	}

	public void setResourceurl(String resourceurl) {
		this.resourceurl = resourceurl;
	}

	public int getItemnumber() {
		return itemnumber;
	}

	public void setItemnumber(int itemnumber) {
		this.itemnumber = itemnumber;
	}

	public MultipartFile getResourcefile() {
		return resourcefile;
	}

	public void setResourcefile(MultipartFile resourcefile) {
		this.resourcefile = resourcefile;
	}

	public boolean isChanged_access() {
		return changed_access;
	}

	public void setChanged_access(boolean changed_access) {
		this.changed_access = changed_access;
	}

	public String getOldresourceurl() {
		return oldresourceurl;
	}

	public void setOldresourceurl(String oldresourceurl) {
		this.oldresourceurl = oldresourceurl;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}
}
