package com.example.project.controller.users.categoty;

import com.example.project.dto.user.RentalFormDto;
import com.example.project.mappers.user.CategoryMapper;
import com.example.project.mappers.user.RentalFormMapper;
import com.example.project.service.category.RentalFormService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RentalFormController {

    @Autowired
    RentalFormMapper rentalMapper;

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    RentalFormService rentalService;

    @GetMapping("/users/category/car/applyCar")
    public String setApplyCar(@RequestParam int id, Model model, HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else{
            model.addAttribute("list", categoryMapper.getviewItem(id));
            return "users/category/car/applyCar";
        }
    }

    @PostMapping("/users/category/car/applyCar")
    public String setApplyCar(@ModelAttribute RentalFormDto rentalDto, Model model){
        rentalMapper.setRentalForm(rentalDto);
        int formId = rentalDto.getFormId();

        rentalService.setCheckRequest(rentalDto.getOwnerId(),formId);
        return "redirect:/users/category/car";
    }
    @GetMapping("/users/category/applyProduct")
    public String getApplyProductView(@RequestParam int id, Model model, HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else{
            model.addAttribute("list", categoryMapper.getviewItem(id));
            return "users/category/applyProduct";
        }
    }
    @PostMapping("/users/category/applyProduct") //자동차 외의 카테고리 렌탈신청폼 저장
    public String setApplyProduct(@ModelAttribute RentalFormDto rentalDto, Model model){
        rentalMapper.setRentalForm(rentalDto);
        int formId = rentalDto.getFormId();
        rentalService.setCheckRequest(rentalDto.getOwnerId(),formId);

        String category = rentalMapper.getCategoryName(formId);
        System.out.println(category);

        switch (category){
            case "무대":
                return "redirect:/users/category/stage";
            case "상업용가전":
                return "redirect:/users/category/restaurant";
            case "오피스":
                return "redirect:/users/category/office";
            case "현장장비":
                return "redirect:/users/category/construction";
            case "악기":
                return "redirect:/users/category/instrument";
            case "기타장비":
                return "redirect:/users/category/etc";
            case "해외":
                return "redirect:/users/category/oversea";
            default:
                return "redirect:/";
        }
    }


}
