package com.example.project.dto.user;

public class PageDto {
    private int startNum; //시작넘버 (0부터 10개, 10부터 10개...)
    private int pageCount=15; //offset (5의 게시물이 있다면 5/2 -> 3페이지가 나올것) 페이지당 게시물 수
    private int blockCount=5; // 페이지 블록에서 보여질 페이지들의 개수
    private int page; //현재 페이지 번호
    private int totalPage; // 전체 페이지 번호
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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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

    @Override
    public String toString() {
        return "PageDto{" +
                "startNum=" + startNum +
                ", pageCount=" + pageCount +
                ", blockCount=" + blockCount +
                ", page=" + page +
                ", totalPage=" + totalPage +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                '}';
    }
}
