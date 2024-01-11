package com.example.project.controller.admin;

import com.example.project.dto.admin.ReportDto;
import com.example.project.dto.user.MemberDto;
import com.example.project.mappers.admin.MemberReportMapper;
import com.example.project.service.admin.MemberReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MemberReportController {

    @Autowired
    MemberReportMapper memberReportMapper;

    @Autowired
    MemberReportService memberReportService;

    @GetMapping("/memberReport")
    public String getReportList(Model model,
                                @RequestParam(value = "searchType", defaultValue = "") String searchType,
                                @RequestParam(value = "searchType2", defaultValue = "") String searchType2,
                                @RequestParam(value = "words", defaultValue = "") String words,
                                @RequestParam(value = "page", defaultValue = "1") int page) {
        model.addAttribute("cnt", memberReportService.getReportSearchCnt(searchType, searchType2, words));
        model.addAttribute("list", memberReportService.getReportSearch(page, searchType, searchType2, words));
        model.addAttribute("page", memberReportService.getReportPageCalc(page, searchType, searchType2, words));

        return "admin/member/memberReportList";
    }

    @PostMapping("/report/deleteSelected")
    @ResponseBody
    public Map<String, Object> setDeleteSelected(@RequestBody List<ReportDto> reportId) {

        for (ReportDto reportDto : reportId) {
            memberReportMapper.setDeleteSelected(reportDto.getReportId());
        }

        return Map.of("msg", "success");
    }

    @GetMapping("/report/viewReport")
    public String setViewReport(@RequestParam int reportId, Model model) {
        model.addAttribute("view", memberReportMapper.getReportView(reportId));
        return "admin/member/memberReportView";
    }

    @GetMapping("/report/deleteItem")
    public String setDeleteItem(@RequestParam int itemId) {
        memberReportMapper.setDeleteItem(itemId);
        return "redirect:/memberReport";
    }

    @GetMapping("/report/MemberDelete")
    public String setMemberDelete(@RequestParam int ownerId) {
        memberReportMapper.setDeleteMember(ownerId);
        return "redirect:/memberReport";
    }

    @PostMapping("/reportCar")
    public String setReportCar(@ModelAttribute ReportDto reportDto, HttpSession session) {

        MemberDto user = (MemberDto) session.getAttribute("user");
        reportDto.setRenterId(user.getMemberId());
        reportDto.setRenterNickName(user.getMemberNickName());

        memberReportMapper.setReport(reportDto);

        return "redirect:/users/category/car/viewCar?id=" + reportDto.getItemId();
    }

    @PostMapping("/reportProduct")
    public String setReportProduct(@ModelAttribute ReportDto reportDto, HttpSession session) {

        MemberDto user = (MemberDto) session.getAttribute("user");
        reportDto.setRenterId(user.getMemberId());
        reportDto.setRenterNickName(user.getMemberNickName());

        memberReportMapper.setReport(reportDto);

        return "redirect:/users/category/viewProduct?id=" + reportDto.getItemId();
    }

    @PostMapping("/report/checkIsCar")
    @ResponseBody
    public String getCheckIsCar(@RequestParam int itemId) {
        if(memberReportMapper.getCheckIsCar(itemId) == 1) {
            return "/admin/boardViewCar?&itemId=";
        }else {
            return "/admin/boardView?&itemId=";
        }

    }

}
