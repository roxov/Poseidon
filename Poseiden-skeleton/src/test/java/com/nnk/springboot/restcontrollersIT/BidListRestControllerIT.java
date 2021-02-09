package com.nnk.springboot.restcontrollersIT;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.restcontrollers.BidListRestController;
import com.nnk.springboot.service.BidListService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BidListRestController.class)
public class BidListRestControllerIT {
	@MockBean
	private BidListService bidListService;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@WithMockUser
	void givenABidList_whenPostBidList_thenReturns200() throws Exception {
		BidList bidList = new BidList("account", "type", 2.2);
		when(bidListService.addBidList(bidList)).thenReturn(bidList);
		mockMvc.perform(post("/bidList").content(objectMapper.writeValueAsString(bidList))).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	void givenABidListWithoutAccount_whenPostBidList_thenReturnsBadRequest() throws Exception {
		BidList bidList = new BidList(null, "type", 2.2);
		mockMvc.perform(post("/bidList").content(objectMapper.writeValueAsString(bidList)))
				.andExpect(status().isBadRequest());
	}

//	@Test
//	@WithMockUser
//	public void  throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("rest/bidList?bidListId=1").andExpect(status().isOk());
//	}

	@Test
	public void givenAUser_whenGetBidList_thenReturnOk() throws Exception {
		User user = new User("user", "PASSword1+", "User", "USER");
		when(userRepository.findByUsername("user")).thenReturn(user);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/rest/bidList?bidListId={id}", 1).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$bidList.bidListId").value(1));
	}
}
