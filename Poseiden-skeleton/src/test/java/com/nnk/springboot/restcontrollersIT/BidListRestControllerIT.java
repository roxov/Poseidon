package com.nnk.springboot.restcontrollersIT;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.restcontrollers.BidListRestController;
import com.nnk.springboot.service.BidListService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BidListRestController.class)
public class BidListRestControllerIT {
	@MockBean
	private BidListService bidListService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void givenABidList_whenPostBidList_thenReturns200() throws Exception {
		BidList bidList = new BidList("account", "type", 2.2);
		when(bidListService.addBidList(bidList)).thenReturn(bidList);
		mockMvc.perform(post("/bidList").content(objectMapper.writeValueAsString(bidList))).andExpect(status().isOk());
	}

	@Test
	void givenABidListWithoutAccount_whenPostBidList_thenReturnsBadRequest() throws Exception {
		BidList bidList = new BidList(null, "type", 2.2);
		mockMvc.perform(post("/bidList").content(objectMapper.writeValueAsString(bidList)))
				.andExpect(status().isBadRequest());
	}
}
