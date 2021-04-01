package me.pulpury.demowebmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SampleControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void helloTest() throws Exception {
		
		mockMvc.perform(get("/hello")
					.param("name", "taeju"))
				.andDo(print())
				.andExpect(status().isOk());
		
//		mockMvc.perform(get("/hi"))
//		.andDo(print())
//		.andExpect(status().isOk());
		
//		mockMvc.perform(put("/hello"))
//		.andDo(print())
//		.andExpect(status().isMethodNotAllowed());
	}

}
