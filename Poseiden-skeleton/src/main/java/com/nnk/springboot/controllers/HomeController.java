package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Handle home page request
 */
@Controller
public class HomeController {

	Logger log = LogManager.getLogger("HomeController");

	@RequestMapping("/")
	public String home(Model model) {
		log.info("RequestMapping @ /");
		return "home";
	}

	/*
	 * For the moment redirect to /bidList/list
	 * 
	 * this is not accessible for user that do not have admin roles
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		log.info("RequestMapping @ /admin/home");
		return "redirect:/bidList/list";
	}

}
