package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.BookFramework;
import dao.SingleBiblioDataDao;

@Service
public class SingleBiblioDataServiceImpl implements SingleBiblioDataService {

	@Autowired
	SingleBiblioDataDao dataDao;
	
	@Override
	public void saveBiblioData(List<BookFramework> listFramework) {
		dataDao.saveSingleBiblioData(listFramework);
		
	}
	

}
