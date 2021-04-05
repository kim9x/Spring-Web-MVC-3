package me.pulpury.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
	
	@GetMapping("/events")
	@ResponseBody
	public String events() {
		return "events";
	}
	
	@GetMapping("/events/{id}")
	@ResponseBody
	public String getAnEvents(@PathVariable("id") int id) {
		return null;
		
	}
	
	@DeleteMapping("/events/{id}")
	@ResponseBody
	public String removeAnEvents(@PathVariable("id") int id) {
		return null;
		
	}
	
	@GetHelloMapping
	@ResponseBody
	public String helloGet() {
		return "hello";
	}

}
