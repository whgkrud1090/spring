package kr.or.ddit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//예외 생성
//예외 클래스를 상속받아 처리한다.
@ResponseStatus(code = HttpStatus.NOT_FOUND)//에러코드설정
public class NoFileException extends Exception{
	
}
