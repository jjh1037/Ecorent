package com.example.project.controller.users.member;

import com.example.project.dto.user.CartDto;
import com.example.project.dto.user.RentalFormDto;
import com.example.project.mappers.user.CartMapper;
import com.example.project.mappers.user.CategoryMapper;
import com.example.project.mappers.user.RentalFormMapper;
import com.example.project.service.member.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    RentalFormMapper rentalFormMapper;
    @Autowired
    CartMapper cartMapper;

    @Autowired
    CartService cartService;

    @GetMapping("/users/member/cart")
    public String getCart(HttpSession session) {
        if(session.getAttribute("user")==null) {
            return "redirect:/users/member/memberLogin/login";
        }else {
            return "users/member/memberInfo/cart/cart";
        }
    }

    @PostMapping("/users/member/cart")
    @ResponseBody
    public Map<String, Object> getCartList(@RequestParam int memberId) {
        Map<String, Object> map = new HashMap<>();
        List<Object> test = new ArrayList<>();
        for(CartDto cartDto : cartMapper.getCart(memberId)) {
            test.add(cartDto);
        }

        map.put("list", test); // return 값 list
        return map;
    }

    @GetMapping("/CartDuplicateCheck")
    @ResponseBody
    public Map<String, Object> setCartDuplicateCheck(@ModelAttribute CartDto cartDto) {
        System.out.println(cartDto);
        Map<String, Object> map = new HashMap<>();
        if(cartMapper.getCartDuplicateCheck(cartDto) == 1) { // 중복된 값
            map.put("msg", "failure");
        } else {
            map.put("msg", "success");
        }

        return map;
    }

    @GetMapping("/addCart")
    @ResponseBody
    public Map<String, Object> setCart(@ModelAttribute CartDto cartDto){
        cartMapper.setCart(cartDto);
        return Map.of("msg","success");
    }

    @GetMapping("/deleteCart")
    public String setDeleteCart(@RequestParam int cartId) {
        cartMapper.setDelete(cartId);
        return "redirect:/users/member/cart";
    }

    @PostMapping("/deleteSelected")
    @ResponseBody
    public Map<String, Object> setDeleteSelected(@RequestBody List<CartDto> checkSelected) {
        return cartService.setSelDelete(checkSelected);
    }

    @GetMapping("/deleteItemAndMemberId")
    @ResponseBody
    public Map<String, Object> setDeleteItemAndMemberId(@ModelAttribute CartDto cartDto) {
        cartMapper.setDeleteItemAndMemberId(cartDto);
        return Map.of("msg","success");
    }

    @PostMapping("/changeToWaiting")
    @ResponseBody
    public Map<String, Object> setChangeToWaitingAll(@RequestBody List<CartDto> sendFormAll) {
        return cartService.setChangeToWaitingAll(sendFormAll);
    }

    @GetMapping("/users/member/cart/cartSavingFormCar")
    public String setFormSaving(@RequestParam int id, Model model) {
        model.addAttribute("list", categoryMapper.getviewItem(id));
        return "users/member/memberInfo/cart/cartSavingFormCar";
    }

    @PostMapping("/users/member/cart/cartSavingFormCar")
    public String setFormSaving(@ModelAttribute RentalFormDto rentalDto, Model model){
        rentalFormMapper.setCartRentalFormSaving(rentalDto);
        rentalFormMapper.setChangeCartFormWritten(rentalDto);
        rentalFormMapper.setCheckRequest(rentalDto.getOwnerId(),rentalDto.getFormId());
        return "redirect:/users/member/cart";

    }


}
