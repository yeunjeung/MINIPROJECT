package com.swim.apuh.members;

import java.util.List;

import com.swim.apuh.Studentlists.Studentlist;
import com.swim.apuh.commom.Management;
import com.swim.apuh.programs.Program;

public class MemberManagement extends Management {

	private static Member mb = null;
	
	// 회원정보를 관리하는 프로그램(서브메뉴)
	public MemberManagement(Member info) {
		mb = info;

		while (true) {
			menuPrint();

			int menuNo = selectMenu();

			if (menuNo == 1) {
				// 1.나의정보
				myPage();
			} else if (menuNo == 2) {
				// 2.나의정보 수정
				myPageModi();
			} else if (menuNo == 3) {
				// 3.수강과목정보(전부다)
				classAll();
			} else if (menuNo == 4) {
				// 4.수강과목조회(등급별조회)
				classGrade();
			} else if (menuNo == 5) {
				// 5.수강신청
				classIn();
			} else if (menuNo == 6) {
				// 6.수강취소
				classOut();
			} else if (menuNo == 9) {
				// 7.뒤로가기
				back();
				break;
			} else {
				showInputError();
			}

		}
	}

	protected void menuPrint() {
		System.out
				.println("===========================================================================================");
		System.out.println("1. 나의 정보 2. 나의 정보 수정 3. 수강가능 프로그램 정보 4. 등급별 프로그램 조회 5. 수강신청 6. 수강취소 9. 뒤로 가기");
		System.out
				.println("===========================================================================================");
	}

	private int selectMenu() {
		int menu = 0;
		try {
			menu = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
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

		System.out.println(mb);
	}

	private void myPageModi() {

		mb = inputUpdateInfo();

		mDAO.updateAddr(mb);
		mDAO.updateCall(mb);
	}

	private Member inputUpdateInfo() {
		System.out.println("회원님의 주소를 수정합니다.");
		System.out.println("기존 : " + mb.getMemberAddr());
		System.out.println("수정(수정하지 않을경우 0) > ");
		String addr = sc.nextLine();
		if (!addr.equals("0")) {
			mb.setMemberAddr(addr);
		}
		System.out.println("회원님의 전화번호를 수정합니다.");
		System.out.println("기존 : " + mb.getMemberCall());
		System.out.println("수정(수정하지 않을경우 0) > ");
		String call = sc.nextLine();
		if (!call.equals("0")) {
			mb.setMemberCall(call);
		}
		return mb;
	}


	private void classAll() {
		List<Program> list = pDAO.selectProgram();

		for (Program program : list) {
			System.out.println(program);
		}

	}

	private void classGrade() {
		System.out.println("프로그램 등급을 입력하세요");
		String programGrade = sc.nextLine();
		List<Program> list = pDAO.selectGrade(programGrade);

		for (Program program : list) {
			System.out.println(program);
		}
	}

	//수강신청
	private void classIn() {
		// 과목이름 입력
		System.out.println("신청할 과목이름을 입력하세요.");
		String programName = sc.nextLine();
		// 존재하는 과목인지 검색해서 확인
		Program pro = pDAO.selectOne(programName);
		// 없는 과목이라면 안내
		if (pro == null) {
			System.out.println("없는 과목입니다.");
			return;
		}
		// 중복신청을 방지하기위해 -> 회원아이디와 프로그램네임을
		Studentlist temp = new Studentlist(); //변수설정
		temp.setStudentlistId(mb.getMemberId()); //아이디가져와서
		temp.setStudentlistProname(programName);//프로그램네임가져오고
		Studentlist info = sDAO.selectOneMbList(temp);//info에 정보담기
		// 이미 수강중인 과목이라면 안내
		if (info != null) {
			System.out.println("이미 신청한 과목입니다.");
		} else {
			// 아니라면 수강내역에 추가하기
			info = new Studentlist();
			info.setStudentlistId(mb.getMemberId());
			info.setStudentlistProname(pro.getProgramName());
			info.setStudentlistGrade(pro.getProgramGrade());
			info.setStudentlistTime(pro.getProgramTime());
			info.setStudentlistTeacher(pro.getProgramTeacher());
			info.setStudentlistDay(pro.getProgramDay());
			sDAO.insertStudentlist(info);
			return;
		}
	}

	//수강취소
	private void classOut() {
		System.out.println("수강취소할 과목이름을 입력하세요.");

		Studentlist sii = new Studentlist();// sii라는 보따리에 담는것
		sii.setStudentlistId(mb.getMemberId());
		
		// 과목입력받고
		sii.setStudentlistProname(sc.nextLine());// DAO에서 쿼리문에 써준 아이디, 프로그램이름담기
		// 수강중인 과목인지 확인
		Studentlist si = sDAO.selectOneMbList(sii); // DAO의 si 에다가 넘겨주면 거기서 풀어서 볼것임!
		// 수강중이 아니라면 취소할수없다고 안내
		if (si == null) { //
			System.out.println("수강중인 과목이 아닙니다.");
			return;
		}
		// 수강중이라면 취소

		// studentlists에 삭제하기
		sDAO.deleteStudentList(si.getStudentlistId());// 매개변수 타입맞추기
	}

}
