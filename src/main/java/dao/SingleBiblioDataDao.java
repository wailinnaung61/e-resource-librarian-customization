package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import beans.BookFramework;

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
					"insert into biblio(author,title,notes,timestamp,datecreated,serial,subject,content,summary) values (?,?,?,?,?,?,?,?,?)",
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
						}

						public int getBatchSize() {
							return listFramework.size();
						}
					});
		} catch (DuplicateKeyException e) {

		}

	}

	private int biblioLatestinfo() {
		String sql = "SELECT biblionumber FROM biblio order by 1 desc limit 1";
		int id = template.queryForObject(sql, Integer.class);
		return id;
	}

	private void insertBiblioItems(List<BookFramework> listFramework) {
		try {
			this.template.batchUpdate(
					"insert into biblioitems(biblionumber,editionstatement,place,notes,itemtype,publicationyear,isbn) values (?,?,?,?,?,?,?)",
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, biblioLatestinfo());
							ps.setString(2, listFramework.get(i).getEditionstatement());
							ps.setString(3, listFramework.get(i).getPlace());
							ps.setString(4, listFramework.get(i).getNotes());
							ps.setString(5, listFramework.get(i).getItemtype());
							ps.setString(6, listFramework.get(i).getPublicationyear());
							ps.setString(7, listFramework.get(i).getIsbn());
						}

						public int getBatchSize() {
							return listFramework.size();
						}
					});
		} catch (DuplicateKeyException e) {
			// e.printStackTrace();
		}
	}

}
