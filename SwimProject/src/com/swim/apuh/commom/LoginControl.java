package com.swim.apuh.commom;

import java.util.Scanner;

import com.swim.apuh.Management;
import com.swim.apuh.user.User;
import com.swim.apuh.user.UsersDAO;

public class LoginControl {

	private Scanner sc = new Scanner(System.in);
	private static User loginInfo = null;
	private static User getLoginInfo() {
		return loginInfo;
	}
	public LoginControl() {
		while(true) {
			menuPrint();
			int menuNo = menuSelect();
			if(menuNo == 1) {
				//로그인
				login();
			}else if (menuNo == 2) {
				exit();
				break;
			}else {
				showInputError();
			}
		}
	}
	
	private void menuPrint() {
		System.out.println("==============");
		System.out.println("1. 로그인 2. 종료");
		System.out.println("==============");
	}
	
	private int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e) {
			System.out.println("숫자형식으로 입력해주세요.");
		}
		return menuNo;
	}
	private void exit() {
		System.out.println("시스템을 종료합니다.");
	}
	private void showInputError() {
		System.out.println("메뉴를 확인해주시기 바랍니다.");
	}
	private void login() {
		// 아이디와 비밀번호 입력
		User inputInfo = inputUser();
		// 로그인 시도
		loginInfo = UsersDAO.getInstance().selectOne(inputInfo);
		
		if(loginInfo == null) return;
		
		new Management().run();
	}
	
	private User inputUser() {
		User info = new User();
		System.out.println("아이디 > ");
		info.setUserId(sc.nextLine());
		System.out.println("비밀번호 > ");
		info.setUserPasswrd(sc.nextLine());
		return info;
	}
}
