package kr.or.ddit.user.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.service.IUserService;

//url설정
@RequestMapping("user")
//controller선언
@Controller
public class UserController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	/**
	* Method : userView
	* 작성자 : PC-03
	* 변경이력 :
	* @return
	* Method 설명 :사용자 상세화면 요청
	 */
	
	//url설정
	//localhost:8081/spring/user/view.do
	@RequestMapping("view.do")
	public String userView() {
		logger.debug("userController.userView()");
		return "user/view";
		
		//prefix + viewName + suffix
		//WEB-INF/views/user/view.jsp
	}
	
	/**
	* Method : userList
	* 작성자 : PC-03
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 :사용자 전체 리스트 조회
	 */
	@RequestMapping(path = "userList", method = RequestMethod.GET)
	public String userList(Model model) {
		//사용자 정보 전체 조회
		model.addAttribute("userList", userService.getUserList());
		return "user/userList";
	}
	
	@RequestMapping(path = "userListOnlyHalf", method = RequestMethod.GET)
	public String userListOnlyHalf(Model model) {
		model.addAttribute("userList", userService.getUserListOnlyHalf());
		return "user/userListOnlyHalf";
	}
	
	@RequestMapping(path = "userPagingList", method = RequestMethod.GET)
//	public String userPagingList(int page, int pagesize, Model model) {
	public String userPagingList(Page page, Model model) {
		
		model.addAttribute("userVo", page);
		//addAttribute / addAllAttribute()
		model.addAllAttributes(userService.getUserPagingList(page));
		return "user/userPagingList";
	}
	
}