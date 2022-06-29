package com.swim.apuh.teacher;

import java.util.List;

import com.swim.apuh.commom.Management;
import com.swim.apuh.programs.Program;


public class TeacherManagement extends Management{
//강사관리
	public TeacherManagement() {
		while(true) {
			menuPrint();
			
			int menuNo = selectMenu();
			
			if(menuNo == 1) {
				//강사추가
				addTeacher();
			}else if(menuNo == 2) {
				//강사전체조회
				selectAllTeacher();
			}else if(menuNo == 3) {
				//강사 단건조회
				selectOneTeacher();
			}else if(menuNo == 4) {
				//강사 전공에 따라 조회
				selectMajor();
			}else if(menuNo == 5) {
				//강사 삭제
				deleteTeacher();
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
		System.out.println("=====================================================================================");
		System.out.println("1.강사추가 2.강사전체조회 3.이름별 강사조회 4.전공별 강사조회 5.강사삭제 9.뒤로 가기");
		System.out.println("=====================================================================================");
		
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
	//강사추가
	private void addTeacher(){
		Teacher tc = inputTeacher();
		tDAO.insert(tc);
		
		
		}
	private Teacher inputTeacher() {
		Teacher tc = new Teacher();
		System.out.println("강사 이름 : ");
		tc.setTeacherName(sc.nextLine());
		System.out.println("강사 나이 : ");
		tc.setTeacherAge(sc.nextLine());
		System.out.println("강사 성별(m/f) : ");
		tc.setTeacherGender(sc.nextLine());
		System.out.println("강사 전화번호 : ");
		tc.setTeacherCall(sc.nextLine());
		System.out.println("강사 주소 : ");
		tc.setTeacherAddr(sc.nextLine());
		System.out.println("강사 전공 : ");
		tc.setTeacherMajor(sc.nextLine());
		
		return tc;
	}
	//강사단건조회
	private void selectOneTeacher() {
		String teacherName = inputName();
		
		Teacher teacher = tDAO.selectOne(teacherName);
		
		if(teacher == null) {
			System.out.println("등록된 강사가 아닙니다.");
			return;
		}
		System.out.println(teacher);
	}
//	//강사정보수정
//	private void modiProgram() {
//		//과목이름 선택
//		String programName = inputName();
//		//과목정보검색
//		Program program = pDAO.selectOne(programName);
//		
//		if(program == null) {
//			System.out.println("등록된 프로그램이 아닙니다.");
//			return;
//		}
//		//수정할 정보입력
//		program = inputUpdateProgram(program);
//		//DB에 업데이트
//		pDAO.updateTeacher(program);
//		
//		//
//	}
	private String inputName() {
		System.out.println("강사 이름 > ");
		return sc.nextLine();
	}
	
	//모든강사조회
	private void selectAllTeacher() {
		List<Teacher> list = tDAO.selectTeacher();
		
		for(Teacher teacher : list) {
			System.out.println(teacher);
		}
	}
//	//강사변경
//	private void teacherChange() {
//		String programName = inputProgramName();
//		
//		Program program = pDAO.selectOne(programName);
//		
//		if(program == null) {
//		System.out.println("등록된 프로그램이 아닙니다.");
//		return;
//	}
//	//수정할 정보입력
//	program = inputUpdateProgram(program);
//	//DB에 업데이트
//	pDAO.updateTeacher(program);
//	
	
	//
//	}
	
//	private String inputProgramName() {
//		System.out.println("프로그램 이름 > ");
//		return sc.nextLine();
//	}
//	private Program inputUpdateProgram(Program pro) {
//		System.out.println("기존 프로그램 선생님 : " +pro.getProgramTeacher());
//		System.out.println("변경할 선생님 이름 : (수정하지 않을 경우 0) > ");
//		String teacher = sc.nextLine();
//		if(!teacher.equals("0")) {
//			pro.setProgramTeacher(teacher);
//		}
//		return pro;
//	}

	//강사 전공별 전체조회(list)
	private void selectMajor() {
		System.out.println("강사 전공을 입력하세요.");
		String teacherMajor = sc.nextLine();
		List<Teacher> list = tDAO.selectMajor(teacherMajor);
		
		for(Teacher teacher : list) {
			System.out.println(teacher);
		}
	}
	//강사삭제(해고)
	private void deleteTeacher() {
		String teacherName = inputName();
		
		tDAO.delete(teacherName);
	}
}


