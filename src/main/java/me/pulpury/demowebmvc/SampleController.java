package me.pulpury.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// Class에 RequestMapping Annotation을 적용할 수도 있다.
// 해당 Class에 속해 있는 모는 Controller는 GET요청만 받을 수 있다. 
@RequestMapping(method = RequestMethod.GET)
public class SampleController {
	
	// @GetMappin에서 @RequestMapping의 method 값에 GET이 들어가 있는 것을 확인할 수 있다.
//	@RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.PUT})
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello";
	}

}
