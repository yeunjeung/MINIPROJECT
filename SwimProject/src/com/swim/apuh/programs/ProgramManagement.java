package com.swim.apuh.programs;

import java.util.List;
import java.util.Scanner;

import com.swim.apuh.Studentlists.Studentlist;
import com.swim.apuh.Studentlists.StudentlistDAO;
import com.swim.apuh.commom.Management;
import com.swim.apuh.members.MemberDAO;
import com.swim.apuh.teacher.Teacher;

public class ProgramManagement extends Management{
	
	//프로그램정보를 관리하는 프로그램(서브메뉴2)
	
	public ProgramManagement() {
		while(true) {
			menuPrint();
			
			int menuNo = selectMenu();
			
			if(menuNo == 1) {
				//과목추가
				addProgram();
			}else if(menuNo == 2) {
				//과목정보수정
				modiProgram();
			}else if(menuNo == 3) {
				//과목정보전체조회
				selectProgram();
			}else if(menuNo == 4) {
				//수강신청내역 전체확인
				selectAll();
			}else if(menuNo == 5) {
				//수강신청내역 단건조회(아이디별)
				selectOneId();
			}else if(menuNo == 6) {
				//등급별 전체조회(list)
				selectGrade();
			}else if(menuNo == 7) {
				//강사전체조회
				selectAllTeacher();
			}else if(menuNo == 8) {
				//과목삭제
				deleteProgram();
			}else if(menuNo == 9) {
				//뒤로가기
				back();
				break;
			}else {
				showInputError();
			}
		}
	}
	
	protected void menuPrint() {
		System.out.println("====================================================================================================================================================");
		System.out.println("1.과목추가 2.과목정보수정 3.모든프로그램 조회 4. 수강신청내역 전체조회 5.수강신청회원별조회 6. 수강신청 등급별 조회 7.강사조회 8.과목삭제 9. 뒤로가기");
		System.out.println("====================================================================================================================================================");
		
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
	//과목추가
	private void addProgram(){
		Program pro = inputProgram();
		pDAO.insert(pro);
		
		
		}
	private Program inputProgram() {
		Program pro = new Program();
		System.out.println("프로그램명 : ");
		pro.setProgramName(sc.nextLine());
		System.out.println("프로그램 등급 : ");
		pro.setProgramGrade(sc.nextLine());
		System.out.println("프로그램 시간 : ");
		pro.setProgramTime(sc.nextLine());
		System.out.println("선생님 : ");
		pro.setProgramTeacher(sc.nextLine());
		System.out.println("강의 요일(주5일 or 주3일) : ");
		pro.setProgramDay(sc.nextLine());
		
		if(pro.getProgramDay().equals("주5일")) {
			System.out.println("매일반");
		}else if(pro.getProgramDay().equals("주3일")){
			System.out.println("월수금반");
		}
		return pro;
	}
	
	//과목정보수정
	private void modiProgram() {
		//과목이름 선택
		String programName = inputName();
		//과목정보검색
		Program program = pDAO.selectOne(programName);
		
		//강사정보검색
		//Teacher teacher = tDAO.selectOne(teacherName);
		if(program == null) {
			System.out.println("등록된 프로그램이 아닙니다.");
			return;
		}
		
		
		//수정할 정보입력
		program = inputUpdateProgram(program);
		
		
		//DB에 업데이트
		pDAO.updateTeacher(program);
		
		//
	}
	private String inputName() {
		System.out.println("프로그램 이름 > ");
		return sc.nextLine();
	}
	
	private String inputTeacherName() {
		System.out.println("강사 이름 > ");
		return sc.nextLine();
	}
	
	//모든프로그램조회
	private void selectProgram() {
		List<Program> list = pDAO.selectProgram();
		
		for(Program program : list) {
			System.out.println(program);
		}
	}
	
	private Program inputUpdateProgram(Program pro) {
			
		System.out.println("기존 프로그램 이름 : " +pro.getProgramTeacher());
		System.out.println("변경할 선생님 이름 : (수정하지 않을 경우 0) > ");
		String teacher = sc.nextLine();
		if(!teacher.equals("0")) {
			pro.setProgramTeacher(teacher);
		}
		return pro;
		}
//	}
//		//선생님 선택
//		String teacherName = inputTeacherName();
//		//선생님 정보선택
//		Teacher teacher = tDAO.selectOne(teacherName);
//		
//		if(teacher == null) {
//			System.out.println("등록된 선생님이 아닙니다.");
//		}
//		

	
	//수강신청내역 전체확인
	private void selectAll() {
		List<Studentlist> list = sDAO.selectListAll();
	
		for(Studentlist si : list) {
			System.out.println(si);
		}
	}
	
	//수강신청내역 단건조회(아이디별)
	private void selectOneId() {
		String studentlistId = inputId();
		
		Studentlist si = sDAO.selectOneProList(studentlistId);
		
		if(si == null) {
			System.out.println("수강 등록된 회원이 아닙니다.");
			return;
		}
		System.out.println(si);
	}
	private String inputId() {
		System.out.println("회원 아이디 입력 > ");
		return sc.nextLine();
	}
	//등급별 전체조회(list)
	private void selectGrade() {
		System.out.println("프로그램 등급을 입력하세요.");
		String studentlistGrade = sc.nextLine();
		List<Studentlist> list = sDAO.selectListGrade(studentlistGrade);
		
		for(Studentlist studentlist : list) {
			System.out.println(studentlist);
		}
	}
	//과목삭제
	private void deleteProgram() {
		String programName = inputProgramName();
		
		pDAO.delete(programName);
	}
	private String inputProgramName() {
		System.out.println("삭제할 프로그램명 : ");
		return sc.nextLine();
	}
	//모든강사조회
		private void selectAllTeacher() {
			List<Teacher> list = tDAO.selectTeacher();
			
			for(Teacher teacher : list) {
				System.out.println(teacher);
			}
		}
	
}
