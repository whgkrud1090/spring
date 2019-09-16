package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.User;

@Service
public class UserService implements IUserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Resource(name="userDao")
	private IUserDao userDao;
	
	public UserService(IUserDao userDao) {
		this.userDao = userDao;
	}
	public UserService() {
	}
	
	/**
	* Method : getUserList
	* 작성자 : PC-21
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<User> getUserList() {
		logger.debug("getUserList()");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return userDao.getUserList();
	}

	@Override
	public User getUser(String userId) {
		return userDao.getUser(userId);
	}

	@Override
	public List<User> getUserListOnlyHalf() {
		return userDao.getUserListOnlyHalf();
	}

	@Override
	public int insertUser(User user) {
		//선언적 트렌잭션
		// . 예외가 발생했을때 정상적으로 rollback이 되었는지
		// . 예외가 발생하지 않고 정상적으로 코드가 실행되었을때
		//	 명시적인 COMMIT없는데 COMMIT이 정상적으로 되는지
//		userDao.insertUser(user);
		return userDao.insertUser(user);
	}

	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}
	@Override
	public Map<String, Object> getUserPagingList(Page page) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<User> userList = userDao.getUserPagingList(page);
		int totalCnt = userDao.getUsetTotalCnt();
		
		map.put("userList", userList);
		map.put("paginationSize", (int)Math.ceil((double)totalCnt/page.getPagesize()));
		
		return map;
	}

}
