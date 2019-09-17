package kr.or.ddit.mvc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("mvc")
@Controller
public class SpringMvcController {

	private static final Logger logger = LoggerFactory.getLogger(SpringMvcController.class);
	
	/**
	* Method : view
	* 작성자 : PC-03
	* 변경이력 :
	* @return
	* Method 설명 :mvc 어노테이션 테스트 view
	 */
	@RequestMapping("view")
	public String view() {
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
}
