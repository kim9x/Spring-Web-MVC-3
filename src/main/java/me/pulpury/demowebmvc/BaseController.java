package me.pulpury.demowebmvc;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

// 모든 컨트롤러에 적용됨.
@ControllerAdvice(assignableTypes = {EventController.class, EventApi.class})
public class BaseController {
	
	
	@ExceptionHandler({EventException.class, RuntimeException.class})
	public String eventErrorHandler(RuntimeException exception, Model model) {
		model.addAttribute("message", "runtime error2");
		return "error";
	}
	
	@InitBinder("event")
	// WebDataBinder를 파라미터로 받을 수 있다.
	public void initEventBinder(WebDataBinder webDataBinder) {
		// 받고 싶지 않은 field를 설정 가능
		// form에서 id를 받더라도 걸러내줌. 
		webDataBinder.setDisallowedFields("id");
		webDataBinder.addValidators(new EventValidator());
		// Validator를 bean으로 등록 후 주입을 받아서 사용할 수도 있다.
	}
	
	@ModelAttribute
	public void categories(Model model) {
		model.addAttribute("categories", List.of("study", "seminar", "hobby", "social"));
	}

}
