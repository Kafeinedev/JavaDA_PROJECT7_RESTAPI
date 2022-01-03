package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidService;

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
 * Handle CRUD request for BidList
 */

@Controller
public class BidListController {

	Logger log = LogManager.getLogger("BidListController");

	@Autowired
	private BidService bidService;

	@RequestMapping("/bidList/list")
	public String home(Model model) {
		log.info("RequestMapping @ /bidList/list");

		model.addAttribute("bidList", bidService.findAllBids());
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		log.info("GetMapping @ /bidList/add");

		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bidList, BindingResult result, Model model) {
		log.info("PostMapping @ /bidList/validate");

		if (result.hasErrors()) {
			return "bidList/add";
		}
		bidService.createBid(bidList);

		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /bidList/update/" + id);

		model.addAttribute("bidList", bidService.findBidById(id));

		return "bidList/update";
	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		log.info("PostMapping @ /bidList/update/" + id);

		if (result.hasErrors()) {
			bidList.setBidListId(id);
			return "bidList/update";
		}
		bidService.updateBid(id, bidList);

		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /bidList/delete/" + id);

		bidService.deleteBidById(id);

		return "redirect:/bidList/list";
	}
}
