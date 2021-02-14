package com.nnk.springboot.restcontrollersIT;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.nnk.springboot.domain.RuleName;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RuleNameRestControllerIT {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Integer ruleNameId;

	@BeforeEach
	public void setUp() throws JsonProcessingException, Exception {
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		String jsonResponse = mockMvc
				.perform(post("/rest/ruleName").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(ruleName)))
				.andReturn().getResponse().getContentAsString();

		ruleNameId = JsonPath.parse(jsonResponse).read("$.id");
	}

	@Test
	@WithMockUser
	void givenARuleName_whenPostRuleName_thenReturns200AndRuleName() throws Exception {
		RuleName ruleName = new RuleName("name1", "description1", "json1", "template1", "sqlStr1", "sqlPart1");
		mockMvc.perform(post("/rest/ruleName").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ruleName))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name1"));
	}

	@WithMockUser
	@Test
	public void givenAnId_whenGetRuleName_thenReturnOkAndRuleName() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/rest/ruleName?id={id}", ruleNameId).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name"));
	}

	@Test
	@WithMockUser
	void givenARuleName_whenPutRuleName_thenReturns200AndUpdatedRuleName() throws Exception {
		RuleName ruleName = new RuleName(ruleNameId, "name2", "description", "json", "template", "sqlStr", "sqlPart");
		mockMvc.perform(put("/rest/ruleName").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ruleName))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name2"));
	}

	@Test
	@WithMockUser
	void givenARuleName_whenDeleteRuleName_thenReturns200() throws Exception {
		mockMvc.perform(delete("/rest/ruleName?id={id}", ruleNameId)).andExpect(status().isOk());
	}
}
