package com.example.project.dto.user;

import org.springframework.cglib.core.Local;
import org.thymeleaf.spring6.processor.SpringErrorClassTagProcessor;

import java.time.LocalDateTime;

public class MessageDto {

    private int msgId;
    private int itemId;
    private int renterId;
    private int ownerId;
    private int sentBy;
    private String msgContent;
    private boolean checked;
    private LocalDateTime msgTime;



    //메시지 리스트를 위한 컬럼들
    private String title;
    private String folderName;
    private String fileName;
    private int otherMemberId;
    private String otherMemberName;

    //메시지 대화창페이지 제품주인이름 불러오기위해
    private String ownerName;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getSentBy() {
        return sentBy;
    }

    public void setSentBy(int sentBy) {
        this.sentBy = sentBy;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public LocalDateTime getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(LocalDateTime msgTime) {
        this.msgTime = msgTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getOtherMemberId() {
        return otherMemberId;
    }

    public void setOtherMemberId(int otherMemberId) {
        this.otherMemberId = otherMemberId;
    }

    public String getOtherMemberName() {
        return otherMemberName;
    }

    public void setOtherMemberName(String otherMemberName) {
        this.otherMemberName = otherMemberName;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "msgId=" + msgId +
                ", itemId=" + itemId +
                ", renterId=" + renterId +
                ", ownerId=" + ownerId +
                ", sentBy=" + sentBy +
                ", msgContent='" + msgContent + '\'' +
                ", checked=" + checked +
                ", msgTime=" + msgTime +
                ", title='" + title + '\'' +
                ", folderName='" + folderName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", otherMemberId=" + otherMemberId +
                ", otherMemberName='" + otherMemberName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
