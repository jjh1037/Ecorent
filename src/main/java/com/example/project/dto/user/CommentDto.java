package com.example.project.dto.user;

import java.time.LocalDateTime;

public class CommentDto {

    private int comId;
    private int postNum;
    private int comWriter;
    private String comContent;
    private LocalDateTime comDate;

    //코멘트 작성자 닉네임 표기위해
    private String memberNickName;

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public int getComWriter() {
        return comWriter;
    }

    public void setComWriter(int comWriter) {
        this.comWriter = comWriter;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public LocalDateTime getComDate() {
        return comDate;
    }

    public void setComDate(LocalDateTime comDate) {
        this.comDate = comDate;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "comId=" + comId +
                ", postNum=" + postNum +
                ", comWriter=" + comWriter +
                ", comContent='" + comContent + '\'' +
                ", comDate=" + comDate +
                ", memberNickName='" + memberNickName + '\'' +
                '}';
    }
}
