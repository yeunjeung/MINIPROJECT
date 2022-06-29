package com.swim.apuh.commom;

import java.sql.Date;
import java.util.Scanner;

import com.swim.apuh.Studentlists.StudentlistDAO;
import com.swim.apuh.members.Member;
import com.swim.apuh.members.MemberDAO;
import com.swim.apuh.programs.ProgramDAO;
import com.swim.apuh.programs.ProgramManagement;
import com.swim.apuh.teacher.TeacherDAO;
import com.swim.apuh.teacher.TeacherManagement;

//서브프로그램
public class Management {

	protected Scanner sc = new Scanner(System.in);
	protected ProgramDAO pDAO = ProgramDAO.getInstance();
	protected MemberDAO mDAO = MemberDAO.getInstance();
	protected StudentlistDAO sDAO = StudentlistDAO.getInstance();
	protected TeacherDAO tDAO = TeacherDAO.getInstance();
	public void run() {
		
		while (true) {
			menuPrint();
			
			int menuNo = menuSelect();
			
			if(menuNo == 1) {
				//로그인
				new LoginControl();
		
			}else if(menuNo == 2) {
				//회원가입
				insert();
			}else if(menuNo == 3) {
				//프로그램관리자
				new ProgramManagement();
			}else if(menuNo == 4) {
				//강사관리자
				new TeacherManagement();
				
			}else if(menuNo == 9) {
				//시스템 종료
				exit();
				break;
			}else {
				showInputError();
			}
			
		}
	}
	//회원정보입력
	private void insert() {
		Member member = inputAll();
		mDAO.Signup(member);
	}
	private Member inputAll() {
		Member member = new Member();
		try {
		System.out.println("아이디> ");
		member.setMemberId(sc.nextLine());
		System.out.println("비밀번호 > ");
		member.setMemberPwd(sc.nextLine());
		System.out.println("이름 > ");
		member.setMemberName(sc.nextLine());
		System.out.println("성별 > ");
		member.setMemberGender(sc.nextLine());
		System.out.println("생년월일(yyyy-mm-dd)");
		member.setMemberBirth(Date.valueOf(sc.nextLine()));
		System.out.println("주소 > ");
		member.setMemberAddr(sc.nextLine());
		System.out.println("전화번호 > ");
		member.setMemberCall(sc.nextLine());
		
		}catch(IllegalArgumentException e) {
			System.out.println("양식에 맞게 입력하세요.");
		}
		return member;
	}
	protected void menuPrint() {
		System.out.println("=================================================================");
		System.out.println(" 1. 로그인 2.회원가입 3.프로그램관리자 4.강사관리자 9.시스템 종료");
		System.out.println("=================================================================");
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
		System.out.println("메뉴를 입력해주시길 바랍니다.");
	}
	
}

