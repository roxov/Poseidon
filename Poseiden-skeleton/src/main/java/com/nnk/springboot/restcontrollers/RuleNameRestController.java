package com.nnk.springboot.restcontrollers;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;

@RestController
public class RuleNameRestController {
	private static final Logger LOGGER = LogManager.getLogger(RuleNameRestController.class);

	@Autowired
	private RuleNameService ruleNameService;

	@GetMapping(value = "/ruleName")
	public Optional<RuleName> findById(@RequestParam Integer id) {
		LOGGER.info("Getting rule name identified by id");
		return ruleNameService.findById(id);
	}

	@PostMapping(value = "/ruleName")
	public RuleName addRuleName(@RequestBody RuleName ruleName) {
		LOGGER.info("Adding new rule name");
		return ruleNameService.addRuleName(ruleName);
	}

	@PutMapping(value = "/ruleName")
	public RuleName updateRuleName(@RequestBody RuleName ruleName) {
		LOGGER.info("Updating rule name");
		return ruleNameService.updateRuleName(ruleName);
	}

	@DeleteMapping(value = "/ruleName")
	public void deleteRuleName(@RequestParam Integer id) {
		LOGGER.info("Deleting rule name");
		ruleNameService.deleteRuleName(id);
	}

}
