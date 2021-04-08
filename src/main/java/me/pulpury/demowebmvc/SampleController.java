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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({"event", "book"})
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
			, BindingResult bindingResult
//			, Model model
			) {
		if (bindingResult.hasErrors()) {
			return "/events/form-name";
		}
//		model.addAttribute("event", event);
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
			, SessionStatus sessionStatus
			, Model model
			, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return "/events/form-limit";
		}
		
		sessionStatus.setComplete();
		
		// Model의 Attribute에 넣어두면 spring Web Mvc는 자동적으로 
		// 리다이렉트 되는 곳의 쿼리 파라미터로 추가가되지만,
		// Spring boot는 자동적으로 꺼져있다.
		// application.properties에서
		// 'spring.mvc.ignore-default-model-on-redirect=false' 설정해주면 사용가능.
//		model.addAttribute("name", event.getName());
//		model.addAttribute("limit", event.getLimit());
		
		// 아래처럼 사용하면 주고싶은 값만 ?뒤의 쿼리파타미터로 넘겨줄 수 있다.
		// ex) ?name=event.getName()
		attributes.addAttribute("name", event.getName());
		attributes.addAttribute("limit", event.getLimit());
		
		return "redirect:/events/list";
		
	}
	
	@GetMapping("/events/list")
//	public String getEvents(Model model, @SessionAttribute LocalDateTime visitTime) {
	public String getEvents(
			// @RequestParam으로 가져올 수도 있
			// @ModelAttribute로 가져올 수도 있다.
//			@RequestParam String name
//			, @RequestParam Integer limit
			// @ModelAttribute로 가져올 때 주의할 점
			// session에 저장된 이름과 같이 사용하면 안된다.
			// 왜냐? 세션에서 찾기 때문에.
			// 그리고 우리는 'eventsFormLimitSubmit' 메서드에서
			// 세션을 비워줬다.!
			@ModelAttribute("newEvent") Event event
			, Model model
//			, @SessionAttribute LocalDateTime visitTime
			) {
//		logger.info("{}", visitTime);
//		LocalDateTime visitTime = (LocalDateTime) httpSession.getAttribute("visitTime");
//		logger.info("{}", visitTime);
		
//		System.out.println(visitTime);
		
//		Event newEvent = new Event();
//		newEvent.setName(event.getName());
//		newEvent.setLimit(event.getLimit());
		
		Event spring = new Event();
		event.setName("spring");
		event.setLimit(10);
		
		List<Event> eventList = new ArrayList<>();
		eventList.add(spring);
		eventList.add(event);
//		eventList.add(newEvent);
		
		model.addAttribute("eventList", eventList);
		
		return "/events/list";
	}
}
