package com.example.project.service.admin;

import com.example.project.dto.admin.BoardDto;
import com.example.project.dto.admin.ShippingDto;
import com.example.project.mappers.admin.ShippingMapper;
import com.example.project.dto.admin.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShippingService {

    @Autowired
    ShippingMapper shippingMapper;

    public List<ShippingDto> getSearch(int page, String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery= "";
        if(searchType.equals("name")) {
            searchQuery = "WHERE " + searchType + " LIKE '%"+words+"%'";
        }else if(searchType.equals("id")) {
            searchQuery = "WHERE " + searchType + " = '" + words + "'";
        }else {
            searchQuery = "";
        }

        PageDto pageDto = new PageDto();
        // limit 시작, 개수

        int startNum = (page - 1) * pageDto.getPageCount();

        map.put("searchQuery", searchQuery);
//        System.out.println(searchQuery);
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());

        return shippingMapper.getList(map);
    }

    public int getSearchCnt(String searchType, String words) {
        Map<String, Object> map = new HashMap<>();

        String searchQuery = "";
        if(searchType.equals("name")) {
            searchQuery = "WHERE " + searchType + " LIKE '%"+words+"%'";
        }else if(searchType.equals("id")) {
            searchQuery = "WHERE " + searchType + " = '" + words + "'";
        }else {
            searchQuery = "";
        }

        map.put("searchQuery", searchQuery);

        return shippingMapper.getListCount(map);
    }

    public PageDto shippingPageCalc(int page,String searchType, String words) {
        PageDto pageDto = new PageDto();

        String searchQuery = "";
        if(searchType.equals("name")) {
            searchQuery = "WHERE " + searchType + " LIKE '%"+words+"%'";
        }else if(searchType.equals("id")) {
            searchQuery = "WHERE " + searchType + " = '" + words + "'";
        }else {
            searchQuery = "";
        }

        int totalCount = shippingMapper.getSearchListCount(searchQuery);
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

    public void setDelete(int id) {
        if(id > 0) {
            ShippingDto shippingDto = shippingMapper.getView(id);
            shippingMapper.setDelete(id);

            String removeFile = shippingDto.getSavedFilePathName();
            if(removeFile != null) {
                File f = new File(removeFile);
                if(f.exists()) {
                    f.delete();
                }
            }
        }
    }

    public Map<String, Object> setShippingDeleteSelected(List<ShippingDto> checkSelected) {
        Map<String, Object> map = new HashMap<>();
        for(ShippingDto shippingDto : checkSelected) {
            shippingMapper.setDelete(shippingDto.getId());
        }
        map.put("msg", "success");
        return map;
    }


}
