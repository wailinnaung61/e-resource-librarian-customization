
package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import beans.Test;


@Controller
public class testcontroller {

	@RequestMapping(value = "/test")
	public String test(Model m, HttpServletRequest req, HttpServletResponse res) {
        // create map to store
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

		m.addAttribute("list", book);
		m.addAttribute("test", new Test());

		return "test";
	}
}
