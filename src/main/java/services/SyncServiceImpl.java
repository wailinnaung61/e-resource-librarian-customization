package services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import beans.BiblioSyncTypes;
import beans.Data;
import beans.ItemTypes;
import beans.Patron;
import beans.SyncData;
import dao.ISyncDao;
import dao.ItemDao;

@Service
public class SyncServiceImpl implements SyncService {

	@Autowired
	private ISyncDao syncDao;

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private UtilService utilService;

	@Override
	public int SyncNewBiblioItems() {
		Data item = itemDao.getLatestItemInfo();

		List<SyncData> biblioItems = syncDao.getBiblioItems(item.getItemnumber());
		System.out.println("Synced item " + biblioItems.get(0).getItemnumber());
		itemDao.InsertBiblio(biblioItems);
		itemDao.InsertBiblioItems(biblioItems);
		itemDao.InsertItems(biblioItems);
		itemDao.InsertBiblioMetadata(biblioItems);
		List<Data> dataList = biblioItems.stream().map(x -> {
			Data d = new Data();
			d.setBiblionumber(x.getBiblionumber());
			d.setItemnumber(x.getItemnumber());
			d.setTitle(x.getTitle());
			d.setAuthorname(x.getAuthorname());
			d.setEnumchron(x.getEnumchron());
			return d;
		}).collect(Collectors.toList());
		List<Integer> ids = dataList.stream().map(x -> x.getItemnumber()).collect(Collectors.toList());
		utilService.rebuildIndexes(ids);
		return item.getItemnumber();
	}

	@Override
	public List<Data> SyncUpdatedItemList(String syncType, List<Integer> idList) {
		if (syncType != null) {
			List<SyncData> biblioItems = null;
			if (BiblioSyncTypes.SyncWithItems.toString().equals(syncType)) {
				biblioItems = syncDao.getBiblioWithItemsByBibioNumber(idList);
				itemDao.UpdateBiblio(biblioItems);
				itemDao.UpdateBiblioItems(biblioItems);
				itemDao.UpdateItems(biblioItems);
				itemDao.UpdateBiblioMetadata(biblioItems);
				
				List<Integer> existItemNumbers = biblioItems.stream().map(SyncData::getItemnumber).collect(Collectors.toList());
				List<Data> dList = itemDao.GetItemsByBiblioNumbers(idList);
				
				dList = dList.stream().filter(x -> !existItemNumbers.contains(x.getItemnumber())).collect(Collectors.toList());
				
				List<Integer> deleteItemNumbers = dList.stream().map(Data::getItemnumber).collect(Collectors.toList());
				if(!deleteItemNumbers.isEmpty()) {
					itemDao.deleteUnlinkedResources(deleteItemNumbers);
					utilService.removeStaticResources(dList);
				}
			} else if (BiblioSyncTypes.SyncWithoutItems.toString().equals(syncType)) {
				biblioItems = syncDao.getBiblioDetailByBibioNumber(idList);
				itemDao.UpdateBiblio(biblioItems);
				itemDao.UpdateBiblioItems(biblioItems);
			} else if (BiblioSyncTypes.SyncWithItemNumber.toString().equals(syncType)) {
				biblioItems = syncDao.getBiblioItemsByList(idList);
				itemDao.UpdateBiblio(biblioItems);
				itemDao.UpdateBiblioItems(biblioItems);
				itemDao.UpdateItems(biblioItems);
			}
			List<Data> dataList = biblioItems.stream().map(x -> {
				Data d = new Data();
				d.setBiblionumber(x.getBiblionumber());
				d.setItemnumber(x.getItemnumber());
				d.setTitle(x.getTitle());
				d.setAuthorname(x.getAuthorname());
				d.setEnumchron(x.getEnumchron());
				return d;
			}).collect(Collectors.toList());
			
			List<Integer> ids = dataList.stream().map(x -> x.getItemnumber()).collect(Collectors.toList());
			utilService.rebuildIndexes(ids);
			return dataList;
		}
		return new ArrayList<Data>();
	}
	
	@Override
	public int GetNewBiblioItemCount() {
		Data item = itemDao.getLatestItemInfo();
		return syncDao.getBiblioItemCount(item.getItemnumber());
	}

	@Override
	public void syncItemTypes() {
		List<ItemTypes> items = syncDao.getItemTypes();
		itemDao.DeleteAndUpdateItemsTypes(items);
	}

	@Override
	public String syncPatrons() {
		Patron lastPatron = itemDao.getLastPatronInfo();
		List<Patron> synced_patrons = syncDao.getNewPatronList(lastPatron);
		List<Patron> new_patrons = synced_patrons.stream()
				.filter(x -> x.getBorrowernumber() > lastPatron.getBorrowernumber()).collect(Collectors.toList());
		List<Patron> update_patrons = synced_patrons.stream()
				.filter(x -> x.getBorrowernumber() <= lastPatron.getBorrowernumber()).collect(Collectors.toList());

		itemDao.insertPatrons(new_patrons);
		itemDao.updatePatrons(update_patrons);
		return lastPatron.getUpdated_on();
	}
}
