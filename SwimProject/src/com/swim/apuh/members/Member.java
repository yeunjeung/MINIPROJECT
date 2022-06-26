package com.swim.apuh.members;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Member {

	private String memberId;
	private String memberName;
	private String memberGender;
	private Date memberBirth;
	private String memberAddr;
	private int memberCall;
	private String memberPwd;
	
	
}
