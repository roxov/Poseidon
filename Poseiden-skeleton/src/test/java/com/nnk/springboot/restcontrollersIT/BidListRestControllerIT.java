package com.nnk.springboot.restcontrollersIT;

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
		// when(bidListService.addBidList(bidList)).thenReturn(bidList);
		// mockMvc.perform(post("/rest/bidList").content(objectMapper.writeValueAsString(bidList)))
		// .andExpect(status().isOk());
		mockMvc.perform(post("/rest/bidList").content(objectMapper.writeValueAsString(bidList)))
				.andExpect(status().isOk());
	}

	// POSTMAN renvoie une 400 si le champ est mauvais (un double ne peut pas être
	// parsé en Integer) mais pour les null il crée l'objet. Un PUT avec id null
	// renvoie un null mais un PUt ou POST avec un champ null alors qu'il devrait
	// être non null renvoie l'objet rempli d'un null
//	@Test
//	@WithMockUser
//	void givenABidListWithoutAccount_whenPostBidList_thenReturnsBadRequest() throws Exception {
//		BidList bidList = new BidList(null, "type", 2.2);
//		mockMvc.perform(post("/bidList").content(objectMapper.writeValueAsString(bidList)))
//				.andExpect(status().isBadRequest());
//	}

//	@Test
//	@WithMockUser
//	public void  throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("rest/bidList?bidListId=1").andExpect(status().isOk());
	// mockMvc.perform(MockMvcRequestBuilders.get("/rest/bidList?bidListId={id}",
	// 1).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$bidList.bidListId").value(1));
//	}.andExpect(MockMvcResultMatchers.jsonPath("$.bidListId").value(1))
	@WithMockUser
	@Test
	public void givenAUser_whenGetBidList_thenReturnOk() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/rest/bidList?bidListId={id}", 1).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.bidListId").value(1));
				.andExpect(MockMvcResultMatchers.jsonPath("$.account").value("account"));
	}
}
