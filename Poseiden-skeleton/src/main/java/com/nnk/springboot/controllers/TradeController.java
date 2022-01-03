package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

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
 * Handle CRUD request for Trade
 */
@Controller
public class TradeController {

	Logger log = LogManager.getLogger("TradeController");

	@Autowired
	private TradeService tradeService;

	@RequestMapping("/trade/list")
	public String home(Model model) {
		log.info("RequestMapping @ /trade/list");

		model.addAttribute("tradeList", tradeService.findAllTrades());

		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addUser(Trade bid) {
		log.info("GetMapping @ /trade/add");

		return "trade/add";
	}

	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {
		log.info("PostMapping @ /trade/validate");

		if (result.hasErrors()) {
			return "trade/add";
		}
		tradeService.createTrade(trade);

		return "redirect:/trade/list";
	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /trade/update/" + id);

		model.addAttribute("trade", tradeService.findTradeById(id));

		return "trade/update";
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
		log.info("PostMapping @ /trade/update/" + id);

		if (result.hasErrors()) {
			trade.setTradeId(id);
			return "trade/update";
		}
		tradeService.updateTrade(id, trade);

		return "redirect:/trade/list";
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /trade/delete/" + id);

		tradeService.deleteTradeById(id);

		return "redirect:/trade/list";
	}
}
