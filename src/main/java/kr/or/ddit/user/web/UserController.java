package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.model.UserValidator;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.FileUtil;
import kr.or.ddit.util.model.FileInfo;

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

	/**
	* Method : userListOnlyHalf
	* 작성자 : PC-03
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 절반 리스트 조회
	 */
	@RequestMapping(path = "userListOnlyHalf", method = RequestMethod.GET)
	public String userListOnlyHalf(Model model) {
		model.addAttribute("userList", userService.getUserListOnlyHalf());
		return "user/userListOnlyHalf";
	}
	
	/**
	* Method : userPagingList
	* 작성자 : PC-03
	* 변경이력 :
	* @param p
	* @param pagesize
	* @param model
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	 */
	@RequestMapping(path = "userPagingList", method = RequestMethod.GET)
	public String userPagingList(@RequestParam(name = "page", defaultValue = "1")int p, @RequestParam(defaultValue = "10")int pagesize, Model model) {
//	public String userPagingList(Page page, Model model) {
		
		Page page = new Page(p, pagesize);
		model.addAttribute("pageVo", page);
		//addAttribute / addAllAttribute()
		model.addAllAttributes(userService.getUserPagingList(page));
		return "user/userPagingList";
	}
	
	/**
	* Method : user
	* 작성자 : PC-03
	* 변경이력 :
	* @param model
	* @param userId
	* @return
	* Method 설명 : 사용자 상세정보 조회
	 */
	@RequestMapping(path = "user", method = RequestMethod.GET)
	public String user(Model model, String userId) {
		model.addAttribute(userService.getUser(userId));
		return "user/user";
	}
	
	/**
	* Method : userPicture
	* 작성자 : PC-03
	* 변경이력 :
	* @param userId
	* @param response
	* @param request
	* @throws IOException
	* Method 설명 : 사용자 사진파일 설정
	 */
	@RequestMapping("userPicture")
	public void userPicture(String userId, HttpServletResponse response, HttpServletRequest request) throws IOException {
		userId = request.getParameter("userId");
		User user = userService.getUser(userId);
		
		ServletOutputStream sos = response.getOutputStream();
		
		File picture = new File(user.getRealFilename());
		FileInputStream fis = new FileInputStream(picture);
		
		byte[] buff = new byte[512];
		int len = 0;
		while((len = fis.read(buff, 0, 512)) != -1) {
			sos.write(buff, 0, len);
		}
		fis.close();
	}
	
	//사용자 등록 (GET, POST)구별
	/**
	* Method : userFormView
	* 작성자 : PC-03
	* 변경이력 :
	* @return
	* Method 설명 :사용자 등록 화면 요청
	 */
	@RequestMapping(path = "userForm", method = RequestMethod.GET)
	public String userFormView() {
		return "user/userForm";
	}
	
	/**
	 * Method : userFormView
	 * 작성자 : PC-03
	 * 변경이력 :
	 * @return
	 * Method 설명 :사용자 등록 요청
	 */
	@RequestMapping(path = "userForm", method = RequestMethod.POST)
	public String userForm(User user, BindingResult result, @RequestPart("picture") MultipartFile picture) {
		
		//아이디 형식 맞지 않으면 새로고침
		new UserValidator().validate(user, result);
		if(result.hasErrors()) {
			return "user/userForm"; 
		}
		else {
			FileInfo fileInfo = FileUtil.getFileInfo(picture.getOriginalFilename());
	
			//첨부된 파일이 있을 경우만 업로드처리
			if(picture.getSize() > 0) {
				try {
					picture.transferTo(fileInfo.getFile());
					user.setFilename(fileInfo.getOriginalFileName());
					user.setRealFilename(fileInfo.getFile().getPath());
					
					
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			int insertCnt = userService.insertUser(user);
			
			if(insertCnt == 1) return "redirect:/user/user?userId=" + user.getUserId();
			else return "user/userForm";
		}
	}
	
	//사용자 수정 (GET, POST)구별
	/**
	* Method : userUpdateView
	* 작성자 : PC-03
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 수정 화면 요청
	 */
	@RequestMapping(path = "userUpdate", method = RequestMethod.GET)
	public String userUpdateView(String userId, Model model) {
		model.addAttribute(userService.getUser(userId));
		return "user/userUpdate";
	}
	
	/**
	* Method : userUpdate
	* 작성자 : PC-03
	* 변경이력 :
	* @param user
	* @param result
	* @param picture
	* @return
	* Method 설명 : 사용자 수정 요청
	 */
	@RequestMapping(path = "userUpdate", method = RequestMethod.POST)
	public String userUpdate(User user, BindingResult result, @RequestPart("picture") MultipartFile picture, String userId) {
		new UserValidator().validate(user, result);
		if(result.hasErrors()) {
			return "user/userUpdate"; 
		}
		else {
			FileInfo fileInfo = FileUtil.getFileInfo(picture.getOriginalFilename());
	
			//첨부된 파일이 있을 경우만 업로드처리
			if(picture.getSize() > 0) {
				try {
					picture.transferTo(fileInfo.getFile());
					user.setFilename(fileInfo.getOriginalFileName());
					user.setRealFilename(fileInfo.getFile().getPath());
					
					
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}else {
				User orgin = userService.getUser(userId);
				user.setFilename(orgin.getFilename());
				user.setRealFilename(orgin.getRealFilename());
			}
					int updateCnt = userService.updateUser(user);
			
			if(updateCnt == 1) return "redirect:/user/user?userId=" + user.getUserId();
			return "user/userUpdate"; 
		}
	}
	
	@RequestMapping("userPagingListAjaxView")
	public String userPaginListAjaxView() {
		return "user/userPagingListAjaxView";
	}
	
	@RequestMapping(path = "userPagingListAjax", method = RequestMethod.GET)
	public String userPagingListAjax(@RequestParam(name = "page", defaultValue = "1")int p, @RequestParam(defaultValue = "10")int pagesize, Model model) {
		
		Page page = new Page(p, pagesize);
		model.addAttribute("pageVo", page);
		//addAttribute / addAllAttribute()
		model.addAllAttributes(userService.getUserPagingList(page));
		return "jsonView";
	}
	
	//사용자 페이징 리스트의 결과를 html로 생성한다.
	@RequestMapping("userPagingListHtmlAjax")
	public String userPagingListHtmlAjax(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pagesize, Model model) {
		
		Page pageVo = new Page(page, pagesize);
		Map<String, Object> resultMap = userService.getUserPagingList(pageVo);
		model.addAllAttributes(resultMap);
		model.addAttribute("pageVo", pageVo);
		return "user/userPagingListHtmlAjax";
	}
	
}