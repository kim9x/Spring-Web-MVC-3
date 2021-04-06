package me.pulpury.demowebmvc;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SampleController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/events/form")
	public String eventsForm(Model model) {
		Event newEvent = new Event();
		newEvent.setLimit(50);
		
		model.addAttribute("event", newEvent);
		return "/events/form"; 
	}
	@PostMapping("/events")
	public String createEvent(
			@Validated @ModelAttribute Event event
			, BindingResult bindingResult
			, Model model) {
		if (bindingResult.hasErrors()) {
			
			return "/events/form";
		}
		
		List<Event> eventList = new ArrayList<>();
		eventList.add(event);
		model.addAttribute("eventList", eventList);
		
		return "redirect:/events/list";
		
	}
	
	@GetMapping("/events/list")
	public String getEvents(Model model) {
		Event event = new Event();
		event.setName("spring");
		event.setLimit(10);
		
		List<Event> eventList = new ArrayList<>();
		eventList.add(event);
		model.addAttribute("eventList", eventList);
		
		return "/events/list";
	}
}
