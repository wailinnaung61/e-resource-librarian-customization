package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import beans.Data;
import beans.ItemTypes;
import beans.UserBean;
import beans.bibliosingledata;

public class UserDao {
	JdbcTemplate template;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int getroleidbyrolename(String rolename) {
		String sql = "SELECT roleid FROM roletb WHERE rolename ='" + rolename + "'";
		int id = template.queryForObject(sql, Integer.class);
		return id;
	}

	public int save(UserBean u) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);

		String rolevalue = u.getRolename();
		int roleid = getroleidbyrolename(rolevalue);

		String password = passwordEncoder.encode(u.getPassword());

		String sql = "INSERT INTO usertb(name, username, email, password, status, roleid, created_at, updated_at) VALUES(?,?,?,?,?,?,?,?)";
		return template.update(sql,
				new Object[] { u.getName(), u.getUsername(), u.getEmail(), password, 1, roleid, time, time });
	}

	public int usernamebyusername(String username) {
		String sql = "SELECT count(*) FROM usertb WHERE username ='" + username + "'";
		int count = template.queryForObject(sql, Integer.class);
		return count;

	}

	public int rolenamebyrolename(String rolename) {
		String sql = "SELECT count(*) FROM roletb WHERE rolename ='" + rolename + "'";
		int count = template.queryForObject(sql, Integer.class);
		return count;

	}

	public int update(UserBean u) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);

		int roleid = getroleidbyrolename(u.getRolename());

		String sql = "UPDATE usertb SET name='" + u.getName() + "',username='" + u.getUsername() + "',email='"
				+ u.getEmail() + "',roleid=" + roleid + ",updated_at='" + time + "' WHERE id=" + u.getId() + "";

		return template.update(sql);
	}

	public int updaterole(UserBean u) {
		String roleCode = "ROLE_" + u.getRolename();
		String sql = "UPDATE roletb SET rolename = ?, rolecode = ? WHERE roleid = ?";

		return template.update(sql, new Object[] { u.getRolename(), roleCode, u.getRoleid() });
	}

	public int delete(int id) {
		String sql = "delete from usertb where id=" + id + "";
		return template.update(sql);
	}

	public UserBean getUserById(int id) {
		String sql = "select u.id,u.name,u.username,u.email,u.password,r.rolename from usertb u,roletb r where u.roleid=r.roleid and u.id=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<UserBean>(UserBean.class));
	}

	public UserBean getRoleById(int id) {
		String sql = "select roleid,rolename from roletb where roleid=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<UserBean>(UserBean.class));
	}

	public List<UserBean> getUsers() {
		return template.query(
				"select u.id,u.name,u.username,u.email,u.password,u.status,r.rolename,u.created_at,u.updated_at   from usertb u,roletb r where u.roleid=r.roleid",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean e = new UserBean();
						e.setId(rs.getInt(1));
						e.setName(rs.getString(2));
						e.setUsername(rs.getString(3));
						e.setEmail(rs.getString(4));
						e.setPassword(rs.getString(5));
						e.setStatus(rs.getInt(6));
						e.setRolename(rs.getString(7));
						e.setCreated(rs.getString(8));
						e.setUpdated(rs.getString(9));
						return e;
					}
				});
	}

	public List<UserBean> checkuser(UserBean u) {

		/*
		 * Md5PasswordEncoder encoderMD5 = new Md5PasswordEncoder(); String password =
		 * encoderMD5.encodePassword(u.getPassword(), null);
		 * 
		 * String
		 * sql="SELECT u.username,u.password,r.rolename FROM usertb u,roletb r WHERE u.username ='"
		 * +u.getUsername()+"' and u.password='"+password+"' and u.roleid=r.roleid";
		 * 
		 * return template.query(sql,new RowMapper<UserBean>(){ public UserBean
		 * mapRow(ResultSet rs, int row) throws SQLException { UserBean e=new
		 * UserBean(); e.setUsername(rs.getString(1)); e.setPassword(rs.getString(2));
		 * e.setRolename(rs.getString(3));
		 * 
		 * return e; } });
		 */
		return null;
	}

	public List<UserBean> getRoles() {
		return template.query("select * from roletb", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean e = new UserBean();
				e.setRoleid(rs.getInt(1));
				e.setRolename(rs.getString(2));
				return e;
			}
		});
	}

	public List<UserBean> getRolename() {
		return template.query("select rolename from roletb", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean e = new UserBean();
				e.setRolename(rs.getString(1));
				return e;
			}
		});
	}

	public int saverole(UserBean u) {
		String roleCode = "ROLE_" + u.getRolename();
		String sql = "INSERT INTO roletb(rolename, rolecode) VALUES(?, ?)";

		return template.update(sql, new Object[] { u.getRolename(), roleCode });
	}

	public int deleterole(int roleid) {
		String sql = "delete from roletb where roleid=" + roleid + "";
		return template.update(sql);
	}

	public int updatepassword(String name, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		String sql = "UPDATE usertb SET password='" + encodedPassword + "' WHERE username='" + name + "'";

		return template.update(sql);
	}

	public String saveexceldata(ArrayList<Data> list) {
		for (Data u : list) {

			// for items
			String sql3 = "select count(*)  from items where itemnumber=" + u.getItemnumber();
			int item = template.queryForObject(sql3, Integer.class);

			if (item <= 0) {
				String biblio = "Insert into items(itemnumber,biblionumber,biblioitemnumber,booksellerid,homebranch,itemcallnumber,keyword,barcode,enumchron)"
						+ "values(" + u.getItemnumber() + "," + u.getBiblionumber() + "," + u.getBiblioitemnumber()
						+ ",'" + u.getBooksellerid() + "','" + u.getHomebranch() + "','" + u.getItemcallnumber() + "','"
						+ u.getKeyword() + "','" + u.getBarcode() + "','" + u.getEnumchron() + "')";
				template.update(biblio);

				// for biblio
				String sql = "select count(*)  from biblio where biblionumber=" + u.getBiblionumber();
				int rownum = template.queryForObject(sql, Integer.class);

				if (rownum <= 0) {
					String sql2 = "Insert into biblio(biblionumber,author,title,notes,timestamp,datecreated,serial)values("
							+ u.getBiblionumber() + "," + "'" + u.getAuthorname() + "','" + u.getTitle() + "','"
							+ u.getNotes() + "','" + u.getTimestamp() + "','" + u.getDatecreated() + "',"
							+ u.getSerial() + ")";
					template.update(sql2);

				}

				// for biblioitems
				String sql1 = "select count(*)  from biblioitems where biblioitemnumber=" + u.getBiblioitemnumber();
				int biblioitem = template.queryForObject(sql1, Integer.class);

				if (biblioitem <= 0) {

					String biblioitems = "Insert into biblioitems(biblionumber,biblioitemnumber,collectiontitle,publishercode,editionstatement,place,notes,itemtype,publicationyear,isbn)"
							+ "values(" + u.getBiblionumber() + "," + u.getBiblioitemnumber() + ",'" + u.getCollection()
							+ "','" + u.getPublishercode() + "','" + u.getEditionstatement() + "','" + u.getPlace()
							+ "'," + "'" + u.getNotes() + "','" + u.getItemtype() + "','" + u.getPublicationyear()
							+ "','" + u.getIsbn() + "') ";
					template.update(biblioitems);

				}

			} else {
				return "Duplicate Itemnumber.Please! Check Your Biblio Data";
			}

		}
		return null;

	}

