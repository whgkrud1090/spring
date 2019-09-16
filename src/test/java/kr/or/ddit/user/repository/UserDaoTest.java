package kr.or.ddit.user.repository;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.User;


public class UserDaoTest extends RootTestConfig{
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	private String userId = "brownTest";

	//UserDao를 테스트하기 위해 필요한거
	//db연결, 트랜잭션, dao
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Test
	public void getUserListTest() {
		/***Given***/

		/***When***/
		List<User> userList = userDao.getUserList();
		logger.debug("userList : {}", userList);

		/***Then***/
		assertTrue(userList.size() > 100);
	}

	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "sally";
		
		/***When***/
		User userVo = userDao.getUser(userId);
		
		/***Then***/
		assertEquals(userVo.getUserNm(), "샐리");
		assertEquals("c6347b73d5b1f7c77f8be828ee3e871c819578f23779c7d5e082ae2b36a44", userVo.getPass());
	}
	
	@Test
	public void getUserListOnlyHalfTest() {
		/***Given***/
		
		/***When***/
		List<User> userList = userDao.getUserListOnlyHalf();

		/***Then***/
		assertEquals(userList.size(), 50);
	}
	
	@Test
	public void getUserPagingListTest(){
		/***Given***/
		Page page = new Page();
		page.setPage(3);
		page.setPagesize(10);
		
		/***When***/
		List<User> userList = userDao.getUserPagingList(page);
		
		/***Then***/
		assertEquals(10, userList.size());
		assertEquals("xuserid22", userList.get(0).getUserId());
	}
	
	@Test
	public void getUserTotalCnt() {
		/***Given***/

		/***When***/
		int totalCnt = userDao.getUsetTotalCnt();
		
		/***Then***/
		assertTrue(totalCnt > 100);
	}

	@Test
	public void insertUserTest() throws ParseException {
		/***Given***/
		User user = new User();
		
		user.setUserId(userId);
		user.setUserNm("브라운테스트");
		user.setPass("brown1234");
		user.setReg_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-08"));
		user.setAlias("곰테스트");
		user.setAddr1("대전광역시 중구 중앙로 76");
		user.setAddr2("영민빌딩 2층 DDIT");
		user.setZipcode("34940");

		/***When***/
		int insertCnt = userDao.insertUser(user);
		/***Then***/
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
		int updateCnt = userDao.updateUser(userVO);

		/*** Then ***/
		assertEquals(updateCnt, 1);
	}
}
