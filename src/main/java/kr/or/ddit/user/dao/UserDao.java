package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;

// class명에서 맨 첫글자를 소문자로 변경한 문자열이 스프링 빈 이름으로 기본 설정됨
// 다른이름의 스프링 벤이름으로 등록하려면 속성을 설정 - @Repository("설정하고자 하는 스프링 빈 이름")
@Repository
public class UserDao implements IUserDao{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<User> getUserList() {
		return sqlSession.selectList("user.getUserList");
	}

	@Override
	public User getUser(String userId) {
		return sqlSession.selectOne("user.getUser", userId);
	}

	@Override
	public List<User> getUserListOnlyHalf() {
		return sqlSession.selectList("user.getUserListOnlyHalf");
	}

	@Override
	public List<User> getUserPagingList(Page page) {
		return sqlSession.selectList("user.getUserPagingList", page);
	}

	@Override
	public int getUsetTotalCnt() {
		return sqlSession.selectOne("user.getUserTotalCnt");
	}
	
	@Override
	public int insertUser(User user) {
		return sqlSession.insert("user.insertUser", user);
	}

	@Override
	public int deleteUser(String userId) {
		return sqlSession.delete("user.deleteUser", userId);
	}

	@Override
	public int updateUser(User user) {
		return sqlSession.update("user.updateUser", user);
	}
}
