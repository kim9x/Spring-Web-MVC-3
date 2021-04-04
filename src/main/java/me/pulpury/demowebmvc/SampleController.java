package me.pulpury.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
	 
	// @GetMapping처럼 Annotation이 모여서 만들어 진 것 Composed Annotation이라고 부를 수 있다.
	// @GetMapping을 들여다보면 @RequestMapping 등등이 있는데 이렇게
	// Annotationn 위에 사용하는 Annotation을 Meta Annotation이라고 부릅니다.
//	@GetMapping("/hello")
	// Custom하여 사용할 수 있다.
	@GetHelloMapping
	@ResponseBody
	public String helloGet() {
		return "hello";
	}
	
//	@RequestMapping("/hello")
//	@ResponseBody
//	public String hello() {
//		return "hello";
//	}

}
