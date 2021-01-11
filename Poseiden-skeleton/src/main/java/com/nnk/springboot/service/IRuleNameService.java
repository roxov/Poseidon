package com.nnk.springboot.service;

import java.util.Optional;

import com.nnk.springboot.domain.RuleName;

/**
 * 
 * Manage the CRUD operations concerning rule names.
 *
 */
public interface IRuleNameService {
	public RuleName addRuleName(RuleName ruleName);

	public Optional<RuleName> findById(Integer id);

	public RuleName updateRuleName(RuleName ruleName);

	public void deleteRuleName(Integer id);
}
