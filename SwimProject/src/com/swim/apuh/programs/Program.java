package com.swim.apuh.programs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Program {
	private String programName;
	private String programGrade;
	private String programTime;
	private String programTeacher;
	private String programDay;
	@Override
	public String toString() {
		return "강의 : " + programName +
				", 강의 등급: " + programGrade+
				", 강의 시간 : " + programTime+
				", 강사 이름 : " + programTeacher+
				", 수강 요일: " + programDay
				;
	}
	
				
	
}
