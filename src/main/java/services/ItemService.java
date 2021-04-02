package services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import beans.BiblioSearchType;
import beans.Collection;
import beans.Data;
import beans.ItemTypes;
import beans.Members;
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

	int getCollectionByCollectionName(String name);

	void saveCollection(Collection collection);

	List<Collection> getCollections();

	void deleteCollection(int collectionID) throws Exception;

	Collection getCollectionById(int collectionID);

	void updateCollection(Collection collection);

	void saveItemTypebyExcelorCSV(List<ItemTypes> itemTypesList);
	
	void saveCollectionbyExcelorCSV(List<Collection> collectionList);

	void saveMember(Members member,String Password) throws ParseException;

	List<Members> getMembers();

	void deleteMember(int memberID) throws Exception;

	Members getmemberById(int memberID);

	void updateMember(Members members) throws ParseException;

}