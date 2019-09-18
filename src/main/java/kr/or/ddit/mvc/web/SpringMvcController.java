package kr.or.ddit.mvc.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.mvc.model.Main;
import kr.or.ddit.user.model.User;
import kr.or.ddit.util.FileUtil;
import kr.or.ddit.util.model.FileInfo;

//지정해 놓은 속성을 session에 집어 넣어 불필요하게 계속 호출하지 않고 재활용한다.
@SessionAttributes("rangers")
@RequestMapping("mvc")
@Controller
public class SpringMvcController {

	private static final Logger logger = LoggerFactory.getLogger(SpringMvcController.class);
	
	//@RequestMapping이 붙은 메소드가 실행되기 전에 @ModelAttribute 메소드가 먼저 실행되고
	//해당 메소드가 리턴하는 값을 model객체에 자동으로 넣어준다.
	//해당 컨트롤러에 대해서만 처리
	@ModelAttribute("rangers")
	public List<String> rangers(){
		logger.debug("rangers()");
		
		//db조회작업 ... : 부하가 생각보다 클수도 있다.
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");
		
		return rangers;
	}
	/**
	* Method : view
	* 작성자 : PC-03
	* 변경이력 :
	* @return
	* Method 설명 :mvc 어노테이션 테스트 view
	 */
	@RequestMapping("view")
	public String view(Model model, @ModelAttribute("rangers") List<String> rangers2, @SessionAttribute(name = "S_USERVO", required = false)User user) {
		//@SessionAttribute : session에 있는 속성을 주입받는것이 아닐까 ?  - 오오 신기해
		// required = false 를 설정해주면 null값이라도 에러가 발생하지 않음
		//S_USERVO는 로그인에 성공한 유저의 정보를 가져오기 때문에 설정해주지 않으면 404에러가 발생함
		/*
		 * [메소드]인자에 사용된 @ModelAttribute
		 * [메소드]에 의해 Model에 설정된 속성값을 메소든 인자에 주입
		 * 만약 해당 Model에 해당 속성이 없을경우 빈 객체를 생성해서 Model객체에 넣어준다.
		 *  --Model객체에 추가가 되어있다.
		 */
		List<String>rangers = (List<String>) model.asMap().get("rangers");
		logger.debug("rangers : {}", rangers);
		logger.debug("rangers2 : {}", rangers2);
		logger.debug("S_USER : {}", user);
		
		return "mvc/view";
	}
	
	/**
	* Method : requestParam
	* 작성자 : PC-03
	* 변경이력 :
	* @param user
	* @param page
	* @return
	* Method 설명 : @RequestParam 어노테이션 테스트	 
	*/
	@RequestMapping("requestParam")
	//@RequestParam으로 받을 파라미터를 지정할 수도 있고, 값을 지정해줄 수도 있다.
	public String requestParam(@RequestParam(name = "userId") String user, @RequestParam(defaultValue = "10") int page) {
		logger.debug("userId : {}", user);
		logger.debug("page : {}", page);
		return "mvc/view";
	}
	
	//mvc/
	@RequestMapping("/path/{subpath}")
	public String requestPath(@PathVariable(name = "subpath")String subpath) {
		logger.debug("subpath : {}", subpath);
		return "mvc/view";
	}
	
	@RequestMapping("upload")
	public String upload(String userId, @RequestParam("picture")MultipartFile partFile) {
		logger.debug("userId : {}", userId);
		logger.debug("partFile.getName() : {}",partFile.getName());
		logger.debug("partFile.getOriginalFilename() : {}",partFile.getOriginalFilename());

		///업로드 되는 시점의 년/월 폴더를 생성해주고, 파일경로와 파일정보를 Fileinfo객체에 담아 리턴
		FileInfo fileInfo = FileUtil.getFileInfo(partFile.getOriginalFilename());
		
		try {
			partFile.transferTo(fileInfo.getFile());
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return "mvc/view";
	}
	
	@RequestMapping("multiParameter")
	public String multiParameter(String userId, @RequestParam("userId") List<String> userIdList, Main main) {
		logger.debug("userId : {}", userId);
		logger.debug("userIdList : {} {} {}", userIdList, userIdList.get(0), userIdList.get(1));
		logger.debug("Main : {}", main);
		return "mvc/view";
	}
	
	@RequestMapping("redirect")
	public String redirect(String userId, Model model, HttpSession session, RedirectAttributes reAttr) {
		//model은 전달안됨
//		model.addAttribute("userId", userId);
		
		//해결
//		session.setAttribute("userId", userId);
		//FlashAttribute 1번만
		//redirect시 최초 1화에 한에 해당 속성값을 유지하고, 읽혀지면 자동적으로 세션에서 해당속성을 제거한다.
		reAttr.addFlashAttribute("userId", userId);
		
		reAttr.addAttribute("alias", "bear");
		
		/*
		 * redirect와 model addAttribute차이점
		 * redirect : 파라미터로 주소에 찍어보냄
		 * addAttribute : 속성값을 넘겨줌
		 */

		return "redirect:/login";
		//redirect : "redirect:url주소"
		//forward : "forward:url주소"	//다른 컨트롤러로 forward 
		//	원본요청 get이면 forward메소드 get(http method에 대해 고려해야함)
	}
}