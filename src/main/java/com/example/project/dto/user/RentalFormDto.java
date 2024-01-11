package com.example.project.dto.user;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentalFormDto {
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



    //rentalForms 테이블과 조인하여 얻는 필드
    private String title;
    private String itemCategory;
    private String folderName;
    private String fileName;

    private boolean checked; //받은렌탈 신청리스트에서 안읽은 신청건확인

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "RentalFormDto{" +
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
                ", itemCategory='" + itemCategory + '\'' +
                ", folderName='" + folderName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", checked=" + checked +
                '}';
    }
}
