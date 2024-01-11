package com.example.project.service.admin;

import com.example.project.dto.admin.*;
import com.example.project.dto.user.MemberDto;
import com.example.project.mappers.admin.AdminMapper;
import com.example.project.mappers.user.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    MemberMapper memberMapper;

    public List<MemberDto> getSearch(int page, String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery="";
        if(searchType.equals("memberName")) {
            searchQuery = "WHERE " + searchType + " LIKE '%"+ words+"%'";
        }else if(searchType.equals("memberNickName")) {
            searchQuery = "WHERE " + searchType + " = '" + words + "'";
        }else {
            searchQuery = "";
        }

        PageDto pageDto = new PageDto();

        int startNum = (page - 1) * pageDto.getPageCount();

        map.put("searchQuery", searchQuery);
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());

        return adminMapper.getList(map);
    }

    public int getSearchCnt(String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery="";
        if(searchType.equals("memberName")) {
            searchQuery = "WHERE " + searchType + " LIKE '%"+ words+"%'";
        }else if(searchType.equals("memberId")) {
            searchQuery = "WHERE " + searchType + " = '" + words + "'";
        }else {
            searchQuery = "";
        }

        map.put("searchQuery", searchQuery);

        return adminMapper.getListCount(map);
    }

    public PageDto adminPageCalc(int page,String searchType, String words) {
        PageDto pageDto = new PageDto();

        String searchQuery="";
        if(searchType.equals("memberName")) {
            searchQuery = "WHERE " + searchType + " LIKE '%"+ words+"%'";
        }else if(searchType.equals("memberNickName")) {
            searchQuery = "WHERE " + searchType + " = '" + words + "'";
        }else {
            searchQuery = "";
        }

        int totalCount = adminMapper.getSearchListCount(searchQuery);
        int totalPage = (int)Math.ceil((double)totalCount / pageDto.getPageCount());
        int startPage = ((int)(Math.ceil((double)page / pageDto.getBlockCount())) -1)* pageDto.getPage()+1;
        int endPage = startPage+pageDto.getBlockCount() -1;

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

    public void setDelete(int memberId) {
        if(memberId > 0) {
            MemberDto membersDto = memberMapper.getMemberId(memberId);
            if(membersDto != null) {
                adminMapper.setDelete(memberId);
            }
        }
    }

    public List<BoardDto> getAdminIndexBoardSearch() {
        Map<String, Object> map = new HashMap<>();

        return adminMapper.getAdminIndexBoardList(map);
    }

    public List<CommunityDto> getAdminIndexCommunitySearch() {
        Map<String, Object> map = new HashMap<>();

        return adminMapper.getAdminIndexCommunityList(map);
    }

    public List<MemberDto> getAdminIndexMemberSearch() {
        Map<String, Object> map = new HashMap<>();

        return adminMapper.getAdminIndexMemberSearch(map);
    }

    public List<ReportDto> getAdminIndexReportSearch() {
        Map<String, Object> map = new HashMap<>();

        return adminMapper.getAdminIndexReportSearch(map);
    }

    public List<ShippingDto> getAdminIndexShippingSearch() {
        Map<String, Object> map = new HashMap<>();

        return adminMapper.getAdminIndexShippingSearch(map);
    }

}
