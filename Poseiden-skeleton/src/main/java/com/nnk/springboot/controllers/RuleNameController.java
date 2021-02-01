package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;

@Controller
public class RuleNameController {
	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Autowired
	private RuleNameService ruleNameService;

	@RequestMapping("/ruleName/list")
	public String home(Model model) {
		model.addAttribute("ruleNames", ruleNameRepository.findAll());
		return "ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			ruleNameService.addRuleName(ruleName);
			model.addAttribute("ruleNames", ruleNameRepository.findAll());
			return "redirect:/ruleName/list";
		}
		return "ruleName/add";
	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		RuleName ruleName = ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
		model.addAttribute("ruleName", ruleName);
		return "ruleName/update";
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "ruleName/update";
		}

		ruleName.setId(id);
		ruleNameService.addRuleName(ruleName);
		model.addAttribute("ruleNames", ruleNameRepository.findAll());
		return "redirect:/ruleName/list";
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
		ruleNameService.deleteRuleName(id);
		model.addAttribute("ruleNames", ruleNameRepository.findAll());
		return "redirect:/ruleName/list";
	}
}
