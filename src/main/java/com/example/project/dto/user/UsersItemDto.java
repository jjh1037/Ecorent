package com.example.project.dto.user;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UsersItemDto {

    int itemId;
    boolean domestic;
    String title;
    String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate rentalStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate rentalEndDate;
    Long rentalPrice;
    String address;
    String carrier;
    String content;
    String uploadDate;
    String folderName;
    String fileName;
    String carType;
    String carAge;
    String carFuelType;
    String itemCategory;
    int memberId;
    String memberName;
    String memberNickName;

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public boolean isDomestic() {
        return domestic;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(LocalDate rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(LocalDate rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public Long getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Long rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarAge() {
        return carAge;
    }

    public void setCarAge(String carAge) {
        this.carAge = carAge;
    }

    public String getCarFuelType() {
        return carFuelType;
    }

    public void setCarFuelType(String carFuelType) {
        this.carFuelType = carFuelType;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

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

    @Override
    public String toString() {
        return "UsersItemDto{" +
                "itemId=" + itemId +
                ", domestic=" + domestic +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", rentalStartDate=" + rentalStartDate +
                ", rentalEndDate=" + rentalEndDate +
                ", rentalPrice=" + rentalPrice +
                ", address='" + address + '\'' +
                ", carrier='" + carrier + '\'' +
                ", content='" + content + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", folderName='" + folderName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", carType='" + carType + '\'' +
                ", carAge='" + carAge + '\'' +
                ", carFuelType='" + carFuelType + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
