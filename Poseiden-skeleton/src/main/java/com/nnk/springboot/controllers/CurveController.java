package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

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
 * Handle CRUD request for CurvePoint
 */
@Controller
public class CurveController {

	Logger log = LogManager.getLogger("CurveController");

	@Autowired
	private CurvePointService curvePointService;

	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		log.info("RequestMapping @ /curvePoint/list");

		model.addAttribute("curvePointList", curvePointService.findAllCurvePoints());

		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		log.info("GetMapping @ /curvePoint/add");

		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		log.info("PostMapping @ /curvePoint/validate");

		if (result.hasErrors()) {
			return "curvePoint/add";
		}
		curvePointService.createCurvePoint(curvePoint);

		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /curvePoint/update/" + id);

		model.addAttribute("curvePoint", curvePointService.findCurvePointById(id));

		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		log.info("PostMapping @ /curvePoint/update/" + id);

		if (result.hasErrors()) {
			curvePoint.setId(id);
			return "curvePoint/update";
		}
		curvePointService.updateCurvePoint(id, curvePoint);

		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /curvePoint/delete/" + id);

		curvePointService.deleteCurvePointById(id);

		return "redirect:/curvePoint/list";
	}
}
