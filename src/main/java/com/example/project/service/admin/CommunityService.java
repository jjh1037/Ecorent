package com.example.project.service.admin;

import com.example.project.dto.admin.CommunityDto;
import com.example.project.dto.admin.PageDto;
import com.example.project.mappers.admin.CommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommunityService {
    @Autowired
    CommunityMapper communityMapper;

    @Value("${fileDir}")
    String fileDir;

    public String getSearchQuery(String searchType) {
        String searchQuery= "";
        if(!searchType.isEmpty()){
            searchQuery = "WHERE post_type = '" + searchType + "'";
        }else {
            searchQuery = "WHERE post_type LIKE '%%'";
        }

        return searchQuery;
    }

    public String getSearchQuery2(String searchType2, String words) {
        String searchQuery= "";
        if(searchType2.equals("postTitle")){
            searchQuery = "AND post_title LIKE '%"+words+"%'";
        }else if(searchType2.equals("postWriter")){
            searchQuery = "AND post_writer = '"+words+"'";
        }else {
            searchQuery = "";
        }

        return searchQuery;
    }

    public List<CommunityDto> getCommunitySearch(int page, String searchType, String searchType2, String words) {
        Map<String, Object> map = new HashMap<>();

        PageDto pageDto = new PageDto();

        int startNum = (page - 1) * pageDto.getPageCount();

        map.put("searchQuery", getSearchQuery(searchType));
        map.put("searchQuery2", getSearchQuery2(searchType2, words));
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());

        System.out.println(communityMapper.getCommunityList(map));

        return communityMapper.getCommunityList(map);
    }

    public int getCommunitySearchCnt(String searchType, String searchType2, String words) {
        Map<String, Object> map = new HashMap<>();

        map.put("searchQuery", getSearchQuery(searchType));
        map.put("searchQuery2", getSearchQuery2(searchType2, words));

        return communityMapper.getCommunityCount(map);
    }

    public PageDto communityPageCalc(int page,String searchType, String searchType2, String words) {
        PageDto pageDto = new PageDto();

        int totalCount = communityMapper.getSearchCommunityListCount(getSearchQuery(searchType), getSearchQuery2(searchType2, words));
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

    public Map<String, Object> setCommunityDeleteSelected(List<CommunityDto> checkSelected) {
        Map<String, Object> map = new HashMap<>();
        for(CommunityDto communityDto : checkSelected) {
            communityMapper.setCommunityDelete(communityDto.getPostNum());
        }
        map.put("msg", "success");
        return map;
    }

}
