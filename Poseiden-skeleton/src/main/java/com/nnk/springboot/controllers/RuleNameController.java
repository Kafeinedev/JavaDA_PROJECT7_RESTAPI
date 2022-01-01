package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

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
public class RuleNameController {

	Logger log = LogManager.getLogger("RuleNameController");

	@Autowired
	private RuleNameService ruleNameService;

	@RequestMapping("/ruleName/list")
	public String home(Model model) {
		log.info("RequestMapping @ /ruleName/list");

		model.addAttribute("ruleNameList", ruleNameService.findAllRuleNames());

		return "ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		log.info("PostMapping @ /ruleName/validate");

		if (result.hasErrors()) {
			return "ruleName/add";
		}
		ruleNameService.createRuleName(ruleName);

		return "ruleName/add";
	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /ruleName/update/" + id);

		model.addAttribute("ruleName", ruleNameService.findRuleNameById(id));

		return "ruleName/update";
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		log.info("PostMapping @ /ruleName/update/" + id);

		if (result.hasErrors()) {
			ruleName.setId(id);
			return "ruleName/update";
		}
		ruleNameService.updateRuleName(id, ruleName);

		return "redirect:/ruleName/list";
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		log.info("GetMapping @ /ruleName/delete/" + id);

		ruleNameService.deleteRuleNameById(id);

		return "redirect:/ruleName/list";
	}
}
