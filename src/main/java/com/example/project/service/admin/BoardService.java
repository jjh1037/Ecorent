package com.example.project.service.admin;

import com.example.project.dto.admin.BoardDto;
import com.example.project.dto.admin.PageDto;
import com.example.project.mappers.admin.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    @Value("${fileDir}")
    String fileDir;
    public String getSearchQuery(String searchType) {
        String searchQuery= "";
        if(!searchType.isEmpty()){
            searchQuery = "WHERE item_category = '" + searchType + "'";
        }else {
            searchQuery = "WHERE item_category LIKE '%%'";
        }

        return searchQuery;
    }

    public String getSearchQuery2(String searchType2, String words) {
        String searchQuery= "";
        if(searchType2.equals("title")){
            searchQuery = "AND " + searchType2 + " LIKE '%"+words+"%'";
        }else if(searchType2.equals("memberNickName")){
            searchQuery = "AND " + searchType2 + " = '"+words+"'";
        }else {
            searchQuery = "";
        }


        return searchQuery;
    }

    public List<BoardDto> getBoardSearch(int page, String searchType, String searchType2, String words) {
        Map<String, Object> map = new HashMap<>();

        PageDto pageDto = new PageDto();

        int startNum = (page - 1) * pageDto.getPageCount();

        map.put("searchQuery", getSearchQuery(searchType));
        map.put("searchQuery2", getSearchQuery2(searchType2, words));
        map.put("startNum", startNum);
        map.put("offset", pageDto.getPageCount());

        return boardMapper.getBoardList(map);
    }

    public int getBoardSearchCnt(String searchType, String searchType2, String words) {
        Map<String, Object> map = new HashMap<>();

        map.put("searchQuery", getSearchQuery(searchType));
        map.put("searchQuery2", getSearchQuery2(searchType2, words));

        return boardMapper.getBoardListCount(map);
    }

    public PageDto boardPageCalc(int page,String searchType, String searchType2, String words) {
        PageDto pageDto = new PageDto();

        int totalCount = boardMapper.getSearchBoardListCount(getSearchQuery(searchType), getSearchQuery2(searchType2, words));
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

    public void setBoardDelete(int itemId ) {
        if(itemId  > 0) {
            BoardDto boardDto = boardMapper.getBoardView(itemId);
            boardMapper.setBoardDelete(itemId);

            String removeFile = fileDir + boardDto.getFolderName() + "/"  + boardDto.getFileName();
            String isFileEmpty = boardDto.getFileName();
            if(isFileEmpty != null) {
                File f = new File(removeFile);
                if(f.exists()) {
                    f.delete();
                }
            }
        }
    }

    public Map<String, Object> setBoardDeleteSelected(List<BoardDto> checkSelected) {
        Map<String, Object> map = new HashMap<>();
        for(BoardDto boardDto : checkSelected) {
            boardMapper.setBoardDelete(boardDto.getItemId());
        }
        map.put("msg", "success");
        return map;
    }
}
