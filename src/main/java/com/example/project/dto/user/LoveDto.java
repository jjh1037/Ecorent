package com.example.project.dto.user;

public class LoveDto {
    private int loveId;
    private int memberId;
    private int postNum;
    private int state;

    public int getLoveId() {
        return loveId;
    }

    public void setLoveId(int loveId) {
        this.loveId = loveId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LoveDto{" +
                "loveId=" + loveId +
                ", memberId=" + memberId +
                ", postNum=" + postNum +
                ", state=" + state +
                '}';
    }
}
