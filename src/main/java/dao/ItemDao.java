package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import beans.Collection;
import beans.Data;
import beans.Item;
import beans.ItemTypes;
import beans.Members;
import beans.Patron;
import beans.ReadingHistory;
import beans.SpecialRequest;
import beans.SubItem;
import beans.SyncData;

@Repository
public class ItemDao {

	private Logger logger = LogManager.getLogger(this.getClass());

	JdbcTemplate template;

	public void InsertBiblio(final List<SyncData> biblio) {
		try {
			this.template.batchUpdate(
					"insert into biblio(biblionumber,author,title,notes,timestamp,datecreated,serial,subject,content,summary) values (?,?,?,?,?,?,?,?,?,?)",
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, biblio.get(i).getBiblionumber());
							ps.setString(2, biblio.get(i).getAuthorname());
							ps.setString(3, biblio.get(i).getTitle());
							ps.setString(4, biblio.get(i).getNotes());
							ps.setString(5, biblio.get(i).getTimestamp());
							ps.setString(6, biblio.get(i).getDatecreated());
							ps.setInt(7, biblio.get(i).getSerial());
							ps.setString(8, biblio.get(i).getSubject());
							ps.setString(9, biblio.get(i).getContent());
							ps.setString(10, biblio.get(i).getSummary());
						}

						public int getBatchSize() {
							return biblio.size();
						}
					});
		} catch (DuplicateKeyException e) {
			// e.printStackTrace();
		}
	}

	public void InsertBiblioItems(final List<SyncData> bitems) {
		try {
			this.template.batchUpdate(
					"insert into biblioitems (biblionumber,biblioitemnumber,collectiontitle,publishercode,editionstatement,place,notes,itemtype,publicationyear,isbn) values (?,?,?,?,?,?,?,?,?,?)",
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, bitems.get(i).getBiblionumber());
							ps.setInt(2, bitems.get(i).getBiblioitemnumber());
							ps.setString(3, bitems.get(i).getCollection());
							ps.setString(4, bitems.get(i).getPublishercode());
							ps.setString(5, bitems.get(i).getEditionstatement());
							ps.setString(6, bitems.get(i).getPlace());
							ps.setString(7, bitems.get(i).getBi_notes());
							ps.setString(8, bitems.get(i).getItemtype());
							ps.setString(9, bitems.get(i).getPublicationyear());
							ps.setString(10, bitems.get(i).getIsbn());
						}

						public int getBatchSize() {
							return bitems.size();
						}
					});
		} catch (DuplicateKeyException e) {
			// e.printStackTrace();
		}
	}

	public void InsertItems(final List<SyncData> items) {
		try {
			this.template.batchUpdate(
					"insert into items (itemnumber, biblionumber, biblioitemnumber, booksellerid, homebranch, itemcallnumber, keyword, barcode, itype, enumchron, publisheddate, collection) values (?,?,?,?,?,?,?,?,?,?,?,?)",
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, items.get(i).getItemnumber());
							ps.setInt(2, items.get(i).getBiblionumber());
							ps.setInt(3, items.get(i).getBiblioitemnumber());
							ps.setString(4, items.get(i).getBooksellerid());
							ps.setString(5, items.get(i).getHomebranch());
							ps.setString(6, items.get(i).getItemcallnumber());
							ps.setString(7, items.get(i).getKeyword());
							ps.setString(8, items.get(i).getBarcode());
							ps.setString(9, items.get(i).getItemtype());
							ps.setString(10, items.get(i).getEnumchron());
							Date d = items.get(i).getPublisheddate();
							ps.setDate(11, (d == null ? null : new java.sql.Date(d.getTime())));
							ps.setString(12, items.get(i).getI_collection());
						}

						public int getBatchSize() {
							return items.size();
						}
					});
		} catch (DuplicateKeyException e) {
			// e.printStackTrace();
		}
	}

	public void UpdateItems(final List<SyncData> items) {
		this.template.batchUpdate(
				"update items set biblionumber = ? ,biblioitemnumber = ? ,booksellerid = ? ,homebranch = ? ,itemcallnumber = ? ,keyword = ? ,barcode = ?, itype=?, publisheddate = ?, collection = ? where itemnumber = ?",
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setInt(1, items.get(i).getBiblionumber());
						ps.setInt(2, items.get(i).getBiblioitemnumber());
						ps.setString(3, items.get(i).getBooksellerid());
						ps.setString(4, items.get(i).getHomebranch());
						ps.setString(5, items.get(i).getItemcallnumber());
						ps.setString(6, items.get(i).getKeyword());
						ps.setString(7, items.get(i).getBarcode());
						ps.setString(8, items.get(i).getItemtype());
						Date d = items.get(i).getPublisheddate();
						ps.setDate(9, (d == null ? null : new java.sql.Date(d.getTime())));
						ps.setString(10, items.get(i).getI_collection());
						ps.setInt(11, items.get(i).getItemnumber());
					}

					public int getBatchSize() {
						return items.size();
					}
				});
		// inserting unsynced items
		InsertItems(items);
	}

	public void UpdateBiblioItems(final List<SyncData> bitems) {
		this.template.batchUpdate(
				"update biblioitems set biblionumber = ? ,collectiontitle = ? ,publishercode = ? ,editionstatement = ? ,place = ? ,notes = ? ,itemtype = ? ,publicationyear = ? ,isbn = ? where biblioitemnumber = ?",
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setInt(1, bitems.get(i).getBiblionumber());
						ps.setString(2, bitems.get(i).getCollection());
						ps.setString(3, bitems.get(i).getPublishercode());
						ps.setString(4, bitems.get(i).getEditionstatement());
						ps.setString(5, bitems.get(i).getPlace());
						ps.setString(6, bitems.get(i).getBi_notes());
						ps.setString(7, bitems.get(i).getItemtype());
						ps.setString(8, bitems.get(i).getPublicationyear());
						ps.setString(9, bitems.get(i).getIsbn());
						ps.setInt(10, bitems.get(i).getBiblioitemnumber());
					}

					public int getBatchSize() {
						return bitems.size();
					}
				});
		InsertBiblioItems(bitems);
	}

	public void UpdateBiblio(final List<SyncData> biblio) {
		this.template.batchUpdate(
				"update biblio set author = ? ,title = ? ,notes = ? ,timestamp = ? ,datecreated = ? ,serial = ?, subject = ?, content = ?, summary = ? where biblionumber = ?",
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(2, biblio.get(i).getTitle());
						ps.setString(1, biblio.get(i).getAuthorname());
						ps.setString(3, biblio.get(i).getNotes());
						ps.setString(4, biblio.get(i).getTimestamp());
						ps.setString(5, biblio.get(i).getDatecreated());
						ps.setInt(6, biblio.get(i).getSerial());
						ps.setString(7, biblio.get(i).getSubject());
						ps.setString(8, biblio.get(i).getContent());
						ps.setString(9, biblio.get(i).getSummary());
						ps.setInt(10, biblio.get(i).getBiblionumber());
					}

					public int getBatchSize() {
						return biblio.size();
					}
				});
		InsertBiblio(biblio);
	}

	public Data getLatestItemInfo() {
		List<Data> dataList = this.template.query("select itemnumber from items order by 1 desc limit 1",
				new RowMapper<Data>() {
					public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
						Data d = new Data();
						d.setItemnumber(rs.getInt("itemnumber"));
						return d;
					}
				});
		return dataList != null && dataList.size() > 0 ? dataList.get(0) : new Data();
	}

	public Data getLatestSyncItemInfo() {
		List<Data> dataList = this.template.query("select itemnumber from items where status="+0+" order by 1 desc limit 1",
				new RowMapper<Data>() {
					public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
						Data d = new Data();
						d.setItemnumber(rs.getInt("itemnumber"));
						return d;
					}
				});
		return dataList != null && dataList.size() > 0 ? dataList.get(0) : new Data();
	}

	public void InsertResourceItem(SubItem item) {
		String insertSql = "insert into subitems(accesslevel, accesspages, resourceurl, downloadable, itemnumber) values(?,?,?,?,?)";
		Object[] params = new Object[] { item.accesslevel, item.accesspages, item.resourceurl, item.downloadable,
				item.itemnumber };
		int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.BIT, Types.INTEGER };
		this.template.update(insertSql, params, types);
	}

	public void UpdateResourceItem(SubItem item) {
		String insertSql = "update subitems set accesslevel = ? , accesspages = ?, resourceurl = ?, downloadable = ? where resourceId = ?";
		Object[] params = new Object[] { item.accesslevel, item.accesspages, item.resourceurl, item.downloadable,
				item.resourceId };
		int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.BIT, Types.INTEGER };
		this.template.update(insertSql, params, types);
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Data> GetBiblioItem(String type, int id) {
		String sql = "select b.biblionumber, bi.biblioitemnumber, i.itemnumber, i.keyword, s.resourceid, s.downloadable,"
				+ " s.resourceurl, s.accesslevel, s.accesspages, i.bookcover, b.title, b.author, i.barcode, i.itemcallnumber, i.collection"
				+ " from biblio b" + " left outer join biblioitems bi on b.biblionumber = bi.biblionumber"
				+ " left outer join items i on i.biblioitemnumber = bi.biblioitemnumber"
//					+ " left outer join collections_tracking ct on ct.itemnumber = i.itemnumber"
//					+ " left outer join collections c on c.colId = ct.colId"
				+ " left outer join subitems s on s.itemnumber = i.itemnumber where ";
		if ("item".equalsIgnoreCase(type)) {
			sql = sql.concat("i.itemnumber = " + id);
		} else if ("biblio".equalsIgnoreCase(type)) {
			sql = sql.concat("b.biblionumber = " + id);
		} else {
			return null;
		}
		List<Data> data = this.template.query(sql, new RowMapper<Data>() {
			public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
				Data biblio = new Data();
				biblio.setBiblionumber(rs.getInt(1));
				biblio.setBiblioitemnumber(rs.getInt(2));
				biblio.setItemnumber(rs.getInt(3));
				biblio.setKeyword(rs.getString(4));
				biblio.setResourceId(rs.getInt(5));
				biblio.setDownloadable(rs.getInt(6));
				biblio.setResourceUrl(rs.getString(7));
				biblio.setAccesslevel(rs.getInt(8));
				biblio.setPages(rs.getString(9));
				biblio.setBookcover(rs.getString(10));
				biblio.setTitle(rs.getString(11));
				biblio.setAuthorname(rs.getString(12));
				biblio.setBarcode(rs.getString(13));
				biblio.setItemcallnumber(rs.getString(14));
				biblio.setCollection(rs.getString(15));
				return biblio;
			}
		});

		return data;
	}

	public List<Data> GetBiblioItemByBiblioId(int id) {
		String sql = "select b.biblionumber, bi.biblioitemnumber, i.itemnumber, i.keyword, s.resourceid, s.downloadable, s.resourceurl, s.accesslevel, s.accesspages, i.bookcover, b.title, b.author from biblio b left outer join biblioitems bi on b.biblionumber = bi.biblionumber inner join items i on i.biblioitemnumber = bi.biblioitemnumber left outer join subitems s on s.itemnumber = i.itemnumber where ";
		sql = sql.concat("b.biblionumber = " + id);
		List<Data> data = this.template.query(sql, new RowMapper<Data>() {
			public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
				Data biblio = new Data();
				biblio.setBiblionumber(rs.getInt(1));
				biblio.setBiblioitemnumber(rs.getInt(2));
				biblio.setItemnumber(rs.getInt(3));
				biblio.setKeyword(rs.getString(4));
				biblio.setResourceId(rs.getInt(5));
				biblio.setDownloadable(rs.getInt(6));
				biblio.setResourceUrl(rs.getString(7));
				biblio.setAccesslevel(rs.getInt(8));
				biblio.setPages(rs.getString(9));
				biblio.setBookcover(rs.getString(10));
				biblio.setTitle(rs.getString(11));
				biblio.setAuthorname(rs.getString(12));
				return biblio;
			}
		});

		return data;
	}

	public void UpdateItemData(final List<Item> items) {
		this.template.batchUpdate("update items set keyword = ? ,bookcover = ? where itemnumber = ?",
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, items.get(i).getKeyword());
						ps.setString(2, items.get(i).getBookcover());
						ps.setInt(3, items.get(i).getItemnumber());
					}

					public int getBatchSize() {
						return items.size();
					}
				});
	}

	public void deleteResource(int id) throws Exception {
		int result = this.template.update("delete from subitems where resourceId = ?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		});
		if (result != 1) {
			throw new Exception("Failed to delete");
		}
	}

	public void InsertBiblioMetadata(List<SyncData> biblio) {
		try {
			this.template.batchUpdate(
					"insert into biblio_metadata (`id`, `biblionumber`, `format`, `schema`, `metadata`, `timestamp`) values (?,?,?,?,?,?)",
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, biblio.get(i).getBm_id());
							ps.setInt(2, biblio.get(i).getBiblionumber());
							ps.setString(3, biblio.get(i).getBm_format());
							ps.setString(4, biblio.get(i).getBm_schema());
							ps.setString(5, biblio.get(i).getBm_metadata());
							ps.setString(6, biblio.get(i).getBm_timestamp());
						}

						public int getBatchSize() {
							return biblio.size();
						}
					});
		} catch (DuplicateKeyException e) {
			// e.printStackTrace();
		}
	}

	public void UpdateBiblioMetadata(List<SyncData> biblio) {
		try {
			this.template.batchUpdate(
					"update biblio_metadata set biblionumber = ?, format = ?, `schema` = ?, metadata = ?, timestamp = ? where id = ?",
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, biblio.get(i).getBiblionumber());
							ps.setString(2, biblio.get(i).getBm_format());
							ps.setString(3, biblio.get(i).getBm_schema());
							ps.setString(4, biblio.get(i).getBm_metadata());
							ps.setString(5, biblio.get(i).getBm_timestamp());
							ps.setInt(6, biblio.get(i).getBm_id());
						}

						public int getBatchSize() {
							return biblio.size();
						}
					});
		} catch (DuplicateKeyException e) {
			// e.printStackTrace();
		}
	}

	public void DeleteAndUpdateItemsTypes(List<ItemTypes> itemtypes) {
		try {
			this.template.execute("DELETE FROM itemtypes where status=0");
			this.template.batchUpdate("INSERT INTO itemtypes (itemtypes, itemcode) values (?, ?)",
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setString(1, itemtypes.get(i).getName());
							ps.setString(2, itemtypes.get(i).getCode());
						}

						@Override
						public int getBatchSize() {
							return itemtypes.size();
						}
					});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	public List<ItemTypes> getItemTypesList() {
		try {
			return this.template.query("SELECT itemID, itemtypes, itemcode FROM itemtypes order by status asc",
					(rs, rowNum) -> new ItemTypes(rs.getInt(1), rs.getString(2), rs.getString(3)));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return new ArrayList<ItemTypes>();
	}

	public List<Data> getSyncedBiblioList(int itemNumber) {
		try {
			return this.template.query(
					"select b.biblionumber, i.itemnumber, b.title, COALESCE(i.enumchron, '') as enumchron, COALESCE(b.author, '') as author from biblio b inner join items i on i.biblionumber = b.biblionumber where i.itemnumber > "
							+ itemNumber+" and i.status=0 and b.status=0",
					(rs, rowNum) -> {
						Data d = new Data();
						d.setBiblionumber(rs.getInt("biblionumber"));
						d.setItemnumber(rs.getInt("itemnumber"));
						d.setTitle(rs.getString("title"));
						d.setEnumchron(rs.getString("enumchron"));
						d.setAuthorname(rs.getString("author"));
						return d;
					});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	public List<Patron> getPatronList(String last_sync) {
		String sql = "SELECT borrowernumber, cardnumber, userid, email, surname, title, dateofbirth, city, category FROM borrowers";
		if (last_sync != null) {
			sql = String.format("%s where last_sync > '%s'", sql, last_sync);
		}
		return this.template.query(sql,
				(rs, rowNum) -> new Patron(rs.getInt("borrowernumber"), rs.getString("cardnumber"),
						rs.getString("userid"), null, null, rs.getString("email"), rs.getString("surname"),
						rs.getString("title"), rs.getDate("dateofbirth"), rs.getString("city"),
						rs.getString("category")));
	}

	public Patron getLastPatronInfo() {
		List<Patron> data_list = this.template.query(
				"select COALESCE(max(borrowernumber), 0) as borrowernumber, max(last_sync) as updated_on from borrowers",
				(rs, row) -> {
					Patron p = new Patron();
					p.setBorrowernumber(rs.getInt(1));
					p.setUpdated_on(rs.getString(2));
					return p;
				});
		return data_list.get(0);
	}

	public void insertPatrons(List<Patron> new_patrons) {
		this.template.batchUpdate(
				"insert into borrowers(borrowernumber, cardnumber, userid, password, last_sync, email, surname, title, dateofbirth, city, category) values(?,?,?,?,?,?,?,?,?,?,?)",
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setInt(1, new_patrons.get(i).getBorrowernumber());
						ps.setString(2, new_patrons.get(i).getCardnumber());
						ps.setString(3, new_patrons.get(i).getUserid());
						ps.setString(4, new_patrons.get(i).getPassword());
						ps.setString(5, new_patrons.get(i).getUpdated_on());
						ps.setString(6, new_patrons.get(i).getEmail());
						ps.setString(7, new_patrons.get(i).getSurname());
						ps.setString(8, new_patrons.get(i).getTitle());
						Date d = new_patrons.get(i).getDateofbirth();
						ps.setDate(9, d == null ? null : new java.sql.Date(d.getTime()));
						ps.setString(10, new_patrons.get(i).getCity());
						ps.setString(11, new_patrons.get(i).getCategory());
					}

					@Override
					public int getBatchSize() {
						return new_patrons.size();
					}
				});
	}

	public void updatePatrons(List<Patron> update_patrons) {
		this.template.batchUpdate(
				"update borrowers set cardnumber = ?, userid = ?, password = ?, last_sync = ?, email = ?, surname = ?, title = ?, dateofbirth = ?, city = ?, category = ? where borrowernumber = ?",
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, update_patrons.get(i).getCardnumber());
						ps.setString(2, update_patrons.get(i).getUserid());
						ps.setString(3, update_patrons.get(i).getPassword());
						ps.setString(4, update_patrons.get(i).getUpdated_on());
						ps.setString(5, update_patrons.get(i).getEmail());
						ps.setString(6, update_patrons.get(i).getSurname());
						ps.setString(7, update_patrons.get(i).getTitle());
						Date d = update_patrons.get(i).getDateofbirth();
						ps.setDate(8, d == null ? null : new java.sql.Date(d.getTime()));
						ps.setString(9, update_patrons.get(i).getCity());
						ps.setString(10, update_patrons.get(i).getCategory());

						ps.setInt(11, update_patrons.get(i).getBorrowernumber());
					}

					@Override
					public int getBatchSize() {
						return update_patrons.size();
					}
				});
	}

	public List<SpecialRequest> getSpecialRequests() {
		String sql = "SELECT sr.id, i.barcode, concat(b.title, ' - ', it.itemtypes, coalesce(concat(' - ', i.enumchron), '')) as title , CONCAT(br.title, br.surname) as username, status FROM specialrequests sr"
				+ " INNER JOIN subitems si ON si.resourceId = sr.itemid"
				+ " INNER JOIN items i ON i.itemnumber = si.itemnumber"
				+ " INNER JOIN biblio b ON b.biblionumber = i.biblionumber"
				+ " INNER JOIN borrowers br ON br.borrowernumber = sr.borrowernumber"
				+ " LEFT OUTER JOIN itemtypes it on i.itype = it.itemcode";
		List<SpecialRequest> req_list = this.template.query(sql, new RowMapper<SpecialRequest>() {

			@Override
			public SpecialRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
				SpecialRequest req = new SpecialRequest();
				req.setId(rs.getInt("id"));
				req.setItembarcode(rs.getString("barcode"));
				req.setItemtitle(rs.getString("title"));
				req.setUsername(rs.getString("username"));
				req.setStatus(rs.getString("status"));
				return req;
			}
		});
		return req_list == null ? new ArrayList<SpecialRequest>() : req_list;
	}

	public void updateSpecialRequestById(int id, String status_str) throws Exception {
		int status = this.template.update("UPDATE specialrequests SET status = ? WHERE id = ?",
				new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, status_str);
						ps.setInt(2, id);
					}
				});
		if (status != 1)
			throw new Exception("Failed to update");
	}

	public List<ReadingHistory> getReadingHistory(String from, String to) {
		String filtered = "";
		if (from != null && to != null) {
			filtered = String.format(" where read_on between '%s' and '%s'", from, to);
		} else if (from != null) {
			filtered = String.format(" where read_on >= '%s'", from);
		} else if (to != null) {
			filtered = String.format(" where read_on <= '%s'", to);
		}

		List<ReadingHistory> history = this.template.query(
				"SELECT b.title, coalesce(CONCAT(br.title, br.surname), 'Anonymous') AS borrowername, ipaddress, read_on FROM reading_history rh"
						+ " LEFT OUTER JOIN borrowers br ON br.borrowernumber = rh.borrowerid"
						+ " INNER JOIN items i on i.itemnumber = rh.itemid"
						+ " INNER JOIN biblio b on b.biblionumber = i.biblionumber" + filtered
						+ " order by read_on desc",
				new RowMapper<ReadingHistory>() {

					@Override
					public ReadingHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
						ReadingHistory rh = new ReadingHistory();
						rh.setBorrowername(rs.getString("borrowername"));
						rh.setTitle(rs.getString("title"));
						rh.setReadTS(rs.getString("read_on"));
						rh.setIpaddress(rs.getString("ipaddress"));
						return rh;
					}
				});
		return history;
	}

	public String getPortalUrlAndSecretCode() {
		this.template.update(
				"UPDATE index_reloading_info SET secret_code = UUID(), last_sync = now() WHERE active = 1 and id <> 0");
		List<String> urls = this.template.query("SELECT url, secret_code FROM index_reloading_info WHERE active = 1",
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						String url = rs.getString("url");
						String code = rs.getString("secret_code");
						String result = url.replace("{code}", code);
						return result;
					}
				});
		return urls.get(0);
	}

	public List<ReadingHistory> getItemsPopularityReport(String from, String to) {

		String filtered = "";
		if (from != null && to != null) {
			filtered = String.format(" where read_on between '%s' and '%s'", from, to);
		} else if (from != null) {
			filtered = String.format(" where read_on >= '%s'", from);
		} else if (to != null) {
			filtered = String.format(" where read_on <= '%s'", to);
		}

		String sql = "select b.title, concat(it.itemtypes, coalesce(concat(' - ', i.enumchron), '')) as itemtype, rh.count"
				+ " from items i inner join" + " (select itemid, count(borrowerid) as count" + " from reading_history"
				+ filtered + " group by itemid) rh on rh.itemid = i.itemNumber"
				+ " inner join biblio b on b.biblionumber = i.biblionumber"
				+ " left outer join itemtypes it on i.itype = it.itemcode";

		return this.template.query(sql, new RowMapper<ReadingHistory>() {
			@Override
			public ReadingHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReadingHistory hist = new ReadingHistory();
				hist.setTitle(rs.getString("title"));
				hist.setItemtype(rs.getString("itemtype"));
				hist.setReadcount(rs.getInt("count"));
				return hist;
			}
		});
	}

	public Map<String, List<ReadingHistory>> getItemsPopularityByAgeByAgeReport(String from, String to) {

		String filtered = "";
		if (from != null && to != null) {
			filtered = String.format(" where read_on between '%s' and '%s'", from, to);
		} else if (from != null) {
			filtered = String.format(" where read_on >= '%s'", from);
		} else if (to != null) {
			filtered = String.format(" where read_on <= '%s'", to);
		}

		String sql = "select b.title, concat(it.itemtypes, coalesce(concat(' - ', i.enumchron), '')) as itemtype, rh.count, coalesce(concat(rh.age, '-', rh.age + 9), 'Unknown') as age"
				+ " from items i inner join"
				+ " (select r.itemid, count(r.borrowerid) as count, floor((YEAR(CURDATE()) - YEAR(dateofbirth))/10) * 10 as age"
				+ " from reading_history r" + " inner join borrowers b on r.borrowerid = b.borrowernumber" + filtered
				+ " group by itemid, age) rh on rh.itemid = i.itemNumber"
				+ " inner join biblio b on b.biblionumber = i.biblionumber"
				+ " left outer join itemtypes it on i.itype = it.itemcode";
		List<ReadingHistory> historyList = this.template.query(sql, new RowMapper<ReadingHistory>() {
			@Override
			public ReadingHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReadingHistory hist = new ReadingHistory();
				hist.setTitle(rs.getString("title"));
				hist.setItemtype(rs.getString("itemtype"));
				hist.setReadcount(rs.getInt("count"));
				hist.setAgerange(rs.getString("age"));
				return hist;
			}
		});
		SortedMap<String, List<ReadingHistory>> historyMap = new TreeMap<>();
		for (ReadingHistory readingHistory : historyList) {
			List<ReadingHistory> tmp_hist = historyMap.getOrDefault(readingHistory.getAgerange(),
					new ArrayList<ReadingHistory>());
			tmp_hist.add(readingHistory);
			historyMap.put(readingHistory.getAgerange(), tmp_hist);
		}
		return historyMap;
	}

	public List<Patron> getPatronAccessReportByCity(String from, String to) {
		String filtered = "";
		if (from != null && to != null) {
			filtered = String.format(" where last_access between '%s' and '%s'", from, to);
		} else if (from != null) {
			filtered = String.format(" where last_access >= '%s'", from);
		} else if (to != null) {
			filtered = String.format(" where last_access <= '%s'", to);
		}
		return this.template.query("SELECT count(distinct(b.borrowernumber)) count, b.city FROM borrowers b"
				+ " inner join borrowers_access_log ba on b.borrowernumber = ba.borrowerid" + filtered
				+ " group by city", (rs, rowNum) -> {
					Patron p = new Patron();
					p.setAccesscount(rs.getInt("count"));
					p.setCity(rs.getString("city"));
					return p;
				});
	}

	public List<Patron> getPatronAccessReportByCategory(String from, String to) {
		String filtered = "";
		if (from != null && to != null) {
			filtered = String.format(" where last_access between '%s' and '%s'", from, to);
		} else if (from != null) {
			filtered = String.format(" where last_access >= '%s'", from);
		} else if (to != null) {
			filtered = String.format(" where last_access <= '%s'", to);
		}
		return this.template.query("SELECT count(distinct(b.borrowernumber)) count, category FROM borrowers b"
				+ " inner join borrowers_access_log ba on b.borrowernumber = ba.borrowerid" + filtered
				+ " group by category", (rs, rowNum) -> {
					Patron p = new Patron();
					p.setAccesscount(rs.getInt("count"));
					p.setCategory(rs.getString("category"));
					return p;
				});
	}

	public int getPendingSpecialRequestCount() {
		List<Integer> count_list = this.template
				.query("select count(*) as count from specialrequests where status = 'pending'", (rs, rowNum) -> {
					return rs.getInt("count");
				});
		return count_list.get(0);
	}

	public List<SubItem> getResourceUrlList(int offset, int count) {
		return this.template.query("select si.resourceurl, si.accesspages, i.collection from subitems si"
				+ " inner join items i on i.itemnumber = si.itemnumber"
				+ " where si.accesslevel = 2 and (si.resourceurl like '%.pdf' or si.resourceurl like '%.docx') limit "
				+ offset + ", " + count, (rs, rowNum) -> {
					SubItem si = new SubItem();
					si.setResourceurl(rs.getString("resourceurl"));
					si.setAccesspages(rs.getInt("accesspages"));
					si.setCollection(rs.getString("collection"));
					return si;
				});
	}

	public List<Data> GetItemsByBiblioNumbers(List<Integer> idList) {
		String inSql = String.join(",", Collections.nCopies(idList.size(), "?"));
		String sql = String.format(
				"SELECT i.itemnumber, GROUP_CONCAT(si.resourceurl  separator '<<eresource-splitter>>') resources, i.bookcover FROM items i LEFT OUTER JOIN subitems si ON si.itemnumber = i.itemnumber WHERE i.biblionumber IN (%s) GROUP BY i.itemnumber",
				inSql);
		return this.template.query(sql, idList.toArray(), (rs, rowNo) -> {
			Data d = new Data();
			d.setItemnumber(rs.getInt("itemnumber"));
			d.setResourceUrl(rs.getString("resources"));
			d.setBookcover(rs.getString("bookcover"));
			return d;
		});
	}

	public void deleteUnlinkedResources(List<Integer> dList) {
		String inSql = String.join(",", Collections.nCopies(dList.size(), "?"));
		String sql = String.format(
				"DELETE i, si FROM items i LEFT OUTER JOIN subitems si ON i.itemnumber = si.itemnumber WHERE i.itemnumber IN (%s)",
				inSql);
		this.template.update(sql, dList.toArray());
	}

	public void deleteBiblio(int biblionumber) {
		this.template.update(
				"DELETE b, bi, bm FROM biblio b LEFT OUTER JOIN biblioitems bi ON b.biblionumber = bi.biblionumber"
						+ " LEFT OUTER JOIN biblio_metadata bm ON b.biblionumber = bm.biblionumber WHERE b.biblionumber = "
						+ biblionumber);
	}

	public int getItemtypesByItemName(String name) {
		List<Integer> count_list = this.template
				.query("select count(*) as count from itemtypes where itemtypes='" + name + "'", (rs, rowNum) -> {
					return rs.getInt("count");
				});
		return count_list.get(0);
	}

	public void saveItemType(ItemTypes itemtypes) {
		String insertSql = "insert into itemtypes(itemtypes,itemcode,status) values(?,?,?)";
		Object[] params = new Object[] { itemtypes.name, itemtypes.code, 1 };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		this.template.update(insertSql, params, types);

	}

	public List<ItemTypes> getItemTypes() {
		try {
			return this.template.query("SELECT itemID,itemtypes,itemcode FROM itemtypes order by status asc",
					(rs, rowNum) -> new ItemTypes(rs.getInt(1), rs.getString(2), rs.getString(3)));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return new ArrayList<ItemTypes>();
	}

	public ItemTypes getitemtypeById(int itemID) {
		List<ItemTypes> dataList = this.template.query(
				"select itemID,itemtypes,itemcode from itemtypes where itemID=" + itemID + "",
				new RowMapper<ItemTypes>() {
					public ItemTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
						ItemTypes d = new ItemTypes();
						d.setId(rs.getInt(1));
						d.setName(rs.getString(2));
						d.setCode(rs.getString(3));

						return d;
					}
				});
		return dataList != null && dataList.size() > 0 ? dataList.get(0) : new ItemTypes();

	}

	public void updateItemType(ItemTypes itemtypes) {
		String insertSql = "update itemtypes set itemtypes = ? , itemcode = ? where itemID = ?";
		Object[] params = new Object[] { itemtypes.name, itemtypes.code, itemtypes.id };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		this.template.update(insertSql, params, types);

	}

	public void deleteItemtype(int itemID) throws Exception {
		int result = this.template.update("delete from itemtypes where itemID = ?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, itemID);
			}
		});
		if (result != 1) {
			throw new Exception("Failed to delete");
		}

	}

	public int getCollectionByCollectionName(String name) {
		List<Integer> count_list = this.template
				.query("select count(*) as count from collections where colTitle='" + name + "'", (rs, rowNum) -> {
					return rs.getInt("count");
				});
		return count_list.get(0);
	}

	public void saveCollection(Collection collection) {
		String insertSql = "insert into collections(colTitle,colDes) values(?,?)";
		Object[] params = new Object[] { collection.name, collection.code };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
		this.template.update(insertSql, params, types);
	}

	public List<Collection> getCollections() {
		try {
			return this.template.query("SELECT colId,colTitle,colDes FROM collections",
					(rs, rowNum) -> new Collection(rs.getInt(1), rs.getString(2), rs.getString(3)));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return new ArrayList<Collection>();
	}

	public void deleteCollection(int collectionID) throws Exception {
		int result = this.template.update("delete from collections where colId = ?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, collectionID);
			}
		});
		if (result != 1) {
			throw new Exception("Failed to delete");
		}

	}

	public Collection getCollectionById(int collectionID) {
		List<Collection> dataList = this.template.query(
				"select colId,colTitle,colDes from collections where colId=" + collectionID + "",
				new RowMapper<Collection>() {
					public Collection mapRow(ResultSet rs, int rowNum) throws SQLException {
						Collection d = new Collection();
						d.setId(rs.getInt(1));
						d.setName(rs.getString(2));
						d.setCode(rs.getString(3));

						return d;
					}
				});
		return dataList != null && dataList.size() > 0 ? dataList.get(0) : new Collection();
	}

	public void updateCollection(Collection collection) {
		String insertSql = "update collections set colTitle = ? , colDes = ? where colId = ?";
		Object[] params = new Object[] { collection.name, collection.code, collection.id };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		this.template.update(insertSql, params, types);
	}

	public void saveItemTypebyExcelorCSV(List<ItemTypes> itemTypesList) {
		try {
			this.template.batchUpdate("insert into itemtypes(itemtypes,itemcode,status) values (?,?,?)",
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setString(1, itemTypesList.get(i).getName());
							ps.setString(2, itemTypesList.get(i).getCode());
							ps.setInt(3, 1);
						}

						public int getBatchSize() {
							return itemTypesList.size();
						}
					});
		} catch (DuplicateKeyException e) {
			// e.printStackTrace();
		}
		deleteDuplicateDataItemTypes();

	}

	public void deleteDuplicateDataItemTypes() {
		this.template.update(
				"DELETE c1 FROM  itemtypes c1 INNER JOIN itemtypes c2 WHERE c1.itemID > c2.itemID AND c1.itemtypes = c2.itemtypes");
	}

	public void deleteDuplicateDataCollection() {
		this.template.update(
				"DELETE c1 FROM  collections c1 INNER JOIN collections c2 WHERE c1.colId > c2.colId AND c1.colTitle = c2.colTitle");
	}

	public void saveCollectionbyExcelorCSV(List<Collection> collectionList) {
		try {
			this.template.batchUpdate("insert into collections(colTitle,colDes,status) values (?,?,?)",
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setString(1, collectionList.get(i).getName());
							ps.setString(2, collectionList.get(i).getCode());
							ps.setInt(3, 1);
						}

						public int getBatchSize() {
							return collectionList.size();
						}
					});
		} catch (DuplicateKeyException e) {
			// e.printStackTrace();
		}
		deleteDuplicateDataCollection();
	}

	String timestamp = "yyyy-MM-dd HH:mm:ss";
	SimpleDateFormat timeStamp = new SimpleDateFormat(timestamp);
	int status = 1;

	public void saveMember(Members member, String password) throws ParseException {
		SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		date = originalFormat.parse(member.getDateOfBirth());
		String insertSql = "insert into borrowers(cardnumber,userid,password,last_sync,email,surname,title,dateofbirth,city,category,status) values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { member.cardNumber, member.username, password, timeStamp.format(new Date()),
				member.email, member.surname, member.title, targetFormat.format(date).toString(), member.city,
				member.category, status };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		this.template.update(insertSql, params, types);
	}

	public List<Members> getMembers() {
		try {
			return this.template.query(
					"SELECT borrowernumber,cardnumber,userid,last_sync,email,surname,title,dateofbirth,city,category FROM borrowers",
					(rs, rowNum) -> new Members(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getString(10)));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return new ArrayList<Members>();
	}

	public void deleteMember(int memberID) throws Exception {
		int result = this.template.update("delete from borrowers where borrowernumber = ?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, memberID);
					}
				});
		if (result != 1) {
			throw new Exception("Failed to delete");
		}
	}

	public Members getmemberById(int memberID) {
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat targetFormat = new SimpleDateFormat("MM/dd/yyyy");
		List<Members> dataList = this.template.query(
				"select borrowernumber,cardnumber,userid,email,surname,title,dateofbirth,city,category from borrowers where borrowernumber="
						+ memberID + "",
				new RowMapper<Members>() {
					public Members mapRow(ResultSet rs, int rowNum) throws SQLException {
						Date date;
						Members d = new Members();
						d.setId(rs.getInt(1));
						d.setCardNumber(rs.getString(2));
						d.setUsername(rs.getString(3));
						d.setEmail(rs.getString(4));
						d.setSurname(rs.getString(5));
						d.setTitle(rs.getString(6));
						try {
							date = originalFormat.parse(rs.getString(7));
							d.setDateOfBirth(targetFormat.format(date).toString());
						} catch (Exception e) {
							e.printStackTrace();
						}
						d.setCity(rs.getString(8));
						d.setCategory(rs.getString(9));

						return d;
					}
				});
		return dataList != null && dataList.size() > 0 ? dataList.get(0) : new Members();
	}

	public void updateMember(Members members) throws ParseException {
		SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		date = originalFormat.parse(members.dateOfBirth);
		String insertSql = "update borrowers set surname = ? , userid = ? , email=? , title=? , dateofbirth=? , cardnumber=? , city=? , category=? where borrowernumber = ?";
		Object[] params = new Object[] { members.surname, members.username, members.email, members.title,
				targetFormat.format(date).toString(), members.cardNumber, members.city, members.category, members.id };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		this.template.update(insertSql, params, types);
	}

	public List<Data> removeCollection(List<Data> dataList) {

		for (Data d : dataList) {
			String sql = "";
			sql = "Select colDes from collections where colDes='" + d.getCollection() + "'";

			if (sql != null && !sql.isEmpty()) {
				dataList.remove(d);
			}
		}

		for (Data d : dataList) {
			System.out.println(d.getCollection());
		}

		return null;
	}

	private int getBiblioitemNumber(int recordNumber) {
		String sql = "SELECT biblioitemnumber FROM biblioitems where biblionumber=" + recordNumber + "";
		int id = template.queryForObject(sql, Integer.class);
		return id;
	}

	public int saveItemsbyExcelorCSV(List<Data> dataList, int recordNumber) {
		String timestamp = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat timeStamp = new SimpleDateFormat(timestamp);

		int biblioItemNumber = getBiblioitemNumber(recordNumber);

		SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MMMM-yyyy");
		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			this.template.batchUpdate(
					"insert into temp_import_items(booksellerid,homebranch,itemcallnumber,barcode,itemtypecode,publisheddate,collectioncode,datetime,biblionumber,biblioitemnumber) values (?,?,?,?,?,?,?,?,?,?)",
					new BatchPreparedStatementSetter() {

						public void setValues(PreparedStatement ps, int i) throws SQLException {
							Date date;
							ps.setString(1, dataList.get(i).getBooksellerid());
							ps.setString(2, dataList.get(i).getHomebranch());
							ps.setString(3, dataList.get(i).getItemcallnumber());
							ps.setString(4, dataList.get(i).getBarcode());
							ps.setString(5, dataList.get(i).getItemtype());
							try {
								date = originalFormat.parse(dataList.get(i).getPublicationyear());
								ps.setString(6, targetFormat.format(date).toString());
							} catch (ParseException e) {
								e.printStackTrace();
							}
							ps.setString(7, dataList.get(i).getCollection());
							ps.setString(8, timeStamp.format(new Date()));
							ps.setInt(9, recordNumber);
							ps.setInt(10, biblioItemNumber);
						}

						public int getBatchSize() {
							return dataList.size();
						}
					});
		} catch (DuplicateKeyException e) {
		}
		int count = template.update(
				"insert into items(booksellerid,homebranch,itemcallnumber,barcode,itype,collection,publisheddate,biblionumber,biblioitemnumber)"
						+ "select booksellerid,homebranch,itemcallnumber,barcode,itemtypecode,collectioncode,publisheddate,biblionumber,biblioitemnumber "
						+ "from temp_import_items ti,collections c,itemtypes i where ti.collectioncode=c.colDes and ti.itemtypecode=i.itemcode");
		template.update("truncate table temp_import_items");

		return count;

	}
}
