package com.nnk.springboot.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {
	private static final Logger LOGGER = LogManager.getLogger(RuleNameService.class);

	@Autowired
	private RuleNameRepository ruleNameRepository;

	public RuleName addRuleName(RuleName ruleName) {
		LOGGER.info("Adding new rule name");
		return ruleNameRepository.save(ruleName);
	}

	public Optional<RuleName> findById(Integer id) {
		LOGGER.info("Getting rule name identified by id");
		return ruleNameRepository.findById(id);
	}

	public RuleName updateRuleName(RuleName ruleName) {
		LOGGER.info("Updating rule name");
		return ruleNameRepository.save(ruleName);
	}

	public void deleteRuleName(Integer id) {
		LOGGER.info("Deleting rule name");
		ruleNameRepository.deleteById(id);
	}
}
