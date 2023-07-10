package com.rama.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int memberId;
	String memberName;
	String memberDepartment;
	String memberRegNo;
	String memberPhoneNo;
	String memberAddress;
	String memberEmail;
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberDepartment() {
		return memberDepartment;
	}
	public void setMemberDepartment(String memberDepartment) {
		this.memberDepartment = memberDepartment;
	}
	public String getMemberRegNo() {
		return memberRegNo;
	}
	public void setMemberRegNo(String memberRegNo) {
		this.memberRegNo = memberRegNo;
	}
	public String getMemberPhoneNo() {
		return memberPhoneNo;
	}
	public void setMemberPhoneNo(String memberPhoneNo) {
		this.memberPhoneNo = memberPhoneNo;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	
	
	

}
