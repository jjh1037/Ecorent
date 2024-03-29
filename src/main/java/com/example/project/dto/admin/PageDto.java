package com.example.project.dto.admin;

public class PageDto {
    private int startNum;
    private int pageCount = 5; // 한 페이지 게시물 개수
    private int blockCount = 5;
    private int page; // 현재 페이지 번호
    private int totalPage;
    private int startPage;
    private int endPage;

    public int getStartNum() {
        return startNum;
    }
    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }
    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "pageCount=" + pageCount +
                ", blockCount=" + blockCount +
                ", page=" + page +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", totalPage=" + totalPage +
                '}';
    }
}
