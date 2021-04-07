package me.pulpury.demowebmvc;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
//@SessionAttributes({"event", "book"})
public class SampleController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/events/form/name")
	public String eventsFormName(Model model, HttpSession httpSession) {
		model.addAttribute("event", new Event());
		return "/events/form-name"; 
	}
	@PostMapping("/events/form/name")
	public String eventsFormNameSubmit(
			@Validated @ModelAttribute Event event
			, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/events/form-name";
		}
		return "redirect:/events/form/limit";
		
	}
	
	@GetMapping("/events/form/limit")
	public String eventsFormLimit(@ModelAttribute Event event, Model model) {
		model.addAttribute("event", event);
		return "/events/form-limit"; 
	}
	@PostMapping("/events/form/limit")
	public String eventsFormLimitSubmit(
			@Validated @ModelAttribute Event event
			, BindingResult bindingResult
			, SessionStatus sessionStatus) {
		if (bindingResult.hasErrors()) {
			return "/events/form-limit";
		}
		
		sessionStatus.setComplete();
		return "redirect:/events/list";
		
	}
	
	@GetMapping("/events/list")
//	public String getEvents(Model model, @SessionAttribute LocalDateTime visitTime) {
	public String getEvents(Model model, HttpSession httpSession) {
//		logger.info("{}", visitTime);
		LocalDateTime visitTime = (LocalDateTime) httpSession.getAttribute("visitTime");
		logger.info("{}", visitTime);
		
		Event event = new Event();
		event.setName("spring");
		event.setLimit(10);
		
		List<Event> eventList = new ArrayList<>();
		eventList.add(event);
		model.addAttribute("eventList", eventList);
		
		return "/events/list";
	}
}
