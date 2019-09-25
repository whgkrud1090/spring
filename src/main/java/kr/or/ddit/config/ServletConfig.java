package kr.or.ddit.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.or.ddit.view.FileDownloadView;

@Configuration
/*
 <context:component-scan base-package="kr.or.ddit" use-default-filters="false">
	<context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
	<context:include-filter type="annotation" expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
 </context:component-scan>
 */
@ComponentScan(basePackages = "kr.or.ddit", useDefaultFilters = false, 
		includeFilters = @Filter(type = FilterType.ANNOTATION, 
		classes = {ControllerAdvice.class, Controller.class}))
@EnableWebMvc	//<mvc:aonnotation-driven/>과 동일
public class ServletConfig extends WebMvcConfigurerAdapter{

	//servlet-context 성능 개선
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	/*
 	<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
		<property name="order" value="0"/>
	</bean>
	*/
	public ViewResolver beanNameViewResolver() {
		
		//<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
		BeanNameViewResolver viewResolver = new BeanNameViewResolver();
		
		//<property name="order" value="0"/>
		viewResolver.setOrder(1);
		
		return viewResolver;
	}
	
	@Bean
	/*
 	<bean name="jsonView" class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
	*/
	public View jsonView() {
		return new MappingJackson2JsonView();
	}

	@Bean
	/*
 	<bean name="fileDownloadView" class="kr.or.ddit.view.FileDownloadView"/>
	*/
	public View fileDownloadView() {
		return new FileDownloadView();
	}
	
	@Bean
	/*
 	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="order" value="1"/>	<!-- value가 낮을수록 우선순위가 높다. -->
		<property name="prefix" value="/WEB-INF/views/"/>	<!-- 접두어 -->
		<property name="suffix" value=".jsp"/>	<!-- 접미어 -->
	</bean>
	 */
	public ViewResolver internalResourceViewResolver() {
		
		//<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		//<property name="order" value="1"/>
		//<property name="prefix" value="/WEB-INF/views/"/>
		//<property name="suffix" value=".jsp"/>
		viewResolver.setOrder(2);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Bean
	/*
 	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSize" value="26214400"/>	<!-- 5mb -->
		<property name="maxUploadSizePerFile" value="5242880"/>	<!-- 25mb -->
	</bean>
	 */
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(26214400);
		multipartResolver.setMaxUploadSizePerFile(5242880);
		
		return multipartResolver;
	}

	@Bean
	/*
 	<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
		<property name="basenames">
			<list>
				<value>classpath:kr/or/ddit/config/msg/error</value>
			</list>
		</property>
	</bean>
	 */
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		//setBasenames({},{} ... ) 여러개 설정이 가능하다.
		messageSource.setBasenames("classpath:kr/or/ddit/config/msg/error","classpath:kr/or/ddit/config/msg/message");
		return messageSource;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	@Bean
	public ViewResolver tilesViewResolver() {
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setOrder(0);
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigrer = new TilesConfigurer();
		tilesConfigrer.setDefinitions("classpath:kr/or/ddit/config/tiles/tiles-config.xml");
		return tilesConfigrer;
	}
}
