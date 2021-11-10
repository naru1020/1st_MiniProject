package com.yedam.java.app.login;

public class User {
	private String userId;
	private String userPassword;
	private String userName;
	private String userPhone;
	private String userEmail;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "회원 아이디 : " + userId + ", 비밀번호 : " + userPassword + ", 회원명 : " + userName + ", 연락처 : " + userPhone
				+ ", 이메일 : " + userEmail;
	}
}
