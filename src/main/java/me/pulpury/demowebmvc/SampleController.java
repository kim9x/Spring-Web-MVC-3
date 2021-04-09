package me.pulpury.demowebmvc;


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
		
		// addAttribute로 전달되려면 String으로 변환이 가능한 값이여야 한다.
		// 도메인 오브젝트 자체를 전달할 순 없다.
//		attributes.addAttribute("name", event.getName());
//		attributes.addAttribute("limit", event.getLimit());
		
		// httpSession을 통해 전달되며
		// redirect 된 곳에서 사용되면 자동적으로 사라진다?!
		// => 1회성이다. 그래서 flash라는 단어가 붙어진듯.
		// 경로등이 보이지 않아 좋다.
		attributes.addFlashAttribute("newEvent", event);
		
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
		spring.setName("spring");
		spring.setLimit(10);
		
		Object newEvent = model.asMap().get("newEvent");
		
		List<Event> eventList = new ArrayList<>();
		eventList.add(spring);
		
		// @ModelAttribute로 받을 수도 있
		eventList.add(event);
		
		// Model로도 받을 수 있다.
		// Object newEvent = model.asMap().get("newEvent");
		model.addAttribute(newEvent);
		
		
		
//		eventList.add(newEvent);
		
		model.addAttribute("eventList", eventList);
		
		return "/events/list";
	}
}
