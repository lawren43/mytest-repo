package my.test.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	final static Logger logger= Logger.getLogger(IndexController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		logger.info("test");
		model.addAttribute("username", "Lawren");
		return "index";

	}
	
	public void test() {
		
	}

}
