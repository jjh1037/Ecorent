package com.example.project.service.category;

import com.example.project.dto.user.PageDto;
import com.example.project.dto.user.UsersPostDto;
import com.example.project.mappers.user.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    @Autowired
    PostMapper postMapper;

    public PageDto pageInfo(int page, String searchType, String words){
        PageDto pageDto = new PageDto();

        String searchQuery = getSearch(searchType, words);

        int totalCount=postMapper.getCountSearch(searchQuery);
        int totalPage = (int)Math.ceil((double)totalCount/pageDto.getPageCount()); //전체 페이지수
        int startPage = (((int)Math.ceil((double)page / pageDto.getBlockCount()))-1)*pageDto.getBlockCount()+1; //현재 페이지네비의 첫번째 페이지
        int endPage = startPage+pageDto.getBlockCount()-1; //현재 페이지네비의 마지막 페이지

        if(endPage>totalPage){
            endPage=totalPage;
        }

        pageDto.setStartNum((page-1)*pageDto.getPageCount());
        pageDto.setTotalPage(totalPage);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setPage(page);

        return pageDto;
    }

    public String getSearch(String searchType, String words){
        String searchQuery="";
        if(!searchType.isEmpty()&&words.isEmpty()){
            searchQuery=" WHERE post.post_type='"+searchType+"'";
        }else if(searchType.isEmpty()&&!words.isEmpty()){
            searchQuery=" WHERE post.post_title LIKE CONCAT('%','"+words+"','%')";
        }else if(!searchType.isEmpty()&&!words.isEmpty()){
            searchQuery=" WHERE post.post_type='"+searchType+"' AND post.post_title LIKE CONCAT('%','"+words+"','%')";
        }else{
            searchQuery="";
        }
        return searchQuery;
    }

    public List<UsersPostDto> getPostList(int page, String searchType, String words){

        PageDto pageDto = pageInfo(page, searchType, words);
        String searchQuery = getSearch(searchType, words);

        Map<String, Object> map = new HashMap<>();
        map.put("searchQuery", searchQuery);
        map.put("startNum", pageDto.getStartNum());
        map.put("offset", pageDto.getPageCount());

        return postMapper.getPostList(map);
    }



}
