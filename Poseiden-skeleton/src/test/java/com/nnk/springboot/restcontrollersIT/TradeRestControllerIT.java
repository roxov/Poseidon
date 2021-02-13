package com.nnk.springboot.restcontrollersIT;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.Trade;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@ContextConfiguration
//@ContextConfiguration("/application.properties")
//@WebAppConfiguration
//@WebMvcTest(controllers = TradeRestController.class)
public class TradeRestControllerIT {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//		  final Authentication authentication = new TestingAuthenticationToken("celine.gilet", "netapsys");
//		  final SecurityContext securityContext = new SecurityContextImpl();
//		  securityContext.setAuthentication(authentication);
//		  SecurityContextHolder.setContext(securityContext);
	}

//	@Test
//	public void givenWac_whenServletContext_thenItProvidesGreetController() {
//		ServletContext servletContext = wac.getServletContext();
//
//		Assert.assertNotNull(servletContext);
//		Assert.assertTrue(servletContext instanceof MockServletContext);
//		Assert.assertNotNull(wac.getBean("greetController"));
//	}

//	@MockBean
//	private TradeService tradeService;
//
//	@MockBean
//	private UserRepository userRepository;
//
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@WithMockUser
	void givenATrade_whenPostTrade_thenReturns200AndTrade() throws Exception {
		Trade trade = new Trade("account", "type");
		// when(tradeService.addTrade(trade)).thenReturn(trade);
		mockMvc.perform(post("/rest/trade").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(trade))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.account").value("account"));
	}
//
//	@WithMockUser
//	@Test
//	public void givenAnId_whenGetTrade_thenReturnOk() throws Exception {
//		when(tradeService.findById(1)).thenReturn(Optional.of(new Trade("account", "type")));
//		mockMvc.perform(MockMvcRequestBuilders.get("/rest/trade?tradeId={id}", 1).accept(MediaType.APPLICATION_JSON))
//				.andDo(print()).andExpect(status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.account").value("account"));
//	}
//
//	@Test
//	@WithMockUser
//	void givenATrade_whenPutTrade_thenReturns200AndUpdatedTrade() throws Exception {
//		Trade trade = new Trade(1, "account", "type");
//		when(tradeService.updateTrade(trade)).thenReturn(trade);
//		mockMvc.perform(put("/rest/trade").contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(trade))).andExpect(status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.account").value("account"));
//	}

	@Test
	@WithMockUser
	void givenATrade_whenDeleteTrade_thenReturns200() throws Exception {
		// doNothing().when(tradeService).deleteTrade(1);
		mockMvc.perform(delete("/rest/trade?tradeId={id}", 1)).andExpect(status().isOk());
	}
}
