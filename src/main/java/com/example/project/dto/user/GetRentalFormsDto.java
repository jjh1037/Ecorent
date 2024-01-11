package com.example.project.dto.user;

import java.time.LocalDateTime;

public class GetRentalFormsDto {
    //item 테이블의 rentalStartDate와 rentalForms의 rentalStartDate 필드명이 겹쳐 만듦
    //받은렌탈요청, 보낸렌탈요청 페이지에서 사용

    private int formId;
    private boolean domestic;
    private String userName;
    private String userPhone;
    private String rentalStartDate;
    private String rentalEndDate;
    private String postcode;
    private String address;
    private String carrier;
    private String userContent;
    private int itemId;
    private int ownerId;
    private int renterId;
    private String formStatus;
    private LocalDateTime createdAt;



    //rentalForms 테이블과 item 테이블 조인하여 얻는 item 필드
    private String title;
    private String OriginStartDate;
    private String OriginEndDate;
    private String folderName;
    private String fileName;

    //member 테이블과 조인하여 얻는 필드
    private String ownerName;

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(String rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public String getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(String rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public boolean isDomestic() {
        return domestic;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getUserContent() {
        return userContent;
    }

    public void setUserContent(String userContent) {
        this.userContent = userContent;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public String getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(String formStatus) {
        this.formStatus = formStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getOriginStartDate() {
        return OriginStartDate;
    }

    public void setOriginStartDate(String originStartDate) {
        OriginStartDate = originStartDate;
    }

    public String getOriginEndDate() {
        return OriginEndDate;
    }

    public void setOriginEndDate(String originEndDate) {
        OriginEndDate = originEndDate;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "GetRentalFormsDto{" +
                "formId=" + formId +
                ", domestic=" + domestic +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", rentalStartDate='" + rentalStartDate + '\'' +
                ", rentalEndDate='" + rentalEndDate + '\'' +
                ", postcode='" + postcode + '\'' +
                ", address='" + address + '\'' +
                ", carrier='" + carrier + '\'' +
                ", userContent='" + userContent + '\'' +
                ", itemId=" + itemId +
                ", ownerId=" + ownerId +
                ", renterId=" + renterId +
                ", formStatus='" + formStatus + '\'' +
                ", createdAt=" + createdAt +
                ", title='" + title + '\'' +
                ", OriginStartDate='" + OriginStartDate + '\'' +
                ", OriginEndDate='" + OriginEndDate + '\'' +
                ", folderName='" + folderName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
