package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import beans.BookFramework;
import services.ItemService;

@Repository
public class Frameworks {

	@Autowired
	ItemService itemService;

	public List<BookFramework> bookFrameworks() {
		List<BookFramework> book_Framework = new ArrayList<BookFramework>();

		BookFramework title = new BookFramework("Title", "text", true, "title");
		BookFramework author = new BookFramework("Author", "text", true, "author");
		BookFramework notes = new BookFramework("Notes", "text", false, "notes");
		BookFramework publicationYear = new BookFramework("Publication Year", "text", true, "publicationyear");
		BookFramework place = new BookFramework("Place", "text", false, "place");
		BookFramework editionstatement = new BookFramework("Edition Statement", "text", false, "editionstatement");
		BookFramework subject = new BookFramework("Subject", "text", false, "subject");
		BookFramework content = new BookFramework("Content", "text", false, "content");
		BookFramework summary = new BookFramework("Summary", "text", false, "summary");
		BookFramework isbn = new BookFramework("ISBN", "text", false, "isbn");
		BookFramework itemtype = new BookFramework("Item Type", "selectbox", true, "itemtype",
				itemService.getItemTypes());

		book_Framework.add(title);
		book_Framework.add(author);
		book_Framework.add(notes);
		book_Framework.add(publicationYear);
		book_Framework.add(place);
		book_Framework.add(editionstatement);
		book_Framework.add(subject);
		book_Framework.add(content);
		book_Framework.add(summary);
		book_Framework.add(isbn);
		book_Framework.add(itemtype);

		return book_Framework;
	}

	/*
	 * public Map<String,List<BookFramework>> bookFramework() { Map<String,
	 * List<BookFramework>> book = new HashMap<String, List<BookFramework>>();
	 * 
	 * List<BookFramework> title =new ArrayList<BookFramework>(); title.add(new
	 * BookFramework("Title","text",true,"title"));
	 * 
	 * List<BookFramework> author =new ArrayList<BookFramework>(); title.add(new
	 * BookFramework("Author","text",true,"author"));
	 * 
	 * List<BookFramework> notes =new ArrayList<BookFramework>(); title.add(new
	 * BookFramework("Notes","text",false,"notes"));
	 * 
	 * List<BookFramework> publicationYear =new ArrayList<BookFramework>();
	 * title.add(new
	 * BookFramework("Publication Year","text",true,"publicationyear"));
	 * 
	 * List<BookFramework> place =new ArrayList<BookFramework>(); title.add(new
	 * BookFramework("Place","text",false,"place"));
	 * 
	 * List<BookFramework> editionStatement =new ArrayList<BookFramework>();
	 * title.add(new
	 * BookFramework("Edition Statement","text",false,"editionstatement"));
	 * 
	 * List<BookFramework> subject =new ArrayList<BookFramework>(); title.add(new
	 * BookFramework("Subject","text",false,"subject"));
	 * 
	 * List<BookFramework> content =new ArrayList<BookFramework>(); title.add(new
	 * BookFramework("Content","text",false,"content"));
	 * 
	 * List<BookFramework> summary =new ArrayList<BookFramework>(); title.add(new
	 * BookFramework("Summary","text",false,"summary"));
	 * 
	 * List<BookFramework> isbn =new ArrayList<BookFramework>(); title.add(new
	 * BookFramework("ISBN","text",false,"isbn"));
	 * 
	 * List<BookFramework> itemType =new ArrayList<BookFramework>(); title.add(new
	 * BookFramework("Item Type","selectbox",true,"itemtype"));
	 * 
	 * book.put("Title", title); book.put("Author", author); book.put("Notes",
	 * notes); book.put("Publication Year", publicationYear); book.put("Place",
	 * place); book.put("Edition Statement", editionStatement); book.put("Subject",
	 * subject); book.put("Content", content); book.put("Summary", summary);
	 * book.put("Isbn", isbn); book.put("Item Type", itemType);
	 * 
	 * return book;
	 * 
	 * }
	 */

}