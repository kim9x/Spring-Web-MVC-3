package me.pulpury.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// Class에 RequestMapping Annotation을 적용할 수도 있다.
// 해당 Class에 속해 있는 모는 Controller는 GET요청만 받을 수 있다. 
//@RequestMapping(method = RequestMethod.GET)

// Class에 매핑 가능
@RequestMapping("/hello")
public class SampleController {
	
	// @GetMappin에서 @RequestMapping의 method 값에 GET이 들어가 있는 것을 확인할 수 있다.
//	@RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.PUT})
	
	// 두개의 URI를 배열로 명시하여 2가지 이상 또한 가능하다.
//	@GetMapping({"/hello", "/hi"})
	
//	@RequestMapping은 패턴을 또한 지원한다. 
//	?: 한 글자 (“/author/???” => “/author/123”) 
//	*: 여러 글자 (“/author/*” => “/author/keesun”) 
//	**: 여러 패스 (“/author/** => “/author/keesun/book”) 
	
//	 @RequestMapping에 정규식도 가능
	
//	URI 확장자 매핑 지원
//	BUT 이 기능은 권장하지 않음
//	RFD Attack에 취약

	
//	아래 두개의 경우 "/hello/taeju" 호출할 경우 아래의 것이 호출된다.
//	가장 구체적으로 명시한 것이 호출 됨!
	@RequestMapping("/taeju")
	@ResponseBody
	public String helloTaeju() {
		return "hello taeju";
	}
	
	@RequestMapping("/**")
	@ResponseBody
	public String hello() {
		return "hello";
	}

}
