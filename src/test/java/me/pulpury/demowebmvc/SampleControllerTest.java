package me.pulpury.demowebmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
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
		
		mockMvc.perform(get("/hello/taeju"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("hello taeju"))
				.andExpect(handler().handlerType(SampleController.class))
				.andExpect(handler().methodName("helloTaeju"));
		
//		mockMvc.perform(get("/hi"))
//		.andDo(print())
//		.andExpect(status().isOk());
		
//		mockMvc.perform(put("/hello"))
//		.andDo(print())
//		.andExpect(status().isMethodNotAllowed());
	}

}
