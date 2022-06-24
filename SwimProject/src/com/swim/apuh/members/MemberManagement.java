package com.swim.apuh.members;

import java.util.List;
import java.util.Scanner;

import com.swim.apuh.Management;
import com.swim.apuh.Studentlists.StudentlistDAO;
import com.swim.apuh.programs.Program;
import com.swim.apuh.programs.ProgramDAO;

public class MemberManagement extends Management{

	private Scanner sc = new Scanner(System.in);
	private MemberDAO mDao = MemberDAO.getInstance();
	private ProgramDAO pDao = ProgramDAO.getInstance();
	private StudentlistDAO sDao = StudentlistDAO.getInstance();
	//회원정보를 관리하는 프로그램(서브메뉴)
	
	public MemberManagement() {
		
		while(true) {
			menuPrint();
			
			int menuNo = selectMenu();
			
			if(menuNo == 1) {
				//1.나의정보
				myPage();
			}else if( menuNo == 2) {
				//2.나의정보 수정
				myPageModi();
			}else if( menuNo == 3) {
				//3.수강과목정보(전부다)
				classAll();
			}else if( menuNo == 4) {
				//4.수강과목조회(등급별조회)
				classGrade();
			}else if( menuNo == 5) {
				//5.수강신청
				classIn();
			}else if( menuNo == 6) {
				//6.수강취소
				classOut();
			}else if( menuNo == 9) {
				//7.뒤로가기
				back();
				break;
			}else {
				showInputError();
			}
			
		}
	}
	
	
	protected void menuPrint() {
		System.out.println("=============================================================================");
		System.out.println("1. 나의 정보 2. 나의 정보 수정 3. 수강과목정보 4. 수강과목조회 5. 수강신청 6. 수강취소 9. 뒤로 가기");
		System.out.println("==============================================================================");
	}
	
	private int selectMenu() {
		int menu = 0;
		try {
			menu = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e) {
			System.out.println("숫자를 입력하세요.");
		}
		return menu;
	}
	private void back() {
		System.out.println("뒤로 가기");
	}
	protected void showInputError() {
		System.out.println("메뉴를 참고하여 입력하시길 바랍니다.");
	}
	
	private void myPage() {
		String memberId = inputId();
		
		Member mb = mDao.selectOne(memberId);
		
		if(mb == null) {
			System.out.println("등록된 회원이 아닙니다.");
			return;
		}
		System.out.println(mb);
	}
	
	private void myPageModi() {
		String memberId = inputId();
		
		Member mb = mDao.selectOne(memberId);
		
		if(mb == null) {
			System.out.println("정보를 등록해주세요.");
			return;
		}
		mb = inputUpdateInfo(mb);
		
		mDao.updateAddr(mb);
		mDao.updateCall(mb);
	}
	private Member inputUpdateInfo(Member member) {
		System.out.println("기존 : " +member.getMemberAddr());
		System.out.println("수정(수정하지 않을경우 0) > ");
		String addr = sc.nextLine();
		if( !addr.equals("0")) {
			member.setMemberAddr(addr);
		}
		System.out.println("기존 : " +member.getMemberCall());
		System.out.println("수정(수정하지 않을경우 -1) > ");
		int call = Integer.parseInt(sc.nextLine());
		if(call > -1) {
			member.setMemberCall(call);
		}
		return member;
	}
	private String inputId() {
		System.out.println("아이디 > ");
		return sc.nextLine();
	}
	private void classAll() {
		List<Member> list = mDao.selectAll();
		
		for(Member member : list) {
			System.out.println(member);
		}
	
	}
	private void classGrade() {
		System.out.println("프로그램 등급을 입력하세요");
		String programGrade = sc.nextLine();
		List<Program> list = pDao.selectGrade(programGrade);
		
		for(Program program : list) {
			System.out.println(program);
		}
	}
		
//		Member mb = mDao.selectOne(programGrade);
//		
//		if(mb == null) {
//			System.out.println("등록된 과목이 아닙니다.");
//			return;
//		}
//		System.out.println(mb);
	
	private void classIn(){
//		System.out.println("수강 프로그램을 입력하세요.");
//		String programName = sc.nextLine();
//		
//		Member mb = mDao.selectOne(programName);
//		
//		if(mb == null) {
//			System.out.println("등록된 프로그램이 아닙니다.");
//			return;
//		}else {
//			System.out.println("신청이 완료되었습니다.");
//			return;
//		}
//		//신청하고 그 정보를 어떻게 담을지,,
//		mDao.insert(programName);
		
	}
	private void classOut() {
//		System.out.println("수강하는 프로그램을 입력하세요.");
//		String programName = sc.nextLine();
//		
//		Member mb = mDao.selectOne(programName);
//		
//		if(mb == null) {
//			System.out.println("수강중인 회원이 아닙니다.");
//			return;
//		} else {
//			System.out.println("수강이 취소되었습니다.");
//			return;
//		}
//		//얘는 또 어케 취소하냐...하 < 이게맞냐..
//		//pDao.delete(mb.getMemberName());
//		boolean isSelected = sDao.deleteStudentList(Member.getMemberId());
//		
//		if(!isSelected) {
//			mDao.delete(programName);
//			
//		}else {
//			System.out.println("삭제못함 암튼못함");
//		}
	}
	
	
}
