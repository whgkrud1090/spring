package kr.or.ddit.test.ioc;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationJavaConfigT1.class)
@WebAppConfiguration
public class ApplicationJavaConfigT1Test {
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	* Method : javaSpringBeanConfigTest
	* 작성자 : PC-21
	* 변경이력 :
	* Method 설명 : 자바파일을 이용한 스프링 빈 설정 테스트
	*/
	@Test
	public void javaSpringBeanConfigTest() {
		/***Given***/
		
		/***When***/

		/***Then***/
		assertNotNull(userDao);
		assertNotNull(userService);
	}

}
