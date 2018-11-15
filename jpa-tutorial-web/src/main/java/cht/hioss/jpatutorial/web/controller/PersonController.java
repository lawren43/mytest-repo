package cht.hioss.jpatutorial.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cht.hioss.jpatutorial.dto.PersonDTO;
import cht.hioss.jpatutorial.model.Person;
import cht.hioss.jpatutorial.service.PersonService;


@Controller
public class PersonController {
	
		private final static Logger logger = LoggerFactory.getLogger(PersonController.class);
		
		@Autowired(required=true)
		private PersonService personService;
		
		//@Autowired(required=true)
		//@Qualifier(value="personService")
		//public void setPersonService(PersonService ps){
			//this.personService = ps;
		//}
		
		@RequestMapping(value = "/persons", method = RequestMethod.GET)
		public String listPersons(Model model) {
			model.addAttribute("person", new Person());

			List<Person> personList = this.personService.listPersons();
			//logger.info(" personList.size():" +  personList.size());
			model.addAttribute("listPersons",  personList);
			return "person";
		}
		
		//For add and update person both
		@RequestMapping(value= "/person/add", method = RequestMethod.POST)
		public String addPerson(@ModelAttribute("person") PersonDTO p, HttpSession session){

			logger.info("addPerson():" + p.toString());
			if(p.getId() == null || p.getId() == 0){
				//new person, add it
				Person newp = new Person();
				newp.setCountry(p.getCountry());
				newp.setName(p.getName());
				this.personService.addPerson(newp);
			}else{
				//existing person, call update
				Person sessp = (Person)session.getAttribute("person");
				sessp.setCountry(p.getCountry());
				sessp.setName(p.getName());
				this.personService.updatePerson(sessp);
			}
			
			return "redirect:/persons";
			
		}
		
		@RequestMapping("/remove/{id}")
	    public String removePerson(@PathVariable("id") int id){
			
	        this.personService.removePerson(id);
	        return "redirect:/persons";
	    }
	 
	    @RequestMapping("/edit/{id}")
	    public String editPerson(@PathVariable("id") int id, Model model, HttpSession session){
	    	Person p = this.personService.findById(id);
	    	session.setAttribute("person", p);
	    	
	        model.addAttribute("person", p);
	        model.addAttribute("listPersons", this.personService.listPersons());
	        return "person";
	    }

}
