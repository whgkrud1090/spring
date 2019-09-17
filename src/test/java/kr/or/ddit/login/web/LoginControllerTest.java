package kr.or.ddit.login.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.config.test.WebTestConfig;
import kr.or.ddit.user.model.User;

//테스트 코드의 오류는 @ContextConfiguration을 설정해주지 않아 Scan할 수 없었기 때문이다. : 20190917 해결

// NoSuchBean
public class LoginControllerTest extends WebTestConfig{

	/**
	* Method : loginViewTest
	* 작성자 : PC-03
	* 변경이력 :
	* Method 설명 :로그인 화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void loginViewTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
			
		//getView()랑 getViewName()이랑 헷갈리지 않기 20190917 : 해결
		/***Then***/
		assertEquals("login/login", mav.getViewName());
	}
	
	/**
	* Method : loginProcessTest
	* 작성자 : PC-03
	* 변경이력 :
	* @throws Exception
	* Method 설명 :로그인 요청 처리 테스트
	 */
	@Test
	public void loginProcessTest() throws Exception {
		MockHttpSession session = new MockHttpSession();
		
		//login페이지 요청
		mockMvc.perform(post("/login")
				
		//param값 설정해 login으로 보냄
				.param("userId", "brown")
				.param("pass", "brown1234")
				.session(session))
		
		//정상처리 여부 체크 -> 200
		.andExpect(status().isOk())
		
		//정상처리 되었을때 view()의 name을 예측해 테스트
		.andExpect(view().name("main"));
	
		//해당 사용자의 정보를 뽑아 null값인지 확인한다.
		User user = (User) session.getAttribute("S_USERVO");
		assertNotNull(user);
	}
}
