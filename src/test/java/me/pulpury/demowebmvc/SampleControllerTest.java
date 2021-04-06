package me.pulpury.demowebmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SampleControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void eventForm() throws Exception {
		mockMvc.perform(get("/events/form"))
				.andDo(print())
				.andExpect(view().name("/events/form"))
				.andExpect(model().attributeExists("event"));
	}
	
	@Test
	public void postEvent() throws Exception {
//		mockMvc.perform(post("/events?name=taeju"))
		// 위에 것이나 아래 것이나 똑같다.
		// 아래처럼 사용할거면 문자열만 줄 수 있다.
		mockMvc.perform(post("/events")
					.param("name", "taeju")
					.param("limit", "20"))
				.andDo(print())
				.andExpect(status().isOk())
//				.andExpect(jsonPath("id").value(1));
				.andExpect(jsonPath("name").value("taeju"));
				;
	}

}
