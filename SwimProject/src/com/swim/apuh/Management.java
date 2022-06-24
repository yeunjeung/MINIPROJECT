package com.swim.apuh;

import java.util.Scanner;

import com.swim.apuh.Studentlists.StudentlistDAO;
import com.swim.apuh.members.MemberDAO;
import com.swim.apuh.members.MemberManagement;
import com.swim.apuh.programs.ProgramDAO;
import com.swim.apuh.programs.ProgramManagement;
import com.swim.apuh.user.User;
import com.swim.apuh.user.UsersDAO;
//서브프로그램
public class Management {

	private Scanner sc = new Scanner(System.in);
	protected MemberDAO mDAO = MemberDAO.getInstance();
	protected ProgramDAO pDAO = ProgramDAO.getInstance();
	private StudentlistDAO swDao = StudentlistDAO.getInstance();
	protected UsersDAO uDao = UsersDAO.getInstance();
	
	public void run() {
		
		while (true) {
			menuPrint();
			
			int menuNo = menuSelect();
			
			if(menuNo == 1) {
				//로그인
			}else if(menuNo ==2) {
				//회원
				new MemberManagement();
			}else if(menuNo ==3) {
				//관리자
				new ProgramManagement();
			}else if(menuNo == 9) {
				//회원가입
				insert();
			}else if(menuNo == 9) {
				//시스템 종료
				exit();
				break;
			}else {
				showInputError();
			}
			
		}
	}
	private void insert() {
		User user = new User();
		System.out.println("아이디> ");
		user.setUserId(sc.nextLine());
		System.out.println("비밀번호 > ");
		user.setUserPasswrd(sc.nextLine());
		uDao.insert(user);
	}
	protected void menuPrint() {
		System.out.println("===============================");
		System.out.println("1. 로그인(구현 ㄴ) 2.회원 3.관리자 4.회원가입 9.시스템 종료");
		System.out.println("===============================");
	}
	protected int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e ) {
			System.out.println("숫자를 입력해주시길 바랍니다.");
		}
		return menuNo;
	}
	protected void exit() {
		System.out.println("시스템을 종료합니다.");
	}
	protected void showInputError() {
		System.out.println("메뉴를 입력해주시길 바랍니다,");
	}
	
}

