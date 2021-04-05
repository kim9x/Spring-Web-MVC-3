package me.pulpury.demowebmvc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	@PostMapping("/events")
	@ResponseBody
	public Event getEvent(
			// '@RequestParam'을 제외하고 String name이런 식으로 생략 또한 가능하다..?
			// 그러나 추천하지는 않음. 팀원들이 모두 다 Spring MVC에 익숙하지 않을 수도 있기 때문에
			// 명시적으로 선호해주는 것이 좋다.
			// 스타 개발자가 아닌 협업을 잘하는 개발자가 되자.
			@RequestParam String name
			, @RequestParam Integer limit
			
			// @RequestParam 또한 변수명을 맞춰주고 코드를 줄이거나
			// @ReauestParma(value = "name")을 사용해서 아예 다른 변수(ex. nameValue) 등을 
			// 사용할 수도 있다.
//			, @RequestParam(value = "name", required = false, defaultValue = "taeju") String nameValue
			
			// Map으로 전달받고 event.setName(params.get("name"));
			// 이런식으로 알고 있는 변수명으로 사용할 수도 있다.
//			, @RequestParam Map<String, String> params
			) {
		Event event = new Event();
		event.setName(name);
		event.setLimit(limit);
		logger.info("event", event);
//		event.setName(params.get("name"));
		return event;
		
	}
}
