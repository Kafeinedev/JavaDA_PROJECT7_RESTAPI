package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/*
 * Handle CRUD request for User
 */
@Controller
public class UserController {

	Logger log = LogManager.getLogger("UserController");

	@Autowired
	private UserService userService;

	@RequestMapping("/user/list")
	public String home(Model model) {
		log.info("RequestMapping @ /user/list");

		model.addAttribute("users", userService.findAllUsers());

		return "user/list";
	}

	@GetMapping("/user/add")
	public String addUser(User bid) {
		log.info("GetMapping @ /user/add");

		return "user/add";
	}

	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {
		log.info("PostMapping @ /user/validate");

		if (result.hasErrors()) {
			return "user/add";
		}
		userService.createUser(user);

		return "redirect:/user/list";
	}

	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /user/update/" + id);

		User user = userService.findUserById(id);
		user.setPassword("");
		model.addAttribute("user", user);

		return "user/update";
	}

	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
		log.info("PostMapping @ /User/update/" + id);

		if (result.hasErrors()) {
			user.setId(id);
			return "user/update";
		}
		userService.updateUser(id, user);

		return "redirect:/user/list";
	}

	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /User/delete/" + id);

		userService.deleteUserById(id);

		return "redirect:/user/list";
	}
}
