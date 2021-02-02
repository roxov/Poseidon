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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;

/**
 * Rest controller for RuleName entities.
 *
 */
@RestController
@RequestMapping("rest")
public class RuleNameRestController {

	private static final Logger LOGGER = LogManager.getLogger(RuleNameRestController.class);

	@Autowired
	private RuleNameService ruleNameService;

	@PostMapping(value = "/ruleName")
	public Optional<RuleName> addRuleName(@RequestBody RuleName ruleName) {
		LOGGER.info("Adding new rule name");
		return Optional.of(ruleNameService.addRuleName(ruleName));
	}

	@GetMapping(value = "/ruleName")
	public Optional<RuleName> findById(@RequestParam Integer id) {
		if (id == null) {
			LOGGER.error("The id must be fielded.");
			return Optional.empty();
		}
		LOGGER.info("Getting rule name identified by id");
		return ruleNameService.findById(id);
	}

	@PutMapping(value = "/ruleName")
	public Optional<RuleName> updateRuleName(@RequestBody RuleName ruleName) {
		if (ruleName.getId() == null) {
			LOGGER.error("The id is mandatory.");
			return Optional.empty();
		}
		LOGGER.info("Updating rule name");
		return Optional.of(ruleNameService.updateRuleName(ruleName));
	}

	@DeleteMapping(value = "/ruleName")
	public void deleteRuleName(@RequestParam Integer id) {
		if (id == null) {
			LOGGER.error("The id must be fielded.");
		} else {
			LOGGER.info("Deleting rule name");
			ruleNameService.deleteRuleName(id);
		}
	}

}
