package com.example.project.service.shipping;

import com.example.project.dto.admin.PageDto;
import com.example.project.dto.admin.ShippingDto;
import com.example.project.dto.user.RequestDto;
import com.example.project.mappers.admin.ShippingMapper;
import com.example.project.mappers.user.UserShippingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserShippingListService {
    @Autowired
    UserShippingMapper userShippingMapper;

    public List<ShippingDto> getSearch(int page, String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery= "";
        if(searchType.equals("2")) {
            searchQuery = "WHERE name LIKE '%"+words+"%'";
        } else if(searchType.equals("1")) {
            searchQuery = "WHERE name LIKE '%"+words+"%'" + "AND domestic = 1";
        } else if(searchType.equals("0")) {
            searchQuery = "WHERE name LIKE '%"+words+"%'" + "AND domestic = 0";
        } else {
            searchQuery = "";
        }

        PageDto pageDto = new PageDto();
        // limit 시작, 개수

        int startNum = (page - 1) * pageDto.getPageCount();

        map.put("searchQuery", searchQuery);
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());

        return userShippingMapper.getList(map);
    }

    public int getSearchCnt(String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery= "";
        if(searchType.equals("2")) {
            searchQuery = "WHERE name LIKE '%"+words+"%'";
        } else if(searchType.equals("1")) {
            searchQuery = "WHERE name LIKE '%"+words+"%'" + "AND domestic = 1";
        } else if(searchType.equals("0")) {
            searchQuery = "WHERE name LIKE '%"+words+"%'" + "AND domestic = 0";
        } else {
            searchQuery = "";
        }

        map.put("searchQuery", searchQuery);

        return userShippingMapper.getListCount(map);
    }

    public PageDto shippingPageCalc(int page,String searchType, String words) {
        PageDto pageDto = new PageDto();

        String searchQuery= "";
        if(searchType.equals("2")) {
            searchQuery = "WHERE name LIKE '%"+words+"%'";
        } else if(searchType.equals("1")) {
            searchQuery = "WHERE name LIKE '%"+words+"%'" + "AND domestic = 1";
        } else if(searchType.equals("0")) {
            searchQuery = "WHERE name LIKE '%"+words+"%'" + "AND domestic = 0";
        } else {
            searchQuery = "";
        }
        int totalCount = userShippingMapper.getSearchListCount(searchQuery);
        int totalPage = (int)Math.ceil((double)totalCount / pageDto.getPageCount());
        int startPage = ((int)(Math.ceil((double)page / pageDto.getBlockCount())) - 1 ) * pageDto.getPage() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;

        if(endPage > totalPage) {
            endPage = totalPage;
        }

        pageDto.setPage(page);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setTotalPage(totalPage);

        return pageDto;
    }


}
