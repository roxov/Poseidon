package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleNameServiceTest {

	@Autowired
	private RuleNameService ruleNameService;

	@MockBean
	private RuleNameRepository ruleNameRepository;

	@Test
	public void givenAName_whenFindAllByOrderNumber_thenReturnListWithTheRating() {
		// GIVEN
		RuleName ruleName = new RuleName(1, "name", "description", "json", "template", "sqlStr", "sqlPart");
		when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));

		// WHEN
		Optional<RuleName> result = ruleNameService.findById(1);

		// THEN
		verify(ruleNameRepository, Mockito.times(1)).findById(1);
		assertEquals("name", result.get().getName());
		assertEquals("description", result.get().getDescription());
		assertEquals("json", result.get().getJson());
		assertEquals("template", result.get().getTemplate());
		assertEquals("sqlStr", result.get().getSqlStr());
		assertEquals("sqlPart", result.get().getSqlPart());
	}

	@Test
	public void givenARuleName_whenAddRuleName_thenReturnCreatedRuleName() {
		// GIVEN
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);

		// WHEN
		RuleName result = ruleNameService.addRuleName(ruleName);

		// THEN
		verify(ruleNameRepository, Mockito.times(1)).save(any(RuleName.class));
		assertEquals("name", result.getName());
		assertEquals("description", result.getDescription());
		assertEquals("json", result.getJson());
		assertEquals("template", result.getTemplate());
		assertEquals("sqlStr", result.getSqlStr());
		assertEquals("sqlPart", result.getSqlPart());
	}
}
