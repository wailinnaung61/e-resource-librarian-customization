package services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.Constants;
import beans.Data;
import dao.ItemDao;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class UtilService {
	@Autowired
	ItemDao itemDao;
	
	private Logger logger  = LogManager.getLogger();

	public void rebuildIndexes(List<Integer> ids) {
		if (ids.size() > 0) {
			try {
				String portalURL = itemDao.getPortalUrlAndSecretCode();
				String queryParams = ids.stream().map(x -> "ids=" + x).reduce((a, b) -> a + '&' + b).get();
				/*
				 * if(toDeleteList != null) { Optional<String> deleteParams =
				 * toDeleteList.stream().map(x -> "rids=" + x).reduce((a, b) -> a + '&' + b);
				 * queryParams += ("&" + deleteParams); }
				 */
				portalURL = portalURL + '?' + queryParams;
				OkHttpClient client = new OkHttpClient();
				Request request = new Request.Builder().url(portalURL).build();
				Call call = client.newCall(request);
				Response resp = call.execute();
				resp.close();
				logger.info("Reload indexes, {}", resp);
			} catch (IOException e) {
				logger.error("Error in reloading indexes: {}", e.getMessage());
			}
		}
	}

	public void removeStaticResources(List<Data> dList) {
		for (Data d : dList) {
			try {
				if(d.getResourceUrl() != null && !d.getResourceUrl().isEmpty()) {
					String[] resources = d.getResourceUrl().split("<<eresource-splitter>>");
					for(String r : resources) {
						File f = new File(Constants.RESOURCE_BASE_URL + Constants.PUBLIC_RESOURCES + r.trim());
						if(f.exists()) {
							f.delete();
						}
						f = new File(Constants.RESOURCE_BASE_URL + Constants.RESTRICTED_RESOURCES + r.trim());
						if(f.exists()) {
							f.delete();
						}
					}
				}
			
				if(d.getBookcover() != null && !d.getBookcover().isEmpty()) {
					File f = new File(Constants.RESOURCE_BASE_URL + Constants.BOOK_COVER_RESOURCE + d.getBookcover().trim());
					if(f.exists()) {
						f.delete();
					}
				}
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
	}

	public void deleteSearchIndex(int biblionumber) {
		try {
			String portalURL = itemDao.getPortalUrlAndSecretCode();
			portalURL = portalURL + "?dbiblionumber=" + biblionumber;
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(portalURL).build();
			Call call = client.newCall(request);
			Response resp = call.execute();
			resp.close();
			logger.info("Reload indexes, {}", resp);
		} catch (IOException e) {
			logger.error("Error in reloading indexes: {}", e.getMessage());
		}
	}
}
