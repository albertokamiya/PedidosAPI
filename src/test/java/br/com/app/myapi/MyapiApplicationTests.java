package br.com.app.myapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.app.myapi.model.Client;

@SpringBootTest
@AutoConfigureMockMvc
class MyapiApplicationTests {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper object;
	
	
	@Test
	void contextLoads() {		
	}

	@Test
	void testAddClient() throws JsonProcessingException, Exception {
		Client client = new Client(0, "Name", "address", "phone");
		
		MvcResult res = mock.perform(post("/api/client")
				.contentType("application/json")
				.content(object.writeValueAsString(client)))
		.andExpect(status().isOk())
		.andReturn();
		
		Client newClient = object.readValue(res.getResponse().getContentAsString(), Client.class);
				
		URI uri = new URI("/api/client/" + newClient.getId());
		mock.perform(get(uri))
		.andExpect(status().isOk());
		
	}
	
}
