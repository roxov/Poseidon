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
import com.nnk.springboot.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerIT {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Integer userId;

	@BeforeEach
	public void setUp() throws JsonProcessingException, Exception {
		User user = new User("username", "PASSword1+", "fullname", "USER");
		String jsonResponse = mockMvc.perform(post("/rest/user").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andReturn().getResponse().getContentAsString();

		userId = JsonPath.parse(jsonResponse).read("$.id");
	}

	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	void givenAUser_whenPostUser_thenReturns200AndUser() throws Exception {
		User user = new User("username1", "PASSword1+", "fullname1", "USER");
		mockMvc.perform(post("/rest/user").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username1"));
	}

	@WithMockUser(username = "admin", roles = { "ADMIN" })
	@Test
	public void givenAnId_whenGetUser_thenReturnOkAndUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/user?id={id}", userId).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username"));
	}

	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	void givenAUser_whenPutUser_thenReturns200AndUpdatedUser() throws Exception {
		User user = new User(userId, "username2", "PASSword1+", "fullname2", "USER");
		mockMvc.perform(put("/rest/user").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username2"));
	}

	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	void givenAUser_whenDeleteUser_thenReturns200() throws Exception {
		mockMvc.perform(delete("/rest/user?id={id}", userId)).andExpect(status().isOk());
	}

}
