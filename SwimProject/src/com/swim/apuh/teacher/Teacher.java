package com.swim.apuh.teacher;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class Teacher {
	String teacherName;
	String teacherAge;
	String teacherGender;
	String teacherCall;
	String teacherAddr;
	String teacherMajor;
	@Override
	public String toString() {
	return "강사 이름 : " + teacherName +
			", 강사 나이 : " + teacherAge +
			", 강사 성별 : " + teacherGender +
			", 강사 연락처 : " + teacherCall +
			", 강사 주소: " + teacherAddr +
			", 강사 전공: " + teacherMajor
			;
	}
	
	
}
