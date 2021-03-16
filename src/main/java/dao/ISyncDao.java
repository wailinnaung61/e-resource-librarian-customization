package dao;

import java.util.List;

import beans.ItemTypes;
import beans.Patron;
import beans.SyncData;

public interface ISyncDao {

	List<SyncData> getBiblioItems(int i);

	List<SyncData> getBiblioItemsByList(List<Integer> itemList);

	List<SyncData> getBiblioWithItemsByBibioNumber(List<Integer> bNumbers);

	List<SyncData> getBiblioDetailByBibioNumber(List<Integer> bNumbers);

	int getBiblioItemCount(int itemnumber);

	List<ItemTypes> getItemTypes();

	List<Patron> getNewPatronList(Patron lastPatron);

}