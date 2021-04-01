package me.pulpury.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 만약 Class와 Method 모두 consumes와 produces 사용 시
// method에서 overinding해서 덮어쓰기 해버림.
public class SampleController {
	
	// Header가 불일치하는 경우 
//	@RequestMapping(value = "/hello", headers = "!"+HttpHeaders.FROM)
	// Header가 일치하는 경우
//	@RequestMapping(value = "/hello", headers = HttpHeaders.AUTHORIZATION + "=" + "111")
	// Name이라는 Parameter가 있어야하는 경우
//	@RequestMapping(value = "/hello", params = "taeju")
	// Parameter가 일치하는 경우
	@RequestMapping(value = "/hello", params = "name=taeju")
	@ResponseBody
	public String hello() {
		return "hello";
	}

}
