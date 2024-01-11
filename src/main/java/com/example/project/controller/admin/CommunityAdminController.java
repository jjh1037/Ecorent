package com.example.project.controller.admin;

import com.example.project.dto.admin.BoardDto;
import com.example.project.dto.admin.CommunityDto;
import com.example.project.mappers.admin.CommunityMapper;
import com.example.project.service.admin.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class CommunityAdminController {
    @Autowired
    CommunityService communityService;

    @Autowired
    CommunityMapper communityMapper;


    @GetMapping("/admin/communityList")
    public String getList(Model model,
                          @RequestParam(value="searchType", defaultValue = "") String searchType,
                          @RequestParam(value="searchType2", defaultValue = "") String searchType2,
                          @RequestParam(value="words", defaultValue = "") String words,
                          @RequestParam(value = "page", defaultValue = "1") int page) {

        model.addAttribute("cnt", communityService.getCommunitySearchCnt(searchType, searchType2, words));
        model.addAttribute("list", communityService.getCommunitySearch(page, searchType, searchType2, words));
        model.addAttribute("page", communityService.communityPageCalc(page, searchType, searchType2, words));

        return "admin/board/communityList";
    }

    @GetMapping("/admin/communityView")
    public String getView(@RequestParam int postNum, Model model) {

        model.addAttribute("view", communityMapper.getCommunityView(postNum));
        return "admin/board/communityView";
    }

    @GetMapping("/admin/communityUpdate")
    public String getCommunityUpdate(@RequestParam int postNum, Model model) {
        CommunityDto communityDto = communityMapper.getCommunityView(postNum);

        model.addAttribute("communityList", communityDto);
        return "admin/board/communityUpdate";
    }

    @PostMapping("/admin/communityUpdate")
    public String setCommunityUpdate(@ModelAttribute CommunityDto communityDto) {
        communityMapper.setCommunityUpdate(communityDto);
        return "redirect:/admin/communityView?&postNum=" + communityDto.getPostNum();
    }

    @GetMapping("/admin/communityDelete")
    public String setDelete(@RequestParam int postNum){
        communityMapper.setCommunityDelete(postNum);
        return "redirect:/admin/communityList";
    }

    @PostMapping("/admin/communityDeleteSelected")
    @ResponseBody
    public Map<String, Object> setDeleteSelected(@RequestBody List<CommunityDto> checkSelected) {
        return communityService.setCommunityDeleteSelected(checkSelected);
    }


}
