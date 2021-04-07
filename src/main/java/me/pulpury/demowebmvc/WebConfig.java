package me.pulpury.demowebmvc;

import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

public class WebConfig implements WebMvcConfigurer {

	
	// @MatrixVariable 사용을 위해 세미콜론을 없애는 것을 false로 설정해줘야한다.
	// 즉 @MatrixVariable를 사용하려면 밑의 설정이 꼭 필요하다.
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(new VisitTimeInterceptor());
	}
	
	

}
