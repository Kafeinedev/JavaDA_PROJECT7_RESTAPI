package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

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

@Controller
public class RatingController {

	Logger log = LogManager.getLogger("RatingController");

	@Autowired
	private RatingService ratingService;

	@RequestMapping("/rating/list")
	public String home(Model model) {
		log.info("RequestMapping @ /rating/list");

		model.addAttribute("ratingList", ratingService.findAllRatings());

		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		log.info("GetMapping @ /rating/add");

		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		log.info("PostMapping @ /rating/validate");

		if (result.hasErrors()) {
			return "rating/add";
		}
		ratingService.createRating(rating);

		return "redirect:/rating/list";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /rating/update/" + id);

		model.addAttribute("rating", ratingService.findRatingById(id));

		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		log.info("PostMapping @ /rating/update/" + id);

		if (result.hasErrors()) {
			rating.setId(id);
			return "rating/update";
		}
		ratingService.updateRating(id, rating);

		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /rating/delete/" + id);

		ratingService.deleteRatingById(id);
		return "redirect:/rating/list";
	}
}
