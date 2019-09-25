package kr.or.ddit.test.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

// 자바 클래스를 스프링 설정파일로 만드는 방법
// 1. @Configuration 어노테이션 클래스에 적용
// 2. @Bean 어노테이션이 적용된 메서드를 선언 : 리턴되는 값이 스프링 빈
@ImportResource(locations = {"classpath:kr/or/ddit/spring/ioc/Component-scan-test.xml", "classpath:kr/or/ddit/config/spring/context-datasource-test.xml"})
@Configuration
@EnableWebMvc
public class ApplicationJavaConfigT1 {
	
	// userDao, userService 스프링으로 생성
	
	// 기본적으로 메서드이름이 스프링 빈의 이름이 된다.
	// 기본이름이 아닌 별도의 이름을 부여 하고싶으면 @Bean(name="") 속성에 값을 설정
	@Bean
	public IUserDao userDao() {
		return new UserDao();
	}
	
	@Bean
	public IUserService userService() {
		return new UserService(userDao());
	}
}
