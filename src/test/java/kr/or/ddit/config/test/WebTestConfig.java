package kr.or.ddit.config.test;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.config.RootConfig;
import kr.or.ddit.config.ServletConfig;
import kr.or.ddit.config.spring.DatasourceConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//		//controller scan : servlet-context.xml
//		"classpath:kr/or/ddit/config/spring/context-root.xml",
//		"classpath:kr/or/ddit/config/spring/context-datasource-test.xml",
//		"classpath:kr/or/ddit/config/spring/context-transaction.xml",
//		"classpath:kr/or/ddit/config/spring/servlet-context.xml"
//		})	

@ContextConfiguration(classes = {ServletConfig.class, DatasourceConfigTest.class, RootConfig.class})
@WebAppConfiguration
public class WebTestConfig {

	//private(접근불가) - protected(상속받은) - default(같은 패키지 클래스) - public(제한없음)
	
	//controller를 테스트 하기 위해서 필요한것 2가지
	//applicationContext : 스프링 컨테이너
	//MockMvc : dispatcherServlet (applicationContext 객체를 통해 생성)
	
	//주입하려고하는 필드의 타입과 일치할 경우 이름과 관계없이 주입
	//만약에 주입하려고하는 필드의 타입과 스프링 빈중에 타입이 일치하는 빈이 2개이상 존재할 경우 애러
	@Autowired
	private WebApplicationContext context;
	
	protected MockMvc mockMvc;
	
	@Resource(name="datasource")
	private BasicDataSource datasource;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		//init.sql에 있는 모든 sql 문장을 테스트 메소드 실행전에 실행
		// init.sql에 있는 table 데이터 삭제, 데이터 입력 sql 문장이 있다.
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("/kr/or/ddit/db/init.sql"));
		populator.setContinueOnError(false); // init.sql을 실행하다 에러가 발생할 경우 중지
		DatabasePopulatorUtils.execute(populator, datasource);
	}
	
	@Ignore
	@Test
	public void dummy() {
		
	}
	
}
