package com.swim.apuh.members;

import java.sql.Date;

public class Member {

	private String memberId;
	private String memberName;
	private String memberGender;
	private Date memberBirth;
	private String memberAddr;
	private int memberCall;
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public Date getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public int getMemberCall() {
		return memberCall;
	}
	public void setMemberCall(int memberCall) {
		this.memberCall = memberCall;
	}
	
	
}
