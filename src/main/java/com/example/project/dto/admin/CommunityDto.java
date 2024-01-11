package com.example.project.dto.admin;

import org.springframework.format.annotation.DateTimeFormat;

public class CommunityDto {
    private int postNum;
    private String postType;
    private String postTitle;
    private String postWriter;
    private String postContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String postDate;

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostWriter() {
        return postWriter;
    }

    public void setPostWriter(String postWriter) {
        this.postWriter = postWriter;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        return "CommunityDto{" +
                "postNum=" + postNum +
                ", postType='" + postType + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postWriter='" + postWriter + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postDate='" + postDate + '\'' +
                '}';
    }
}
