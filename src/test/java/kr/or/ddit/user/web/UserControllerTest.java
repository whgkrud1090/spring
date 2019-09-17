package kr.or.ddit.user.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.WebTestConfig;

public class UserControllerTest extends WebTestConfig{

	/**
	* Method : userListTest
	* 작성자 : PC-03
	* 변경이력 :
	* Method 설명 : 사용자 전체 리스트 조회 테스트
	 * @throws Exception 
	 */
	@Test
	public void userListTest() throws Exception {
		mockMvc.perform(get("/user/userList")).andExpect(model().attributeExists("userList")).andExpect(view().name("user/userList"));
	}
	
	/**
	* Method : userListHalfTest
	* 작성자 : PC-03
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 리스트 절반 조회 테스트
	 */
	@Test
	public void userListHalfTest() throws Exception {
		mockMvc.perform(get("/user/userListOnlyHalf")).andExpect(model().attributeExists("userList")).andExpect(view().name("user/userListOnlyHalf"));
	}
	
	/**
	* Method : userPagingList
	* 작성자 : PC-03
	* 변경이력 :
	* @throws Exception
	* Method 설명 :페이지리스트 조회 테스트
	 */
	@Test
	public void userPagingList() throws Exception {
		mockMvc.perform(get("/user/userPagingList")).andExpect(model().attributeExists("userList")).andExpect(view().name("user/userPagingList"));
	}
	
	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	/**
	* Method : pageTest
	* 작성자 : PC-03
	* 변경이력 :
	* Method 설명 :페이지 값 조회 테스트
	 */
	@Test
	public void pageTest() {
		Page page = new Page();
		logger.debug("Page : {}", page);
	}

}
