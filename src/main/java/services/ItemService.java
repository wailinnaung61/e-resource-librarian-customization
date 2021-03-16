package services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import beans.BiblioSearchType;
import beans.Data;
import beans.ItemTypes;
import beans.Patron;
import beans.ReadingHistory;
import beans.SpecialRequest;
import beans.bibliosingledata;

public interface ItemService {

	bibliosingledata getBiblioItem(BiblioSearchType data);

	void updateBiblioItem(bibliosingledata biblio) throws IOException;

	void deleteResourceById(int id) throws Exception;

	List<ItemTypes> getItemTypesList();

	List<Data> getSyncedBiblioList(int itemNumber);

	List<Patron> getPatronList(String last_sync);

	List<SpecialRequest> getSpecialRequests();

	void updateSpecialRequestById(int id, int status) throws Exception;

	List<ReadingHistory> getReadingHistory(String from, String to);

	List<ReadingHistory> getItemsPopularityReport(String from, String to);

	Map<String, List<ReadingHistory>> getItemsPopularityByAgeReport(String from, String to);

	List<Patron> getPatronAccessReportByCity(String from, String to);

	int getPendingSpecialRequestCount();

	List<Patron> getPatronAccessReportByCategory(String offset, String count);

	void checkAndRegenerateResources(int from, int to);

	void deleteBiblio(int biblionumber);

	int getItemtypesByItemName(String name);

	void saveItemType(ItemTypes itemtypes);

	List<ItemTypes> getItemTypes();

	ItemTypes getitemtypeById(int itemID);

	void updateItemType(ItemTypes itemtypes);

	void deleteItemtype(int itemID) throws Exception;

}