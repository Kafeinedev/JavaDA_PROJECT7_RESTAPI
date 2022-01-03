package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Handle home page request
 */
@Controller
public class HomeController {
	@RequestMapping("/")
	public String home(Model model) {
		return "home";
	}

	/*
	 * For the moment redirect to /bidList/list
	 * 
	 * this is not accessible for user that do not have admin roles
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		return "redirect:/bidList/list";
	}

}
