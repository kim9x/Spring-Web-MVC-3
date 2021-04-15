package me.pulpury.demowebmvc;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class EventControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void eventForm() throws Exception {
		MockHttpServletRequest request = mockMvc.perform(get("/events/form"))
				.andDo(print())
				.andExpect(view().name("/events/form"))
				.andExpect(model().attributeExists("event"))
				.andExpect(request().sessionAttribute("event", notNullValue()))
				.andReturn().getRequest();
		
  		Object event = request.getSession().getAttribute("event");
  		System.out.println(event);
	}
	
	@Test
	public void postEvent() throws Exception {
		ResultActions result = mockMvc.perform(post("/events")
					.param("name", "taeju")
					.param("limit", "-10"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
			ModelAndView mav = result.andReturn().getModelAndView();
			Map<String, Object> model = mav.getModel();
			System.out.println(model.size());
	}
	
	@Test
	public void getEvents() throws Exception {
		Event newEvent = new Event();
		newEvent.setName("Winter is coming.");
		newEvent.setLimit(10000);
		
		mockMvc.perform(get("/events/list")
				.sessionAttr("visitTime", LocalDateTime.now())
				.flashAttr("newEvent", newEvent))
					.andDo(print())
					.andExpect(status().isOk());
	}

}
