package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
public class RuleNameRestControllerTest {

	@Autowired
	private RuleNameRestController ruleNameRestController;

	@MockBean
	private RuleNameRepository ruleNameRepository;

	@Test
	public void givenAName_whenFindAllByOrderNumber_thenReturnListWithTheRating() {
		// GIVEN
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		List<RuleName> ruleNameList = new ArrayList<>();
		ruleNameList.add(ruleName);
		when(ruleNameRepository.findAllByName("name")).thenReturn(ruleNameList);

		// WHEN
		List<RuleName> result = ruleNameRestController.findAllByName("name");

		// THEN
		verify(ruleNameRepository, Mockito.times(1)).findAllByName("name");
		assertEquals("description", result.get(0).getDescription());
		assertEquals("json", result.get(0).getJson());
		assertEquals("template", result.get(0).getTemplate());
		assertEquals("sqlStr", result.get(0).getSqlStr());
		assertEquals("sqlPart", result.get(0).getSqlPart());
	}

	@Test
	public void givenARuleName_whenAddRuleName_thenReturnCreatedRuleName() {
		// GIVEN
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);

		// WHEN
		RuleName result = ruleNameRestController.addRuleName(ruleName);

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
