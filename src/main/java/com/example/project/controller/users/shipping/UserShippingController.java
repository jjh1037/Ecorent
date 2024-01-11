package com.example.project.controller.users.shipping;

import com.example.project.dto.user.RequestDto;
import com.example.project.mappers.user.UserShippingMapper;
import com.example.project.service.admin.MailService;
import com.example.project.service.shipping.UserShippingListService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/users/shipping")
public class UserShippingController {

    @Autowired
    UserShippingMapper userShippingMapper;

    @Autowired
    UserShippingListService userShippingListService;

    @Autowired
    MailService mailService;

    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/u_shippingList")
    public String getUshippingList(Model model,
                                   @RequestParam(value="searchType", defaultValue = "") String searchType,
                                   @RequestParam(value="words", defaultValue = "") String words,
                                   @RequestParam(value ="page", defaultValue = "1") int page) {

        model.addAttribute("cnt", userShippingListService.getSearchCnt(searchType, words));
        model.addAttribute("list", userShippingListService.getSearch(page, searchType, words));
        model.addAttribute("page", userShippingListService.shippingPageCalc(page, searchType, words));


        return "users/shipping/u_shippingList";
    }

    @GetMapping("/u_shippingRequest")
    public String getUshippingRequest(@RequestParam int id, Model model) {

        model.addAttribute("view", userShippingMapper.getView(id));

        return "users/shipping/u_shippingRequest";
    }
    @PostMapping("/u_shippingRequest")
    @ResponseBody
    public Map<String, Object> setUshippingRequest(@ModelAttribute RequestDto requestDto) throws MessagingException {
        System.out.println(requestDto);
        userShippingMapper.setRequest(requestDto);
        return mailService.mailSend(requestDto);
    }



}
