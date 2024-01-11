package com.example.project.dto.user;

import java.time.LocalDate;

public class MemberDto {
    private int memberId;
    private String memberEmail;
    private String memberPasswd;
    private String memberName;
    private String memberPhone;
    private String memberNickName;
    private LocalDate memberBirth;
    private String memberPostcode;
    private String memberAddress;
    private String memberInterest;
    private LocalDate memberRegDate;
    private int memberPoint;
    private String memberRole;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPasswd() {
        return memberPasswd;
    }

    public void setMemberPasswd(String memberPasswd) {
        this.memberPasswd = memberPasswd;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public LocalDate getMemberBirth() {
        return memberBirth;
    }

    public void setMemberBirth(LocalDate memberBirth) {
        this.memberBirth = memberBirth;
    }

    public String getMemberPostcode() {
        return memberPostcode;
    }

    public void setMemberPostcode(String memberPostcode) {
        this.memberPostcode = memberPostcode;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberInterest() {
        return memberInterest;
    }

    public void setMemberInterest(String memberInterest) {
        this.memberInterest = memberInterest;
    }

    public LocalDate getMemberRegDate() {
        return memberRegDate;
    }

    public void setMemberRegDate(LocalDate memberRegDate) {
        this.memberRegDate = memberRegDate;
    }

    public int getMemberPoint() {
        return memberPoint;
    }

    public void setMemberPoint(int memberPoint) {
        this.memberPoint = memberPoint;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "memberId=" + memberId +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPasswd='" + memberPasswd + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberNickName='" + memberNickName + '\'' +
                ", memberBirth=" + memberBirth +
                ", memberPostcode='" + memberPostcode + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", memberInterest='" + memberInterest + '\'' +
                ", memberRegDate=" + memberRegDate +
                ", memberPoint=" + memberPoint +
                ", memberRole='" + memberRole + '\'' +
                '}';
    }
}
