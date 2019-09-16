package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.user.model.User;


public class UserServiceTest extends RootTestConfig{

	@Resource(name="userService")
	private IUserService userService;
	
	
	private String userId = "brownTest";

	@Test
	public void getUserListTest() {
		/***Given***/

		/***When***/
		List<User> userList = userService.getUserList();
		
		/***Then***/
		assertTrue(userList.size() >= 105);
	}
	@Test
	public void getUser() {
		/*** Given ***/
		String userId = "sally";
		
		/***When***/
		User userVo = userService.getUser(userId);
		
		/***Then***/
		assertEquals(userVo.getUserNm(), "샐리");
		assertEquals("c6347b73d5b1f7c77f8be828ee3e871c819578f23779c7d5e082ae2b36a44", userVo.getPass());
	}

	@Test
	public void getUserListOnlyHalf() {
		/*** Given ***/

		/*** When ***/
		List<User> userList = userService.getUserListOnlyHalf();

		/*** Then ***/
		assertEquals(50, userList.size());
		System.out.println(userList);
	}

	@Test
	public void getUserPagingListTest() {
		/*** Given ***/
		Page page = new Page();
		page.setPage(3);
		page.setPagesize(10);

		/*** When ***/
		Map<String, Object> resultMap = (Map<String, Object>) userService.getUserPagingList(page);
		List<User> userList = (List<User>) resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		/*** Then ***/
		assertEquals(10, userList.size());
		assertEquals("xuserid22", userList.get(0).getUserId());
		assertEquals(11, paginationSize);
	}

	@Test
	public void ceilingTest() {
		/*** Given ***/
		int totalCnt = 105;
		int pagesize = 10;

		/*** When ***/
		double paginationSize = Math.ceil((double) totalCnt / pagesize);

		/*** Then ***/
		assertEquals(11, (int) paginationSize);
	}

	@Test
	public void insertUserTest() throws ParseException {
		/*** Given ***/
		User user = new User();

		user.setUserId(userId);
		user.setUserNm("브라운테스트");
		user.setPass("brown1234");
		user.setReg_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-08"));
		user.setAlias("곰테스트");
		user.setAddr1("대전광역시 중구 중앙로 76");
		user.setAddr2("영민빌딩 2층 DDIT");
		user.setZipcode("34940");

		/*** When ***/
		int insertCnt = userService.insertUser(user);

		/*** Then ***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void userUpdateTest() throws ParseException {
		/*** Given ***/
		User userVO = new User();
		userVO.setUserId("brown");
		userVO.setPass("c6347b73d5b1f7c77f8be828ee3e871c819578f23779c7d5e082ae2b36a44");
		userVO.setReg_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-08"));
		userVO.setUserNm("홍길동");
		userVO.setAlias("가나다");
		userVO.setAddr1("대흥동");
		userVO.setAddr2("영민빌딩");
		userVO.setZipcode("34340");

		/*** When ***/
		int updateCnt = userService.updateUser(userVO);

		/*** Then ***/
		assertEquals(updateCnt, 1);
	}
}
