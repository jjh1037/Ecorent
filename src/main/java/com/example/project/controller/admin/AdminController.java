package com.example.project.controller.admin;

import com.example.project.dto.user.MemberDto;
import com.example.project.mappers.admin.AdminMapper;
import com.example.project.mappers.user.MemberMapper;
import com.example.project.service.admin.AdminService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    AdminMapper adminMapper;

    @GetMapping("")
    public String getIndex(Model model) {

        model.addAttribute("boardList", adminService.getAdminIndexBoardSearch());
        model.addAttribute("communityList", adminService.getAdminIndexCommunitySearch());
        model.addAttribute("memberList", adminService.getAdminIndexMemberSearch());
        model.addAttribute("reportList", adminService.getAdminIndexReportSearch());
        model.addAttribute("shippingList", adminService.getAdminIndexShippingSearch());

        return "admin/index";
    }

    @GetMapping("/member/memberList")
    public String getMemberList(Model model,
                                @RequestParam(value = "searchType", defaultValue = "")String searchType,
                                @RequestParam(value = "words", defaultValue = "")String words,
                                @RequestParam(value = "page", defaultValue = "1")int page) {

        model.addAttribute("cnt", adminService.getSearchCnt(searchType, words));
        model.addAttribute("list", adminService.getSearch(page, searchType, words));
        model.addAttribute("page", adminService.adminPageCalc(page, searchType, words));

        return "admin/member/memberList";
    }

    @GetMapping("/member/memberView")
    public String getMemberView(@RequestParam(name = "memberId", required = true) int memberId, Model model) {
        model.addAttribute("view", memberMapper.getMemberId(memberId));
        return "admin/member/memberView";
    }
    @GetMapping("/member/memberUpdate")
    public String getMemberUpdate(@RequestParam("memberId") int memberId, Model model) {
        MemberDto membersDto = memberMapper.getMemberId(memberId);
        model.addAttribute("member", membersDto);
        return "admin/member/memberUpdate";
    }

    @PostMapping("/member/memberUpdate")
    @ResponseBody
    public Map<String, Object> setMemberUpdate(@ModelAttribute MemberDto membersDto) {

        Map<String, Object> map = new HashMap<>();
        if(membersDto != null) {
            adminMapper.setMemberUpdate(membersDto);
            map.put("message", "success");
        }else {
            map.put("message", "failure");
        }

        return map;
    }

    @GetMapping("/member/memberDelete")
    public String setDelete(@RequestParam int memberId) {
        adminService.setDelete(memberId);
        return "redirect:/admin/member/memberList";
    }

    /*가영 선택 삭제*/
    @PostMapping("/member/memberDelete")
    public String deleteMembers(@RequestParam List<Integer> idList) {
        for (int memberId : idList) {
            adminService.setDelete(memberId);
        }
        return "redirect:/admin/member/memberList";
    }

}
