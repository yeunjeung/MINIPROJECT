package com.swim.apuh.members;

import java.util.Scanner;

public class MemberManagement {

	private Scanner sc = new Scanner(System.in);
	//회원정보를 관리하는 프로그램
	public MemberManagement() {
		
		while(true) {
			menuPrint();
			
			int menuNo = Selectmenu();
			
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
	private void showInputError() {
		System.out.println("메뉴를 참고하여 입력하시길 바랍니다.");
	}
	
	private void mypage() {
		
	}
}
