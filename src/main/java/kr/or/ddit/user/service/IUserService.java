package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;

public interface IUserService {
	/**
	* Method : getUserList
	* 작성자 : PC-03
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	 */
	List<User> getUserList();
	
	User getUser(String userId);

	List<User> getUserListOnlyHalf();

	int insertUser(User user);

	int deleteUser(String userId);

	int updateUser(User user);

	 Map<String, Object> getUserPagingList(Page page);
}	
