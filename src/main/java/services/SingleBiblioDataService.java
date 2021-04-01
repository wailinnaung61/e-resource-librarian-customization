package services;

import java.util.List;

import beans.BookFramework;
import beans.Data;

public interface SingleBiblioDataService {

	void saveBiblioData(List<BookFramework> listFramework);

	void addItem(Data data);

	List<Data> getItemsbyBiblioData(int biblioLatestinfo);

	void deleteItem(int itemID) throws Exception;

}
