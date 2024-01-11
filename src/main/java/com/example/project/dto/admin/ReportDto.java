package com.example.project.dto.admin;

import java.time.LocalDateTime;

public class ReportDto {
    private int reportId;
    private int itemId;
    private int renterId;
    private int ownerId;
    private String reportType;
    private String reportContent;
    private LocalDateTime reportTime;

    // 출력용
    private String title;
    private String renterNickName;
    private String ownerNickName;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
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

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public LocalDateTime getReportTime() {
        return reportTime;
    }

    public void setReportTime(LocalDateTime reportTime) {
        this.reportTime = reportTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRenterNickName() {
        return renterNickName;
    }

    public void setRenterNickName(String renterNickName) {
        this.renterNickName = renterNickName;
    }

    public String getOwnerNickName() {
        return ownerNickName;
    }

    public void setOwnerNickName(String ownerNickName) {
        this.ownerNickName = ownerNickName;
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                "reportId=" + reportId +
                ", itemId=" + itemId +
                ", renterId=" + renterId +
                ", ownerId=" + ownerId +
                ", reportType='" + reportType + '\'' +
                ", reportContent='" + reportContent + '\'' +
                ", reportTime=" + reportTime +
                ", title='" + title + '\'' +
                ", renterNickName='" + renterNickName + '\'' +
                ", ownerNickName='" + ownerNickName + '\'' +
                '}';
    }
}
