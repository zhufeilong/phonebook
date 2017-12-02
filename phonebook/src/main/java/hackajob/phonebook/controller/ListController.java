package hackajob.phonebook.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hackajob.common.Utils;
import hackajob.phonebook.model.Contact;

@Controller
public class ListController {
	Logger log = Logger.getLogger(getClass());

    @RequestMapping("/list")
    public String list(@RequestParam(value="searchTerm", required=false) String searchTerm,
    		@RequestParam(value="searchValue", required=false) String searchValue,
    		@RequestParam(value="sortTerm", required=false) String sortTerm,
    		Model model) throws MalformedURLException, IOException {
    	
    	model.addAttribute("searchTerm", searchTerm);
    	model.addAttribute("searchValue", searchValue);
    	
		String content = Utils.getFromHttp(new URL("http://www.mocky.io/v2/581335f71000004204abaf83"));
		List<Contact> list = Utils.getContact(content);
		if(searchValue!=null&&!searchValue.equals("")) {
			list = filter(list,searchTerm,searchValue);
		}
		if(sortTerm!=null&&!sortTerm.equals("")) {
			Collections.sort(list, (Comparator<Contact>) (Contact o1, Contact o2)->o1.getAttr(sortTerm).compareTo(o2.getAttr(sortTerm)));		
		}
		model.addAttribute("list", list);
        return "list";
    }
    
    @RequestMapping("/list2")
    public String list2(Model model) throws MalformedURLException, IOException {
    	
		String content = Utils.getFromHttp(new URL("http://www.mocky.io/v2/581335f71000004204abaf83"));
		List<Contact> list = Utils.getContact(content);
		model.addAttribute("list", list);
        return "list2";
    }
    
    @RequestMapping("/list3")
    public String list3(@RequestParam(value="searchTerm", required=false) String searchTerm,
    		@RequestParam(value="searchValue", required=false) String searchValue,
    		@RequestParam(value="sortTerm", required=false) String sortTerm,
    		@RequestParam(value="contacts", required=false) String contacts,
    		Model model) throws MalformedURLException, IOException {
    	
    	model.addAttribute("searchTerm", searchTerm);
    	model.addAttribute("searchValue", searchValue);
    	
		List<Contact> list = Utils.getContact(contacts);
		if(searchValue!=null&&!searchValue.equals("")) {
			list = filter(list,searchTerm,searchValue);
		}
		if(sortTerm!=null&&!sortTerm.equals("")) {
			Collections.sort(list, (Comparator<Contact>) (Contact o1, Contact o2)->o1.getAttr(sortTerm).compareTo(o2.getAttr(sortTerm)));		
		}
		model.addAttribute("list", list);
        return "list2";
    }
    
	private List<Contact> filter(List<Contact> list, String searchTerm, String searchValue) {
		List<Contact> result = new ArrayList<>();
		for(Contact contact : list) {
			String value = contact.getAttr(searchTerm).toLowerCase();
			if(value.contains(searchValue.toLowerCase())) {
				result.add(contact);
			}
		}
		return result;
	}
	
}
