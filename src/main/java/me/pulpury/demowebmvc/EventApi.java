package me.pulpury.demowebmvc;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@RequestMapping("/api/events")
public class EventApi {
	
	@PostMapping
	public ResponseEntity<Event> createEvent(
			@RequestBody @Valid Event event
			, BindingResult bindingResult) {
		// save event
		if (bindingResult.hasErrors()) {
			ResponseEntity.badRequest().build();
//			bindingResult.getAllErrors().forEach(error -> {
//				System.out.println(error);
//			});
		}
		
//		ResponseEntity<Event> responseEntity = new ResponseEntity<Event>;
		
		return ResponseEntity.ok(event);
	}
}
