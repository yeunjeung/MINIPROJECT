package com.swim.apuh.Studentlists;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class Studentlist {
	private String studentlistId;
	private String studentlistProname;
	private String studentlistGrade;
	private String studentlistTime;
	private String studentlistTeacher;
	private String studentlistDay;
	@Override
	public String toString() {
		return "수강 아이디 : " + studentlistId +
				", 강의 이름: " + studentlistProname+
				", 강의 등급 : " + studentlistGrade+
				", 강의 시간 : " + studentlistTime+
				", 강사 이름: " + studentlistTeacher+
				", 강의 요일: " + studentlistDay;
				
	}
	
	
	
	
	
	
	
}
