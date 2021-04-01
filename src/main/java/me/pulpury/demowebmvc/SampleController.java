package me.pulpury.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
	 
	@GetMapping("/hello")
	@ResponseBody
	public String helloGet() {
		return "hello";
	}
	
	@PostMapping("/hello")
	@ResponseBody
	public String helloPost() {
		return "hello";
	}
	
//	@RequestMapping("/hello")
//	@ResponseBody
//	public String hello() {
//		return "hello";
//	}

}
