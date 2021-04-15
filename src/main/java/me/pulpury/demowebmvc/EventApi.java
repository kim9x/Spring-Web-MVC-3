package me.pulpury.demowebmvc;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventApi {
	
	@PostMapping
	// @RequestBody와 비슷하지만 추가적으로 헤더정보를 사용할 수 있다.
	// @RequestBody는 body에 접근 가능.
//	public Event createEvent(@RequestBody Event  event) {
	public Event createEvent(HttpEntity<Event>  request) {
		// save event
	MediaType contentType = request.getHeaders().getContentType();
	System.out.println(contentType);
		
		return request.getBody();
	}
}
