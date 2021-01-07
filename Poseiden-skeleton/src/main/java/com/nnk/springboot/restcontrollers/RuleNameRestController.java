package com.nnk.springboot.restcontrollers;

import java.util.List;

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
import com.nnk.springboot.repositories.RuleNameRepository;

@RestController
public class RuleNameRestController {
	private static final Logger LOGGER = LogManager.getLogger(RuleNameRestController.class);

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@GetMapping(value = "/ruleName")
	public List<RuleName> findAllByName(@RequestParam String name) {
		LOGGER.info("Getting rule name identified by name");
		return ruleNameRepository.findAllByName(name);
	}

	@PostMapping(value = "/ruleName")
	public RuleName addRuleName(@RequestBody RuleName ruleName) {
		LOGGER.info("Adding new rule name");
		return ruleNameRepository.save(ruleName);
	}

	@PutMapping(value = "/ruleName")
	public RuleName updateRuleName(@RequestBody RuleName ruleName) {
		LOGGER.info("Updating rule name");
		return ruleNameRepository.save(ruleName);
	}

	@DeleteMapping(value = "/ruleName")
	public void deleteRuleName(@RequestParam String name) {
		LOGGER.info("Deleting rule name");
		ruleNameRepository.deleteByName(name);
	}

}
