package kr.or.ddit.user.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//Spring에서 제공하는 걸로
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		//User에 있는것을 검증
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//target에러 발생시 errors
		User user = (User) target;
		
		//지정한 규칙 : userId필드는 null일 수 없고, 문자열의 길이는 4글자 이상어야한다.
//		if(user.getUserId() == null || user.getUserId().length() <= 3) 
//			errors.rejectValue("userId", "required"); //필드명, 에러코드(개발자 정의) 
		
		//userForm에서 usreId검사
		Pattern p = Pattern.compile("^([a-zA-Z\\d\\.@]){3,20}$");
		Matcher m = p.matcher(user.getUserId());
		
		if(!m.find()) errors.rejectValue("userId", "required");
	}
}
