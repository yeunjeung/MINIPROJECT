package com.swim.apuh.commom;

import java.util.Scanner;

import com.swim.apuh.Management;
import com.swim.apuh.members.Member;
import com.swim.apuh.members.MemberDAO;



public class LoginControl {

	private Scanner sc = new Scanner(System.in);
	private static Member loginInfo = null;
	protected MemberDAO mDAO = MemberDAO.getInstance();
	private static Member getLoginInfo() {
		
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
		Member inputInfo = inputMember();
		// 로그인 시도
		loginInfo = MemberDAO.getInstance().selectOne(inputInfo);
		
		if(loginInfo == null) return;
		
		new Management().run();
	}
	
	//회원가입
	public void signUp() {
		System.out.println("회원가입 > ");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("아이디 > ");
		String member_id = sc.nextLine();
		System.out.println("비밀번호 > ");
		String member_pwd = sc.nextLine();
		System.out.println("이름 > ");
		String member_name = sc.nextLine();
		
		Member member = new Member(member_id, member_pwd, member_name);
		
		new MemberDAO.().signUp(member);
	}
	
	private Member inputMember() {
		Member info = new Member();
		System.out.println("아이디 > ");
		info.setMemberId(sc.nextLine());
		System.out.println("비밀번호 > ");
		info.setMemberPwd(sc.nextLine());
		return info;
	}
}
