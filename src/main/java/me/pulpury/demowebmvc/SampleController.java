package me.pulpury.demowebmvc;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	// 이러한 형식으로 받을 수도 있다.
	@PostMapping("/events/name/{name}")
	@ResponseBody
	// BindingResult bindingResult 앞에처럼 처리해주면 'bindingResult' 해당 변수에
	// binding과 관련된 에러가 담겨져 온다.
	// binding 관련에러(binding Exception)를 던져주는게 아니라 일단 담겨져 오
	// 처리는 된다.(일단은..)
	// '@ModelAttribute' 또한 생략 가능하다.
	// 복합 타입의 경우 Spring Web Mvc가 알아서 @ModelAttribute가 붙어있는 것이라고 생각한다.
	// @Valid는 그룹을 지정할 수 없다.
	// @Validated는 class 등을 설정할 수 있다.(ex. @Validated(Event.ValidatedLimit.class))
	// 물론 그냥 사용하게된다면 @Valid랑 동일하게도 사용 가능하다.
	public Event getEvent(@Validated(Event.ValidatedName.class) @ModelAttribute Event event, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			 logger.info("==============================");
			 bindingResult.getAllErrors().forEach(c -> {
//				 System.out.println(c.toString());
				 logger.info(c.toString());
			 });
			 logger.info("==============================");
		}
		
		return event;
		
	}
}
