package com.swim.apuh.programs;

import java.util.List;
import java.util.Scanner;

import com.swim.apuh.Management;
import com.swim.apuh.Studentlists.Studentlist;
import com.swim.apuh.Studentlists.StudentlistDAO;
import com.swim.apuh.members.MemberDAO;

public class ProgramManagement extends Management{
	
	private Scanner sc = new Scanner(System.in);
	private MemberDAO mDao = MemberDAO.getInstance();
	private ProgramDAO pDao = ProgramDAO.getInstance();
	private StudentlistDAO sDao = StudentlistDAO.getInstance();
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
		System.out.println("========================================================================================");
		System.out.println("1.과목추가 2.과목정보수정 3.모든프로그램 조회 4. 수강신청내역 전체조회 5.수강신청회원별조회 6. 수강신청 등급별 조회 7.과목삭제 9. 뒤로가기");
		System.out.println("========================================================================================");
		
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
		pDao.insert(pro);
		
		
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
		System.out.println("강의 요일 : ");
		pro.setProgramDay(Integer.parseInt(sc.nextLine()));
		//String programDay = sc.nextLine();
		
		if(pro.getProgramDay()==0) {
			System.out.println("매일반");
		}else if(pro.getProgramDay() == 1){
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
	
	//모든프로그램조회
	private void selectProgram() {
		List<Program> list = pDAO.selectProgram();
		
		for(Program program : list) {
			System.out.println(program);
		}
	}
	
	private Program inputUpdateProgram(Program pro) {
		System.out.println("기존 : " +pro.getProgramTeacher());
		System.out.println("수정(수정하지 않을 경우 0) > ");
		String teacher = sc.nextLine();
		if(!teacher.equals("0")) {
			pro.setProgramTeacher(teacher);
		}
		return pro;
	}
	private String inputTeacher() {
		System.out.println("변경할 강사명 : ");
		return sc.nextLine();
	}
	
	//수강신청내역 전체확인
	private void selectAll() {
		List<Studentlist> list = sDao.selectListAll();
			
		for(Studentlist sl : list) {
			System.out.println(sl);
		}
	}
	//수강신청내역 단건조회(아이디별)
	private void selectOneId() {

	}
	//등급별 전체조회(list)
	private void selectGrade() {
		System.out.println("프로그램 등급을 입력하세요.");
		String studentlistGrade = sc.nextLine();
		List<Studentlist> list = sDao.selectListGrade(studentlistGrade);
		
		for(Studentlist studentlist : list) {
			System.out.println(studentlist);
		}
	}
	//과목삭제
	private void deleteProgram() {
		String programName = inputProgramName();
		
		pDao.delete(programName);
	}
	private String inputProgramName() {
		System.out.println("삭제할 프로그램명 : ");
		return sc.nextLine();
	}


}
