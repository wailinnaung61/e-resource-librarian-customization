package services;

import java.util.List;

import beans.Data;

public interface SyncService {

	int SyncNewBiblioItems(); 

	List<Data> SyncUpdatedItemList(String syncType, List<Integer> itemList);

	int GetNewBiblioItemCount();

	void syncItemTypes();

	String syncPatrons();
}