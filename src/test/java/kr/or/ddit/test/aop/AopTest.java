package kr.or.ddit.test.aop;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
{"classpath:kr/or/ddit/spring/ioc/Component-scan-test.xml"		//test resource
,"classpath:kr/or/ddit/config/spring/context-datasource-test.xml"})	//test resource
public class AopTest {

	@Resource(name="userService")
	private IUserService userService;
	
 	@Test
	public void aopTest() {
 		/***Given***/

		/***When***/
 		userService.getUserList();
 		
		/***Then***/
 		assertNotNull(userService);
	}

}
