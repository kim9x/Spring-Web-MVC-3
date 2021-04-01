package me.pulpury.demowebmvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SampleControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void helloTest() throws Exception {
		
		// HEAD요청은 GET요청과 동일하지만 응답 본문을 받아오지 않고 응답 헤더만 받아온다.
		// response에 body를 실어보내지 않는다.
		// GET요청으로 정보를 가져오기 전에
		// header정보로 resource의 정보를 확인만 하는 것이지 떄문
//		mockMvc.perform(head("/hello"))
		
		// options는 사용할 수 있는 HTTP Method 제공
		// 서버 또는 특정 리소스가 제공하는 기능 확인 가능
		// 서버에서 Allow 응답 헤더에 사용할 수 있는 HTTP Method 목록을 제공
		mockMvc.perform(options("/hello"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(header().exists(HttpHeaders.ALLOW))
				// 밑의 코드는 stringValues 때문에 순서까지 맞춰줘야한다.
				// 
//				.andExpect(header().stringValues(HttpHeaders.ALLOW, "GET", "POST", "OPTIONS", "HEAD"));
				
				//아래 코드는 순서가 상관이 없지만... 좀 번거롭..
				.andExpect(header().stringValues(HttpHeaders.ALLOW
						, hasItems(
									containsString("GET")
									, containsString("POST")
									, containsString("HEAD")
									, containsString("OPTIONS"))));
	}

}
