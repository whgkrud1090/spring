package kr.or.ddit.user.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;

public class User {
	private String userId;
	private String userNm;
	private String alias;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	//pattern을 지정해주어야한다.
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reg_dt;

	@NotNull
	private String pass;		//사용자 비밀번호
	
	private String addr1;		//주소1
	private String addr2;		//주소2
	private String zipcode;		//우편번호
	private String filename;
	private String realfilename;
	private String realfilename2;


	
	public User() {
		
	}

	public String getRealfilename2() {
		return realfilename2;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getRealFilename() {
		return realfilename;
	}


	public void setRealFilename(String realfilename) {
		this.realfilename = realfilename;
	}

	
	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public User(String userName) {
		this.userNm = userName;
	}
	
	public User(String userId, String userNm, String alias, Date reg_dt, String addr1, String addr2,
			String pass, String zipcode, String filename, String realfilename) {
		this.userId = userId;
		this.userNm = userNm;
		this.alias = alias;
		this.reg_dt = reg_dt;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.pass = pass;
		this.zipcode = zipcode;
		this.filename = filename;
		this.realfilename = realfilename;
		
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getReg_dt() {
		return reg_dt;
	}
	
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	
	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userNm=" + userNm + ", alias=" + alias + ", reg_dt=" + reg_dt + ", pass="
				+ pass + ", addr1=" + addr1 + ", addr2=" + addr2 + ", zipcode=" + zipcode + ", filename=" + filename
				+ ", realfilename=" + realfilename + "]";
	}

	//로그인을 확인하는 메서드 -> 비밀번호
	public boolean checkLoginValidate(String userId, String pass) {
		
		//암호화 문장끼리 비교
			if(userId.equals(this.userId) && KISA_SHA256.encrypt(pass).equals(this.pass))
				return true;
			
			return false;
		
	}
}