//not use now i will show from list
	public List<Data> getDatas() {

		return template.query(
				"select items.itemnumber,biblio.title,biblio.author,items.accesslevel,items.downloadable,biblioitems.collectiontitle from biblio,items,biblioitems where biblio.biblionumber=items.biblionumber and biblio.biblionumber=biblioitems.biblionumber  ORDER BY biblio.timestamp DESC",
				new RowMapper<Data>() {

					int number = 1;

					public Data mapRow(ResultSet rs, int row) throws SQLException {
						Data e = new Data();
						e.setItemnumber(rs.getInt(1));
						e.setTitle(rs.getString(2));
						e.setAuthorname(rs.getString(3));
						e.setAccesslevel(rs.getInt(4));
						e.setDownloadable(rs.getInt(5));
						e.setCollection(rs.getString(6));
						e.setNumber(number);
						number++;
						return e;
					}
				});
	}

	public int updatedownload(Integer downloadable, Integer itemnumber) {

		String sql = "UPDATE items SET downloadable=" + downloadable + " WHERE itemnumber=" + itemnumber + "";

		return template.update(sql);
	}

	public int updateradio(int itemnumber, int i) {
		String sql = "UPDATE items SET accesslevel=" + i + " WHERE itemnumber=" + itemnumber + "";
		return template.update(sql);
	}

	public int savePDFinformation(String pdffilelink, int accesspage, int itemnumber) {
		String sql = "UPDATE items SET accesspages=" + accesspage + ",pdfresource='" + pdffilelink
				+ "' WHERE itemnumber='" + itemnumber + "'";

		return template.update(sql);
	}

	public List<Data> getDatasbyauthor(String authortitle) {
		String sql = "select items.itemnumber,biblio.title,biblio.author,items.accesslevel,items.downloadable,biblioitems.collectiontitle,biblioitems.itemtype from biblio,items,biblioitems where CONCAT(author,'')LIKE '"
				+ authortitle
				+ "%' and biblio.biblionumber=items.biblionumber and biblio.biblionumber=biblioitems.biblionumber ORDER BY biblio.timestamp DESC";
		return template.query(sql, new RowMapper<Data>() {
			int number = 1;

			public Data mapRow(ResultSet rs, int row) throws SQLException {
				Data e = new Data();
				e.setItemnumber(rs.getInt(1));
				e.setTitle(rs.getString(2));
				e.setAuthorname(rs.getString(3));
				e.setAccesslevel(rs.getInt(4));
				e.setDownloadable(rs.getInt(5));
				e.setCollection(rs.getString(6));
				e.setItemtype(rs.getString(7));
				e.setNumber(number);
				number++;
				return e;
			}
		});
	}

	public List<Data> getDatasbytitle(String authortitle) {
		return template.query(
				"select items.itemnumber,biblio.title,biblio.author,items.accesslevel,items.downloadable,biblioitems.collectiontitle,biblioitems.itemtype from biblio,items,biblioitems where CONCAT(title,'')LIKE '"
						+ authortitle
						+ "%' and biblio.biblionumber=items.biblionumber and biblio.biblionumber=biblioitems.biblionumber ORDER BY biblio.timestamp DESC",
				new RowMapper<Data>() {

					int number = 1;

					public Data mapRow(ResultSet rs, int row) throws SQLException {
						Data e = new Data();
						e.setItemnumber(rs.getInt(1));
						e.setTitle(rs.getString(2));
						e.setAuthorname(rs.getString(3));
						e.setAccesslevel(rs.getInt(4));
						e.setDownloadable(rs.getInt(5));
						e.setCollection(rs.getString(6));
						e.setItemtype(rs.getString(7));
						e.setNumber(number);
						number++;
						return e;
					}
				});
	}

	public String getemailbyusernameforforgotpassword(String username) {
		String sql = "SELECT email FROM usertb WHERE username ='" + username + "'";
		List<String> count = template.queryForList(sql, String.class);

		if (count.isEmpty()) {
			return null;
		} else {
			return count.get(0);
		}
	}

	public int resetchangepassword(String changepassword, String emailToRecipient) {
		String sql = "UPDATE usertb SET password='" + changepassword + "' WHERE email='" + emailToRecipient + "'";

		return template.update(sql);
	}

	public int saveimageurl(int itemnumber, String imagePath) {
		String sql = "UPDATE items SET bookcover='" + imagePath + "' WHERE itemnumber=" + itemnumber + "";

		return template.update(sql);

	}

	public List<Data> getDatasincludecollection() {
		return template.query(
				"select items.itemnumber, biblio.title,biblio.author,items.accesslevel,items.downloadable,biblioitems.collectiontitle,biblioitems.itemtype, biblio.biblionumber from biblio,items,biblioitems where biblio.biblionumber=items.biblionumber and biblio.biblionumber=biblioitems.biblionumber ORDER BY items.itemnumber DESC limit 20",
				new RowMapper<Data>() {
					int number = 1;

					public Data mapRow(ResultSet rs, int row) throws SQLException {
						Data e = new Data();
						e.setItemnumber(rs.getInt(1));
						e.setTitle(rs.getString(2));
						e.setAuthorname(rs.getString(3));
						e.setAccesslevel(rs.getInt(4));
						e.setDownloadable(rs.getInt(5));
						e.setCollection(rs.getString(6));
						e.setItemtype(rs.getString(7));
						e.setBiblionumber(rs.getInt(8));
						e.setNumber(number);
						number++;
						return e;
					}
				});
	}

	public List<Data> getDatasbyitemtypeandcollection(String itemtype, String Collection) {
		return template.query(
				"select items.itemnumber,biblio.title,biblio.author,items.accesslevel,items.downloadable,biblioitems.collectiontitle,biblioitems.itemtype from biblio,items,biblioitems where biblioitems.collectiontitle='"
						+ Collection + "' and biblioitems.itemtype='" + itemtype
						+ "' and biblio.biblionumber=items.biblionumber and biblio.biblionumber=biblioitems.biblionumber ORDER BY biblio.timestamp DESC",
				new RowMapper<Data>() {

					int number = 1;

					public Data mapRow(ResultSet rs, int row) throws SQLException {
						Data e = new Data();
						e.setItemnumber(rs.getInt(1));
						e.setTitle(rs.getString(2));
						e.setAuthorname(rs.getString(3));
						e.setAccesslevel(rs.getInt(4));
						e.setDownloadable(rs.getInt(5));
						e.setCollection(rs.getString(6));
						e.setItemtype(rs.getString(7));
						e.setNumber(number);
						number++;
						return e;
					}
				});
	}

	public List<Data> getDatasbycollection(String collection) {
		return template.query(
				"select items.itemnumber,biblio.title,biblio.author,items.accesslevel,items.downloadable,biblioitems.collectiontitle,biblioitems.itemtype from biblio,items,biblioitems where biblioitems.collectiontitle='"
						+ collection
						+ "' and biblio.biblionumber=items.biblionumber and biblio.biblionumber=biblioitems.biblionumber ORDER BY biblio.timestamp DESC",
				new RowMapper<Data>() {

					int number = 1;

					public Data mapRow(ResultSet rs, int row) throws SQLException {
						Data e = new Data();
						e.setItemnumber(rs.getInt(1));
						e.setTitle(rs.getString(2));
						e.setAuthorname(rs.getString(3));
						e.setAccesslevel(rs.getInt(4));
						e.setDownloadable(rs.getInt(5));
						e.setCollection(rs.getString(6));
						e.setItemtype(rs.getString(7));
						e.setNumber(number);
						number++;
						return e;
					}
				});
	}

	public List<Data> getDatasbyitemtype(String itemtype) {
		return template.query(
				"select items.itemnumber,biblio.title,biblio.author,items.accesslevel,items.downloadable,biblioitems.collectiontitle,biblioitems.itemtype from biblio,items,biblioitems where biblioitems.itemtype='"
						+ itemtype
						+ "' and biblio.biblionumber=items.biblionumber and biblio.biblionumber=biblioitems.biblionumber ORDER BY biblio.timestamp DESC",
				new RowMapper<Data>() {

					int number = 1;

					public Data mapRow(ResultSet rs, int row) throws SQLException {
						Data e = new Data();
						e.setItemnumber(rs.getInt(1));
						e.setTitle(rs.getString(2));
						e.setAuthorname(rs.getString(3));
						e.setAccesslevel(rs.getInt(4));
						e.setDownloadable(rs.getInt(5));
						e.setCollection(rs.getString(6));
						e.setItemtype(rs.getString(7));
						e.setNumber(number);
						number++;
						return e;
					}
				});
	}

	public int collectionbycollection(String collection) {
		String sql = "SELECT count(*) FROM collections WHERE colTitle ='" + collection + "'";
		int count = template.queryForObject(sql, Integer.class);
		return count;
	}

	public int savecollection(UserBean user) {
		String sql = "INSERT INTO collections(colTitle) VALUES('" + user.getCollection() + "')";

		return template.update(sql);

	}

	public List<UserBean> getCollections() {
		return template.query("select * from collections", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean e = new UserBean();
				e.setCollectionid(rs.getInt(1));
				e.setCollection(rs.getString(2));
				return e;
			}
		});
	}

	public int deletecollection(int collectionid) {
		String sql = "delete from collections where colId=" + collectionid + "";
		return template.update(sql);

	}

	public UserBean getCollectionById(int id) {
		String sql = "select colId,colTitle from collections where colId=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<UserBean>(UserBean.class));
	}

	public int updatecollection(UserBean u) {
		String sql = "UPDATE collections SET colTitle='" + u.getColTitle() + "' WHERE colId=" + u.getColId() + "";

		return template.update(sql);

	}

	public List<UserBean> getCollectionname() {
		return template.query("select colTitle from collections", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean e = new UserBean();
				e.setColTitle(rs.getString(1));
				return e;
			}
		});
	}

	public List<UserBean> getitemtypesnameandcode() {
		return template.query("select itemtypes,itemcode from itemtypes", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean e = new UserBean();
				e.setItemtypes(rs.getString(1));
				e.setItemcode(rs.getString(2));
				return e;
			}
		});
	}

	public String savesingledata(bibliosingledata u) {
		// for items
		String sql3 = "select count(*)  from items where itemnumber=" + u.getItemnumber();
		int item = template.queryForObject(sql3, Integer.class);

		if (item <= 0) {
			String biblio = "Insert into items(itemnumber,biblionumber,biblioitemnumber,booksellerid,homebranch,itemcallnumber,keyword,barcode)"
					+ "values(" + u.getItemnumber() + "," + u.getBiblionumber() + "," + u.getBiblioitemnumber() + ",'"
					+ u.getBooksellerid() + "','" + u.getHomebranch() + "','" + u.getItemcallnumber() + "','"
					+ u.getKeyword() + "','" + u.getBarcode() + "')";
			template.update(biblio);

			// for biblio
			String sql = "select count(*)  from biblio where biblionumber=" + u.getBiblionumber();
			int rownum = template.queryForObject(sql, Integer.class);

			if (rownum <= 0) {
				String sql2 = "Insert into biblio(biblionumber,author,title,notes,timestamp,datecreated)values("
						+ u.getBiblionumber() + "," + "'" + u.getAuthor() + "','" + u.getTitle() + "','" + u.getNotes()
						+ "','" + u.getTimestamp() + "','" + u.getDatecreated() + "')";
				template.update(sql2);

			} else if (rownum >= 0) {
				String sql2 = "Update biblio set author='" + u.getAuthor() + "',title='" + u.getTitle() + "',notes='"
						+ u.getNotes() + "',timestamp='" + u.getTimestamp() + "',datecreated='" + u.getDatecreated()
						+ "' where biblionumber=" + u.getBiblionumber() + "";
				template.update(sql2);
			}

			// for biblioitems
			String sql1 = "select count(*)  from biblioitems where biblioitemnumber=" + u.getBiblioitemnumber();
			int biblioitem = template.queryForObject(sql1, Integer.class);

			if (biblioitem <= 0) {

				String biblioitems = "Insert into biblioitems(biblionumber,biblioitemnumber,collectiontitle,publishercode,editionstatement,place,notes,itemtype,publicationyear,isbn)"
						+ "values(" + u.getBiblionumber() + "," + u.getBiblioitemnumber() + ",'"
						+ u.getCollectiontitle() + "','" + u.getPublishercode() + "','" + u.getEditionstatement()
						+ "','" + u.getPlace() + "'," + "'" + u.getNotes() + "','" + u.getItemtype() + "','"
						+ u.getPublishedyear() + "','" + u.getIsbn() + "') ";
				template.update(biblioitems);

			} else if (biblioitem >= 0) {
				String biblioitems = "update biblioitems set collectiontitle='" + u.getCollectiontitle()
						+ "',publishercode='" + u.getPublishercode() + "',editionstatement='" + u.getEditionstatement()
						+ "',place='" + u.getPlace() + "',notes='" + u.getNotes() + "',itemtype='" + u.getItemtype()
						+ "',publicationyear='" + u.getPublishedyear() + "',isbn='" + u.getIsbn()
						+ "' where biblionumber=" + u.getBiblionumber() + " and biblioitemnumber="
						+ u.getBiblioitemnumber() + "";
				template.update(biblioitems);
			}

		} else {
			return "Duplicate Itemnumber.Please! Check Your Biblio Data";
		}

		return null;

	}

	public List<bibliosingledata> getsinglebibliodata() {

		return template.query("select i.itemnumber, i.biblionumber, i.biblioitemnumber, i.barcode,"
				+ " i.booksellerid, i.homebranch, i.itemcallnumber,"
				+ " bi.collectiontitle, bi.publishercode, bi.editionstatement,"
				+ " bi.place, b.author, b.title, b.notes, b.timestamp,"
				+ " b.datecreated, i.keyword, i.itype as itemtype, bi.publicationyear, bi.isbn "
				+ " from biblio b inner join items i on i.biblionumber = b.biblionumber"
				+ " left outer join biblioitems bi on bi.biblionumber = b.biblionumber" + " ORDER BY b.timestamp DESC",
				new RowMapper<bibliosingledata>() {
					public bibliosingledata mapRow(ResultSet rs, int row) throws SQLException {
						bibliosingledata e = new bibliosingledata();
						e.setItemnumber(rs.getInt(1));
						e.setBiblionumber(rs.getInt(2));
						e.setBiblioitemnumber(rs.getInt(3));
						e.setBarcode(rs.getString(4));
						e.setBooksellerid(rs.getString(5));
						e.setHomebranch(rs.getString(6));
						e.setItemcallnumber(rs.getString(7));
						e.setCollectiontitle(rs.getString(8));
						e.setPublishercode(rs.getString(9));
						e.setEditionstatement(rs.getString(10));
						e.setPlace(rs.getString(11));
						e.setAuthor(rs.getString(12));
						e.setTitle(rs.getString(13));
						e.setNotes(rs.getString(14));
						e.setTimestamp(rs.getString(15));
						e.setDatecreated(rs.getString(16));
						e.setKeyword(rs.getString(17));
						e.setItemtype(rs.getString(18));
						e.setPublishedyear(rs.getString(19));
						e.setIsbn(rs.getString(20));
						return e;
					}
				});
	}

	public int checkitemnumber(bibliosingledata user) {
		String sql1 = "select count(*)  from items where itemnumber=" + user.getItemnumber();
		int biblioitem = template.queryForObject(sql1, Integer.class);
		return biblioitem;
	}

	public int deleteupdatebibliolistitems(int itemnumber) {
		String sql = "delete from items where itemnumber=" + itemnumber;
		return template.update(sql);

	}

	public int deleteupdatebibliolistbiblioitems(int biblioitemnumber) {
		String sql = "delete from biblioitems where biblioitemnumber=" + biblioitemnumber;
		return template.update(sql);

	}

	public int deleteupdatebibliolistbibliobiblio(int biblionumber) {
		String sql = "delete from biblio where biblionumber=" + biblionumber;
		return template.update(sql);

	}

	public bibliosingledata getRolebibliolist(int itemnumber) {
		String sql = "select items.itemnumber,items.biblionumber,items.biblioitemnumber,items.barcode,items.booksellerid,items.homebranch,items.itemcallnumber,biblioitems.collectiontitle,biblioitems.publishercode,biblioitems.editionstatement,biblioitems.place,biblio.author,biblio.title,biblio.notes,biblio.timestamp,biblio.datecreated,items.keyword,biblioitems.itemtype,biblioitems.publicationyear,biblioitems.isbn from biblio,items,biblioitems where items.itemnumber=? and  items.biblionumber=biblio.biblionumber and items.biblioitemnumber=biblioitems.biblioitemnumber";
		return template.queryForObject(sql, new Object[] { itemnumber },
				new BeanPropertyRowMapper<bibliosingledata>(bibliosingledata.class));
	}

	public void updatesinglebibliodata(bibliosingledata u) {

		String items = "update items set biblionumber=" + u.getBiblionumber() + ",items.biblioitemnumber='"
				+ u.getBiblioitemnumber() + "',booksellerid='" + u.getBooksellerid() + "',homebranch='"
				+ u.getHomebranch() + "',itemcallnumber='" + u.getItemcallnumber() + "',keyword='" + u.getKeyword()
				+ "',barcode='" + u.getBarcode() + "' where itemnumber=" + u.getItemnumber() + "";
		template.update(items);

		String sql = "select count(*)  from biblio where biblionumber=" + u.getBiblionumber();
		int rownum = template.queryForObject(sql, Integer.class);

		if (rownum <= 0) {
			String sql2 = "Insert into biblio(biblionumber,author,title,notes,timestamp,datecreated)values("
					+ u.getBiblionumber() + "," + "'" + u.getAuthor() + "','" + u.getTitle() + "','" + u.getNotes()
					+ "','" + u.getTimestamp() + "','" + u.getDatecreated() + "')";
			template.update(sql2);

		} else if (rownum >= 0) {
			String biblio = "update biblio set author='" + u.getAuthor() + "',title='" + u.getTitle() + "',notes='"
					+ u.getNotes() + "',timestamp='" + u.getTimestamp() + "',datecreated='" + u.getDatecreated()
					+ "' where biblionumber=" + u.getBiblionumber() + "";
			template.update(biblio);
		}

		String sql1 = "select count(*)  from biblioitems where biblioitemnumber=" + u.getBiblioitemnumber();
		int biblioitem = template.queryForObject(sql1, Integer.class);

		if (biblioitem <= 0) {

			String biblioitems = "Insert into biblioitems(biblionumber,biblioitemnumber,collectiontitle,publishercode,editionstatement,place,notes,itemtype,publicationyear,isbn)"
					+ "values(" + u.getBiblionumber() + "," + u.getBiblioitemnumber() + ",'" + u.getCollectiontitle()
					+ "','" + u.getPublishercode() + "','" + u.getEditionstatement() + "','" + u.getPlace() + "'," + "'"
					+ u.getNotes() + "','" + u.getItemtype() + "','" + u.getPublishedyear() + "','" + u.getIsbn()
					+ "') ";
			template.update(biblioitems);

		} else if (biblioitem >= 0) {
			String biblioitems = "update biblioitems set collectiontitle='" + u.getCollectiontitle()
					+ "',publishercode='" + u.getPublishercode() + "',editionstatement='" + u.getEditionstatement()
					+ "',place='" + u.getPlace() + "',notes='" + u.getNotes() + "',itemtype='" + u.getItemtype()
					+ "',publicationyear='" + u.getPublicationyear() + "',isbn='" + u.getIsbn()
					+ "' where biblionumber=" + u.getBiblionumber() + " and biblioitemnumber=" + u.getBiblioitemnumber()
					+ "";
			template.update(biblioitems);
		}

	}

	public List<Data> seriallistview() {
		return template.query(
				"SELECT biblio.biblionumber,biblio.title,count(items.itemnumber) from biblio left join items on items.biblionumber = biblio.biblionumber where biblio.serial = 1 group by biblio.biblionumber;",
				new RowMapper<Data>() {

					public Data mapRow(ResultSet rs, int row) throws SQLException {
						Data e = new Data();
						e.setBiblionumber(rs.getInt(1));
						e.setTitle(rs.getString(2));
						e.setItemcount(rs.getInt(3));

						return e;
					}
				});
	}

	public List<Data> seriallistdetail(int biblionumber) {
		return template.query(
				"select items.itemnumber,items.biblionumber,biblio.title,items.enumchron from items,biblio where biblio.biblionumber=items.biblionumber and items.biblionumber = "
						+ biblionumber + ";",
				new RowMapper<Data>() {

					public Data mapRow(ResultSet rs, int row) throws SQLException {
						Data e = new Data();
						e.setItemnumber(rs.getInt(1));
						e.setBiblionumber(rs.getInt(2));
						e.setTitle(rs.getString(3));
						e.setEnumchron(rs.getString(4));
						return e;
					}
				});
	}

	public UserDetails getUserByUsername(String username) {
		User user = template.queryForObject(
				"SELECT u.username, u.password, r.rolecode, u.status"
						+ " FROM usertb u INNER JOIN roletb r ON r.roleid = u.roleid WHERE username = ?",
				new Object[] { username }, (rs, rowNum) -> {
					String name = rs.getString("username");
					String pass = rs.getString("password");
					String role = rs.getString("rolecode");
					boolean status = rs.getBoolean("status");
					User u = new User(name, pass, status, status, status, status,
							Collections.singletonList(new SimpleGrantedAuthority(role)));
					return u;
				});
		return user;
	}
	/*
	 * Md5PasswordEncoder encoderMD5 = new Md5PasswordEncoder(); String password =
	 * encoderMD5.encodePassword(u.getPassword(), null);
	 * 
	 * String
	 * sql="SELECT u.username,u.password,r.rolename FROM usertb u,roletb r WHERE u.username ='"
	 * +u.getUsername()+"' and u.password='"+password+"' and u.roleid=r.roleid";
	 * 
	 * return template.query(sql,new RowMapper<User>(){ public User mapRow(ResultSet
	 * rs, int row) throws SQLException { User e=new User();
	 * e.setUsername(rs.getString(1)); e.setPassword(rs.getString(2));
	 * e.setRolename(rs.getString(3));
	 * 
	 * return e; } });
	 */

	public int oldpassword(String name, String input) {
		String sql = "SELECT count(*) FROM usertb WHERE username ='" + name + "' and password='" + input + "'";
		int count = template.queryForObject(sql, Integer.class);
		return count;
	}

	public void CheckOldPass(String username, String password) throws Exception {
		String encodedPassword = this.template.queryForObject("SELECT password FROM usertb WHERE username = ?",
				new Object[] { username }, String.class);
		if (!passwordEncoder.matches(password, encodedPassword)) {
			throw new Exception("Username and password does not match!");
		}

	}


}