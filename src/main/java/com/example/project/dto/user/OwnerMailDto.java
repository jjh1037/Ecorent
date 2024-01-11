package com.example.project.dto.user;

public class OwnerMailDto {

    private String renterName;
    private String renterPhone;
    private String itemCategory;
    private String rentalStartDate;
    private String rentalEndDate;
    private String renterAddress;
    private String renterCarrier;
    private String renterContent;

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getRenterPhone() {
        return renterPhone;
    }

    public void setRenterPhone(String renterPhone) {
        this.renterPhone = renterPhone;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
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

    public String getRenterAddress() {
        return renterAddress;
    }

    public void setRenterAddress(String renterAddress) {
        this.renterAddress = renterAddress;
    }

    public String getRenterCarrier() {
        return renterCarrier;
    }

    public void setRenterCarrier(String renterCarrier) {
        this.renterCarrier = renterCarrier;
    }

    public String getRenterContent() {
        return renterContent;
    }

    public void setRenterContent(String renterContent) {
        this.renterContent = renterContent;
    }

    @Override
    public String toString() {
        return "MailDto{" +
                "renterName='" + renterName + '\'' +
                ", renterPhone='" + renterPhone + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", rentalStartDate='" + rentalStartDate + '\'' +
                ", rentalEndDate='" + rentalEndDate + '\'' +
                ", renterAddress='" + renterAddress + '\'' +
                ", renterCarrier='" + renterCarrier + '\'' +
                ", renterContent='" + renterContent + '\'' +
                '}';
    }
}
