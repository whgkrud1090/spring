package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PerformanceCheckInterceptor extends HandlerInterceptorAdapter{
	long startTime = System.currentTimeMillis();
	private static final Logger logger = LoggerFactory.getLogger(PerformanceCheckInterceptor.class);
	
	/**
	* Method : preHandle
	* 작성자 : PC-03
	* 변경이력 :
	* @param request
	* @param response
	* @param handler
	* @return
	* @throws Exception
	* Method 설명 : 요청을 controller로 보내기 전에 호출되는 메소드
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		request.setAttribute("startTime", startTime);
		//다음 interceptor나 혹은 더이상 interceptor가 없을 경우 요청을 처리해주는 controller로 응답을 보낼지 여부
		
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long endTime = System.currentTimeMillis();
		long startTime = (long) request.getAttribute("startTime");
		
		//메소드 실행시간
		logger.debug("{}, {} endTime - startTime : {}",handler, request.getRequestURI(), endTime - startTime);
	}
	
}
