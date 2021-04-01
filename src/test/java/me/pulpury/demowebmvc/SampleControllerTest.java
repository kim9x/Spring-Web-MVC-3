package me.pulpury.demowebmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
				// header중 From의 값을 localhost로 보내준다.
//					.header(HttpHeaders.FROM, "localhost" ))
				// header의 Authorizationn값을 111로 보내준다.
//					.header(HttpHeaders.AUTHORIZATION, "111"))
//				 parameter의 name을 name, value를 taeju로 보내준다.
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
