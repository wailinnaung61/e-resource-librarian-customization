package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Test;

public class test {

	
	public Map<String, List<Test>>  fsdfs()
	{
		 Map<String, List<Test>> book = new HashMap<String, List<Test>>();

	     // create list one and store values
	     List<Test> valSetOne = new ArrayList<Test>();
	     valSetOne.add(new Test("text","required"));

	     // create list two and store values
	     List<Test> valSetTwo = new ArrayList<Test>();
	     valSetTwo.add(new Test("text",""));

	     // create list three and store values
	     List<Test> valSetThree = new ArrayList<Test>();
	     valSetThree.add(new Test("text","required"));

	     // put values into map
	     book.put("Title", valSetOne);
	     book.put("Author", valSetTwo);
	     book.put("Subject", valSetThree);
	     
	     return book;
	     
	     
	}

}
