package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleNameRestControllerTest {
	@Autowired
	private RuleNameRestController ruleNameRestController;

	@MockBean
	private RuleNameService ruleNameService;

	@Test
	public void givenNullId_whenFindById_thenReturnEmptyOptional() {
		// WHEN
		Optional<RuleName> result = ruleNameRestController.findById(null);

		// THEN
		verify(ruleNameService, Mockito.times(0)).findById(null);
		assertEquals(Optional.empty(), result);
	}

	@Test
	public void givenANullId_whenUpdateRuleName_thenReturnEmptyOptional() {
		// GIVEN
		RuleName ruleName = new RuleName(null, "name", "description", "json", "template", "sqlStr", "sqlPart");

		// WHEN
		Optional<RuleName> result = ruleNameRestController.updateRuleName(ruleName);

		// THEN
		verify(ruleNameService, Mockito.times(0)).updateRuleName(ruleName);
		assertEquals(Optional.empty(), result);
	}
}
