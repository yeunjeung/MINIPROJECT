package com.swim.apuh.members;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter

public class Member {

	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberGender;
	private Date memberBirth;
	private String memberAddr;
	private String memberCall;
	@Override
	public String toString() {
		return "아이디 : " + memberId +
				", 패스워드 : " + memberPwd+
				", 이름 : " + memberGender+
				", 성별 : " + memberBirth+
				", 생년월일 : " + memberAddr+
				", 주소 : " + memberAddr+
				", 전화번호 : " + memberCall;
				
	}

	
}
