package com.example.project.controller;

import com.example.project.dto.user.MemberDto;
import com.example.project.mappers.user.CategoryMapper;
import com.example.project.mappers.user.MemberMapper;
import com.example.project.mappers.user.RentalFormMapper;
import com.example.project.service.category.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    RentalFormMapper rentalMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    MemberMapper memberMapper;
    @PostMapping("/index")
    @ResponseBody
    public Map<String, Object> getNewItemList(HttpSession session){
        Map<String, Object> map = new HashMap<>();
        MemberDto member = (MemberDto) session.getAttribute("user");
        if (member != null) {
            map.put("count", rentalMapper.countCheckRequest(member.getMemberId()));
            map.put("countMsg", memberMapper.getCountUnreadMsg(member.getMemberId()));
        }
        map.put("list",categoryMapper.getNewItemList());
        return map;
    }
    @GetMapping("/users/searchMain")
    public String getSearch(@RequestParam(required = false) String searchWord, Model model){
        if(searchWord==null || searchWord.isEmpty()){
            model.addAttribute("list", Collections.emptyList());
        }else{
            model.addAttribute("list",categoryService.getMainSearch(searchWord));
        }
        return "users/searchMain";
    }
}
