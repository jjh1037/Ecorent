package com.example.project.dto.user;

public class CartDto {
    private int cartId;
    private int itemId;
    private int memberId;
    private String cartTime;
    private int formWritten;


    // list 출력 data
    private String name;
    private String folderName;
    private String fileName;
    private String itemCategory;
    private String title;
    private int rentalPrice;
    private String rentalStartDate;
    private String rentalEndDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getCartTime() {
        return cartTime;
    }

    public void setCartTime(String cartTime) {
        this.cartTime = cartTime;
    }

    public int getFormWritten() {
        return formWritten;
    }

    public void setFormWritten(int formWritten) {
        this.formWritten = formWritten;
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

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
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

    @Override
    public String toString() {
        return "CartDto{" +
                "cartId=" + cartId +
                ", itemId=" + itemId +
                ", memberId=" + memberId +
                ", cartTime='" + cartTime + '\'' +
                ", formWritten=" + formWritten +
                ", name='" + name + '\'' +
                ", folderName='" + folderName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", title='" + title + '\'' +
                ", rentalPrice=" + rentalPrice +
                ", rentalStartDate='" + rentalStartDate + '\'' +
                ", rentalEndDate='" + rentalEndDate + '\'' +
                '}';
    }
}
