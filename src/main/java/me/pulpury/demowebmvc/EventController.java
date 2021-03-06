package me.pulpury.demowebmvc;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({"event", "book"})
public class EventController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
//	@ExceptionHandler
//	public String eventErrorHandler(EventException exception, Model model) {
//		model.addAttribute("message", "event error");
//		return "error";
//	}
	
	// 해당 컨트롤러 안에서 처리 됨.
//	@ExceptionHandler({EventException.class, RuntimeException.class})
//	public String eventErrorHandler(RuntimeException exception, Model model) {
//		model.addAttribute("message", "runtime error2");
//		return "error";
//	}
	
	
//	@ExceptionHandler
//	public String runtimeErrorHandler(RuntimeException exception, Model model) {
//		model.addAttribute("message", "runtime error");
//		return "error";
//	}
	
//	@Autowired
//	EventValidator eventValidator;
	
	// @InitBinder("event")
	// initBinder에 'event' 같이 value를 주면
	// 'event' 이름의 modelAttribute를 binding 받을 때에만
	// 해당 @InitBinder를 사용하도록 설정할 수 있다.
	// 해당 컨트롤러 안에서 처리됨.
//	@InitBinder
//	// WebDataBinder를 파라미터로 받을 수 있다.
//	public void initEventBinder(WebDataBinder webDataBinder) {
//		// 받고 싶지 않은 field를 설정 가능
//		// form에서 id를 받더라도 걸러내줌. 
//		webDataBinder.setDisallowedFields("id");
////		webDataBinder.addValidators(new EventValidator());
//		// Validator를 bean으로 등록 후 주입을 받아서 사용할 수도 있다.
//	}
	
//	@ModelAttribute
//	public void categories(Model model) {
//		model.addAttribute("categories", List.of("study", "seminar", "hobby", "social"));
//	}
	
//	@ModelAttribute
//	public List<String> categories(Model model) {
//		return List.of("study", "seminar", "hobby", "social");
//	}
	
	@GetMapping("/events/form/name")
	public String eventsFormName(Model model, HttpSession httpSession) {
		
		// 가장 구체적인 에러를 발생시킨다.
		// 따라서 eventErrorHandler()가 호출됐었음.
		throw new EventException();
		
//		model.addAttribute("event", new Event());
//		return "/events/form-name"; 
	}
	
	// 보통 이렇게 사용한다.
//	@GetMapping("/events/form/name")  
//	@ModelAttribute
//	public String eventsFormName(Model model) {
//		model.addAttribute("event", new Event());
//		return "/events/form-name";
//	}
	
//	@GetMapping("/events/form/name")  
//	@ModelAttribute
	// @ModelAttribute 생략 가능.
	// String으로 return하지 않고(view name 생략)
	// 요청 값에 맞는 view를 찾아준다.(RequestToViewNameTranslator 인터페이스 사용됨.)
	// 
//	public Event eventsFormName() {
//		return new Event();
//	}
	
	@PostMapping("/events/form/name")
	public String eventsFormNameSubmit(
			@Validated @ModelAttribute Event event
			, BindingResult bindingResult
//			, Model model
			) {
		if (bindingResult.hasErrors()) {
			return "/events/form-name";
		}
		
		// 이렇게 사용할꺼면 EventValidator에서 OverRiding해서 사용할 필요가 없어진다.
//		eventValidator.validate(event, bindingResult);
		
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
