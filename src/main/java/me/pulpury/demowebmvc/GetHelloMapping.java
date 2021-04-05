package me.pulpury.demowebmvc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// @Retention을 사용하여 해당 Annotation 정보를 언제까지 유지할 것인가 설정할 수 있다.
// RetentionPolicy.RUNTIME을 줘야 RUNTIME 시에 사용할 수 있다.
// RetentionPolicy.CLASS는 .class에는 남아 있지만.. RUNTIM 시엔 사라진다.
// RetentionPolicy.SOURCE는 소스코드 내에서만 볼 수 있으며.. compile하고 나서는 없어진다.
// => 주석처럼 사용하고 싶을 때 사용 가능.

// @Target을 사용하여 어디서 사용할 수 있는지 명시한다.

// @Documented는 문서화가 가능하다?
@Documented
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.GET, value = "/hello")
// @GetMapping은 Meta Annotation이 아니므로 사용하지 못한다.
public @interface GetHelloMapping {
	

}
