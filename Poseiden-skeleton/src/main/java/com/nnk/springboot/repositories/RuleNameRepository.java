package com.nnk.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.RuleName;

public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
	List<RuleName> findAllByName(String name);

	void deleteByName(String name);

}
