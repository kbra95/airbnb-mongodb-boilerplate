package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.domain.ListingsAndReviews;
import com.example.demo.json.DetailAttribute;
import com.example.demo.services.HomeAttributeService;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AirbnbMongodbApplicationTests {

	@MockBean
	private HomeAttributeService service;
	
	@MockBean
	private ListingsAndReviews lis;
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void getContext() {
		
	}
	@Test
	void getDetailAttributeByIdTest() throws Exception{
		//setup mock object
		DetailAttribute det = Mockito.mock(DetailAttribute.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		//setup mock service
		doReturn(Optional.of(det)).when(service).getDetailAttribute(request, "1");
		
		//execute get method
		mockMvc.perform(get("/airbnb/detailattribute/{id}" ,1))
				
				//validate response code and content type
				.andExpect(status().isOk())
				
				//validte fields
				.andExpect(jsonPath("$.id", is("1")));
	}

}
