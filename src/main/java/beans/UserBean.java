package beans;
public class UserBean {
	private int id;  
	private String name;  
	private String username;  
	private String email;  
	private String password;
	private int status;
	private String created;
	private String updated;
	private String rolename;//for select option
	private int roleid;
	private String changepassword;
	private int code;
	private String collection;
	private int collectionid;
	private String colTitle;
	private int colId;
	private String itemtypes;
	private String itemcode;
	private int itemID;
	
	public String getChangepassword() {
		return changepassword;
	}	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setChangepassword(String changepassword) {
		this.changepassword = changepassword;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public int getCollectionid() {
		return collectionid;
	}
	public void setCollectionid(int collectionid) {
		this.collectionid = collectionid;
	}
	public String getColTitle() {
		return colTitle;
	}
	public void setColTitle(String colTitle) {
		this.colTitle = colTitle;
	}
	public int getColId() {
		return colId;
	}
	public void setColId(int colId) {
		this.colId = colId;
	}
	public String getItemtypes() {
		return itemtypes;
	}
	public void setItemtypes(String itemtypes) {
		this.itemtypes = itemtypes;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	
	
	
}