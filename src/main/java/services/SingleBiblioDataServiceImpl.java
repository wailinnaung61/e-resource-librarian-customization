package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.BookFramework;
import beans.Data;
import dao.SingleBiblioDataDao;

@Service
public class SingleBiblioDataServiceImpl implements SingleBiblioDataService {

	@Autowired
	SingleBiblioDataDao dataDao;

	@Override
	public void saveBiblioData(List<BookFramework> listFramework) {
		dataDao.saveSingleBiblioData(listFramework);
	}

	@Override
	public void addItem(Data data) {
		dataDao.addItem(data);
	}

	@Override
	public List<Data> getItemsbyBiblioData(int biblioLatestinfo) {
		return dataDao.getItemsbyBiblioData(biblioLatestinfo);
	}

	@Override
	public void deleteItem(int itemID) throws Exception {
		dataDao.deleteItem(itemID);
	}

	@Override
	public int checkBiblioNumber(int biblioNumber) {
		return dataDao.checkBiblioNumber(biblioNumber);
	}

}
