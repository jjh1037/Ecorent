package com.example.project.service.admin;

import com.example.project.dto.admin.PageDto;
import com.example.project.dto.admin.ReportDto;
import com.example.project.mappers.admin.MemberReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberReportService {

    @Autowired
    MemberReportMapper memberReportMapper;

    public String getSearchQuery(String searchType) {
        String searchQuery= "";

        if(searchType.equals("사기")) {
            searchQuery = "WHERE reportType = '" + searchType + "'";
        }else if(searchType.equals("금지물품")) {
            searchQuery = "WHERE reportType = '" + searchType + "'";
        }else if(searchType.equals("비매너")) {
            searchQuery = "WHERE reportType = '" + searchType + "'";
        }else if(searchType.equals("기타")) {
            searchQuery = "WHERE reportType = '" + searchType + "'";
        }else {
            searchQuery = "WHERE reportType LIKE '%%'";
        }

        return searchQuery;
    }

    public String getSearchQuery2(String searchType2, String words) {
        String searchQuery= "";
        if(searchType2.equals("title")){
            searchQuery = "AND " + searchType2 + " LIKE '%"+words+"%'";
        }else if(searchType2.equals("renterNickName")){
            searchQuery = "AND " + searchType2 + " = '"+words+"'";
        }else if(searchType2.equals("ownerNickName")){
            searchQuery = "AND " + searchType2 + " = '"+words+"'";
        }else {
            searchQuery = "";
        }


        return searchQuery;
    }

    public List<ReportDto> getReportSearch(int page, String searchType, String searchType2, String words) {
        Map<String, Object> map = new HashMap<>();

        PageDto pageDto = new PageDto();

        int startNum = (page - 1) * pageDto.getPageCount();

        map.put("searchQuery", getSearchQuery(searchType));
        map.put("searchQuery2", getSearchQuery2(searchType2, words));
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());

        return memberReportMapper.getReportList(map);
    }

    public int getReportSearchCnt(String searchType, String searchType2, String words) {
        Map<String, Object> map = new HashMap<>();

        map.put("searchQuery", getSearchQuery(searchType));
        map.put("searchQuery2", getSearchQuery2(searchType2, words));

        return memberReportMapper.getReportListCount(map);
    }

    public PageDto getReportPageCalc(int page,String searchType, String searchType2, String words) {
        PageDto pageDto = new PageDto();

        int totalCount = memberReportMapper.getSearchReportListCount(getSearchQuery(searchType), getSearchQuery2(searchType2, words));
        int totalPage = (int)Math.ceil((double)totalCount / pageDto.getPageCount());
        int startPage = ((int)(Math.ceil((double)page / pageDto.getBlockCount())) - 1 ) * pageDto.getPage() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;

        if(endPage > totalPage) {
            endPage = totalPage;
        }

        pageDto.setStartPage((page - 1) * pageDto.getPageCount());
        pageDto.setTotalPage(totalPage);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setPage(page);

        return pageDto;
    }

}
