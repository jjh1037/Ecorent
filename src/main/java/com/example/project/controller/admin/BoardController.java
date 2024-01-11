package com.example.project.controller.admin;

import com.example.project.dto.admin.BoardDto;
import com.example.project.mappers.admin.BoardMapper;
import com.example.project.service.admin.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardMapper boardMapper;
    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/admin/boardList")
    public String getList(Model model,
                          @RequestParam(value="searchType", defaultValue = "") String searchType,
                          @RequestParam(value="searchType2", defaultValue = "") String searchType2,
                          @RequestParam(value="words", defaultValue = "") String words,
                          @RequestParam(value = "page", defaultValue = "1") int page) {

        model.addAttribute("cnt", boardService.getBoardSearchCnt(searchType, searchType2, words));
        model.addAttribute("list", boardService.getBoardSearch(page, searchType, searchType2, words));
        model.addAttribute("page", boardService.boardPageCalc(page, searchType, searchType2, words));

        return "admin/board/boardList";
    }

    @GetMapping("/admin/boardViewCar")
    public String getViewCar(@RequestParam int itemId, Model model) {

        model.addAttribute("view", boardMapper.getBoardView(itemId));
        return "admin/board/boardViewCar";
    }

    @GetMapping("/admin/boardView")
    public String getView(@RequestParam int itemId, Model model) {

        model.addAttribute("view", boardMapper.getBoardView(itemId));
        return "admin/board/boardView";
    }

    @GetMapping("/admin/boardDelete")
    public String setDelete(@RequestParam int itemId){
        boardService.setBoardDelete(itemId);
        return "redirect:/admin/boardList";
    }

    @PostMapping("/admin/boardDeleteSelected")
    @ResponseBody
    public Map<String, Object> setDeleteSelected(@RequestBody List<BoardDto> checkSelected) {
        return boardService.setBoardDeleteSelected(checkSelected);
    }

    @GetMapping("/admin/boardUpdateCar")
    public String getBoardUpdateCar(@RequestParam int itemId, Model model) {
        BoardDto boardDto = boardMapper.getBoardView(itemId);

        model.addAttribute("boardList", boardDto);
        return "admin/board/boardUpdateCar";
    }

    @PostMapping("/admin/boardUpdateCar")
    public String setBoardUpdateCar(@RequestParam("file") MultipartFile file,
                                    @ModelAttribute BoardDto boardDto) throws IOException {

        if(!file.isEmpty()) {
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);

            if (!makeFolder.exists()) {
                makeFolder.mkdir();
            }

            //첨부한 파일의 원래이름 가져오기
            String originName = file.getOriginalFilename();
            //난수생성 하기
            String uuid = UUID.randomUUID().toString();
            //파일이름의 온점을 기준으로 잘라내기
            String extention = originName.substring(originName.lastIndexOf("."));
            //앞서 생성했던 랜덤문자열과 잘라낸 확장자 붙여주기 (이미지를 표시할 때)
            String savedFileName = uuid + extention;
            //저장될 이미지의 최종 경로 변수로 지정
            String savedPathFileName = fileDir + folderName + "/" + savedFileName;

            boardDto.setFolderName(folderName);
            boardDto.setFileName(savedFileName);

            file.transferTo(new File(savedPathFileName));
        }else {
            boardDto.setFolderName(boardDto.getFolderName());
            boardDto.setFileName(boardDto.getFileName());
        }

        boardMapper.setBoardUpdateCar(boardDto);

        return "redirect:/admin/boardViewCar?&itemId=" + boardDto.getItemId();
    }


    @GetMapping("/admin/boardUpdate")
    public String getBoardUpdate(@RequestParam int itemId, Model model) {
        BoardDto boardDto = boardMapper.getBoardView(itemId);

        model.addAttribute("boardList", boardDto);
        return "admin/board/boardUpdate";
    }

    @PostMapping("/admin/boardUpdate")
    public String setBoardUpdate(@RequestParam("file") MultipartFile file,
                                    @ModelAttribute BoardDto boardDto) throws IOException {

        if(!file.isEmpty()) {
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);

            if (!makeFolder.exists()) {
                makeFolder.mkdir();
            }

            //첨부한 파일의 원래이름 가져오기
            String originName = file.getOriginalFilename();
            //난수생성 하기
            String uuid = UUID.randomUUID().toString();
            //파일이름의 온점을 기준으로 잘라내기
            String extention = originName.substring(originName.lastIndexOf("."));
            //앞서 생성했던 랜덤문자열과 잘라낸 확장자 붙여주기 (이미지를 표시할 때)
            String savedFileName = uuid + extention;
            //저장될 이미지의 최종 경로 변수로 지정
            String savedPathFileName = fileDir + folderName + "/" + savedFileName;

            boardDto.setFolderName(folderName);
            boardDto.setFileName(savedFileName);

            file.transferTo(new File(savedPathFileName));
        }else {
            boardDto.setFolderName(boardDto.getFolderName());
            boardDto.setFileName(boardDto.getFileName());
        }

        boardMapper.setBoardUpdate(boardDto);

        return "redirect:/admin/boardView?&itemId=" + boardDto.getItemId();
    }


}
