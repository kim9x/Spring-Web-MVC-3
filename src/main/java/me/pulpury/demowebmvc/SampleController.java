package me.pulpury.demowebmvc;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SampleController {
	
	@GetMapping("/events/{id}")
	@ResponseBody
	public Event getEvent(
			
			// {id}와 @PathVariable의 변수명(ex. 바로 밑의 Integer id)을 맞춰주면
			// 따로 설정없이도 사용이 가능하다.
			@PathVariable Integer id
			// 아래처럼 {id} 값을 @PathVariable("id")로 명시해주면
			// idValue같은 다른 변수명을 사용할 수 있다.
//			, @PathVariable("id") Integer idValue
			, @MatrixVariable String name) {
		Event event = new Event();
		event.setId(id);
//		event.setId(idValue);
		event.setName(name);
		return event;
		
	}
}
