package me.pulpury.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 만약 Class와 Method 모두 consumes와 produces 사용 시
// method에서 overinding해서 덮어쓰기 해버림.
@RequestMapping(
		consumes = MediaType.APPLICATION_XML_VALUE
		, produces = MediaType.TEXT_PLAIN_VALUE)
public class SampleController {
	
//	cousumes는 특정한 타입의 데이터를 담고 있는 요청만 처리하는 핸들러
//	Content-Type 헤더로 필터링
//	ex) @RequestMapping(consumes="applicationn/json")
	@RequestMapping(
			value = "/hello"
			, consumes = MediaType.APPLICATION_JSON_VALUE
			, produces = MediaType.TEXT_PLAIN_VALUE
	)
	@ResponseBody
	public String hello() {
		return "hello";
	}

}
