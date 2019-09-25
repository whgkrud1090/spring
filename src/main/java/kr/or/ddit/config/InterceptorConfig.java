package kr.or.ddit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import kr.or.ddit.interceptor.PerformanceCheckInterceptor;
import kr.or.ddit.interceptor.SessionCheckInterceptor;

@Configuration
@EnableWebMvc
public class InterceptorConfig extends WebMvcConfigurerAdapter{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		PerformanceCheckInterceptor performanceCheckInterceptor = new PerformanceCheckInterceptor();
		registry.addInterceptor(performanceCheckInterceptor).addPathPatterns("/**");
		
//		SessionCheckInterceptor sessionCheckInterceptor = new SessionCheckInterceptor();
//		registry.addInterceptor(sessionCheckInterceptor)
//				.addPathPatterns("/**")
//				.addPathPatterns("/login")
//				.addPathPatterns("/css/**")
//				.addPathPatterns("/js/**")
//				.addPathPatterns("/bootstrap/**")
//				.addPathPatterns("/error/**");
		
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		registry.addInterceptor(localeChangeInterceptor)
				.addPathPatterns("/**")
				.addPathPatterns("/login")
				.addPathPatterns("/css/**")
				.addPathPatterns("/js/**")
				.addPathPatterns("/bootstrap/**")
				.addPathPatterns("/error/**");
	}
}
