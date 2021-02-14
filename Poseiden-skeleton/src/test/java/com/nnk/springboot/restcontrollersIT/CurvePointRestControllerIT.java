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
import com.nnk.springboot.domain.CurvePoint;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurvePointRestControllerIT {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Integer curvePointId;

	@BeforeEach
	public void setUp() throws JsonProcessingException, Exception {
		CurvePoint curvePoint = new CurvePoint(1, 1.1, 2.2);
		String jsonResponse = mockMvc
				.perform(post("/rest/curvePoint").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(curvePoint)))
				.andReturn().getResponse().getContentAsString();

		curvePointId = JsonPath.parse(jsonResponse).read("$.id");
	}

	@Test
	@WithMockUser
	void givenACurvePoint_whenPostCurvePoint_thenReturns200AndCurvePoint() throws Exception {
		CurvePoint curvePoint = new CurvePoint(2, 1.1, 2.2);
		mockMvc.perform(post("/rest/curvePoint").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(curvePoint))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.curveId").value(2));
	}

	@WithMockUser
	@Test
	public void givenAnId_whenGetCurvePoint_thenReturnOkAndCurvePoint() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/rest/curvePoint?id={id}", curvePointId).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.curveId").value(1));
	}

	@Test
	@WithMockUser
	void givenACurvePoint_whenPutCurvePoint_thenReturns200AndUpdatedCurvePoint() throws Exception {
		CurvePoint curvePoint = new CurvePoint(curvePointId, 3, 1.1, 2.2);
		mockMvc.perform(put("/rest/curvePoint").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(curvePoint))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.curveId").value(3));
	}

	@Test
	@WithMockUser
	void givenACurvePoint_whenDeleteCurvePoint_thenReturns200() throws Exception {
		mockMvc.perform(delete("/rest/curvePoint?id={id}", curvePointId)).andExpect(status().isOk());
	}
}
