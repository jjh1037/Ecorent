package com.example.project.controller.users.categoty;

import com.example.project.dto.user.MemberDto;
import com.example.project.dto.user.MessageDto;
import com.example.project.mappers.user.MemberMapper;
import com.example.project.service.category.MemberService;
import com.example.project.service.category.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    MemberService memberService;
    @Autowired
    MessageService messageService;

    @GetMapping("/users/member/memberInfo/messages")
    public String getMessagesView(HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else {
            return "users/member/memberInfo/messages";
        }
    }
    @PostMapping("/users/category/car/msgCar")
    public String setMessage(@ModelAttribute MessageDto msgDto, HttpSession session){

        MemberDto user = (MemberDto) session.getAttribute("user");
        msgDto.setSentBy(user.getMemberId());
        System.out.println(msgDto);
        memberMapper.setMessage(msgDto);

        return "redirect:/users/category/car/viewCar?id="+msgDto.getItemId();
    }

    @PostMapping("/users/member/memberInfo/messages")
    @ResponseBody
    public Map<String,Object> getMessageList(HttpSession session){
        MemberDto user = (MemberDto) session.getAttribute("user");
        System.out.println(messageService.getMessageList(user.getMemberId()));
        return messageService.getMessageList(user.getMemberId());
    }

    @PostMapping("/users/member/memberInfo/deleteMsgs")
    @ResponseBody
    public Map<String,Object> setDeleteMsgs(@RequestBody List<MessageDto> selectedMsgs, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        MemberDto user = (MemberDto) session.getAttribute("user");
        int memberId = user.getMemberId();

        return messageService.setDeleteMsgs(memberId,selectedMsgs);
    }

    @GetMapping("/users/member/memberInfo/msgView")
    public String getmsgView(@RequestParam("renterId") int renterId, @RequestParam("ownerId") int ownerId,@RequestParam("itemId") int itemId, Model model, HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else{
            model.addAttribute("view",memberMapper.getmsgView(itemId, renterId, ownerId));
            System.out.println("renterId: " + renterId + ", ownerId: " + ownerId + ", itemId: " + itemId);
            return "users/member/memberInfo/msgView";
        }
    }

    @PostMapping("/users/member/memberInfo/msgView")
    @ResponseBody
    public Map<String, Object> getMessages(MessageDto mdto){
        return messageService.getMessages(mdto);
    }

    @PostMapping("/users/member/memberInfo/sendMsg")
    @ResponseBody
    public Map<String, Object> setMsgContent(@ModelAttribute MessageDto mdto){
        memberMapper.setMessageContent(mdto);
        return Map.of("result","good");
    }
    @PostMapping("/users/member/memberInfo/messages/checkMsg")
    @ResponseBody
    public void setCheckMsg(@RequestParam int msgId){
        memberMapper.setCheckIsMsgRead(msgId);
    }
}
