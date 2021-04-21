package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import beans.BookFramework;
import beans.Data;

public class SingleBiblioDataDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	String timestamp = "yyyy-MM-dd HH:mm:ss";
	String datecreated = "yyyy-MM-dd";
	SimpleDateFormat timeStamp = new SimpleDateFormat(timestamp);
	SimpleDateFormat dateCreated = new SimpleDateFormat(datecreated);

	// String date = simpleDateFormat.format(new Date());

	public void saveSingleBiblioData(List<BookFramework> listFramework) {
		insertBiblio(listFramework);
		insertBiblioItems(listFramework);
	}

	private void insertBiblio(List<BookFramework> listFramework) {
		try {
			this.template.batchUpdate(
					"insert into biblio(author,title,notes,timestamp,datecreated,serial,subject,content,summary,status) values (?,?,?,?,?,?,?,?,?,?)",
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setString(1, listFramework.get(i).getAuthor());
							ps.setString(2, listFramework.get(i).getTitle());
							ps.setString(3, listFramework.get(i).getNotes());
							ps.setString(4, timeStamp.format(new Date()));
							ps.setString(5, dateCreated.format(new Date()));
							ps.setInt(6, 0);
							ps.setString(7, listFramework.get(i).getSubject());
							ps.setString(8, listFramework.get(i).getContent());
							ps.setString(9, listFramework.get(i).getSummary());
							ps.setInt(10, 1);
						}

						public int getBatchSize() {
							return listFramework.size();
						}
					});
		} catch (DuplicateKeyException e) {

		}

	}

	public int biblioLatestinfo() {
		String sql = "SELECT biblionumber FROM biblio order by 1 desc limit 1";
		int id = template.queryForObject(sql, Integer.class);
		return id;
	}

	private void insertBiblioItems(List<BookFramework> listFramework) {
		try {
			this.template.batchUpdate(
					"insert into biblioitems(biblionumber,editionstatement,place,notes,itemtype,publicationyear,isbn,status) values (?,?,?,?,?,?,?,?)",
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, biblioLatestinfo());
							ps.setString(2, listFramework.get(i).getEditionstatement());
							ps.setString(3, listFramework.get(i).getPlace());
							ps.setString(4, listFramework.get(i).getNotes());
							ps.setString(5, listFramework.get(i).getItemtype());
							ps.setString(6, listFramework.get(i).getPublicationyear());
							ps.setString(7, listFramework.get(i).getIsbn());
							ps.setInt(8, 1);
						}

						public int getBatchSize() {
							return listFramework.size();
						}
					});
		} catch (DuplicateKeyException e) {
			// e.printStackTrace();
		}
	}

	public void addItem(Data data) {

		int biblioItemNumber = getBiblioitemNumber(data);
		String insertSql = "insert into items(biblionumber,biblioitemnumber,booksellerid,homebranch,itemcallnumber,barcode,enumchron,timestamp,itype,publisheddate,collection,status) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { data.biblionumber, biblioItemNumber, data.booksellerid, data.homebranch,
				data.itemcallnumber, data.barcode, data.enumchron, timeStamp.format(new Date()), data.itemtype,
				dateCreated.format(new Date()), data.collection, 1 };
		int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.INTEGER };
		this.template.update(insertSql, params, types);
	}

	private int getBiblioitemNumber(Data data) {
		String sql = "SELECT biblioitemnumber FROM biblioitems where biblionumber=" + data.biblionumber + "";
		int id = template.queryForObject(sql, Integer.class);
		return id;
	}

	public List<Data> getItemsbyBiblioData(int biblioLatestinfo) {
		try {
			return this.template.query(
					"SELECT booksellerid,homebranch,itemcallnumber,barcode,itype,publisheddate,collection,itemnumber FROM items where biblionumber="
							+ biblioLatestinfo + "",
					(rs, rowNum) -> new Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<Data>();
	}

	public void deleteItem(int itemID) throws Exception {
		int result = this.template.update("delete from items where itemnumber = ?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, itemID);
			}
		});
		if (result != 1) {
			throw new Exception("Failed to delete");
		}

	}

	public int checkBiblioNumber(int biblioNumber) {
		String sql = "SELECT count(*) FROM biblio WHERE biblionumber =" + biblioNumber + "";
		int count = template.queryForObject(sql, Integer.class);
		return count;

	}

}
