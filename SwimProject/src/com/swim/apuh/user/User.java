package com.swim.apuh.user;

public class User {
	private String userId;
	private String userPasswrd;
	//userRole - 0. 회원 1. 관리자
	private int userRole;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPasswrd() {
		return userPasswrd;
	}
	public void setUserPasswrd(String userPasswrd) {
		this.userPasswrd = userPasswrd;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		String info = "";
		
		if(userRole == 0) {
			info = "회원 계정" + userId;
		}else {
			info = "관리자 계정" + userId;
		}
		return info;
	}
	
	
}
