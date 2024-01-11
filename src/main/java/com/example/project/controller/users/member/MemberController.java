package com.example.project.controller.users.member;

import com.example.project.dto.user.MemberDto;
import com.example.project.dto.user.OwnerMailDto;
import com.example.project.dto.user.RentalFormDto;
import com.example.project.dto.user.UsersItemDto;
import com.example.project.mappers.user.MemberMapper;
import com.example.project.mappers.user.RentalFormMapper;
import com.example.project.service.admin.MailService;
import com.example.project.service.category.MemberService;
import com.example.project.service.category.RentalFormService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    RentalFormMapper rentalMapper;
    @Autowired
    MailService mailService;

    @Autowired
    RentalFormService rentalService;

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request, HttpSession hs){
        //현재 요청의 리퍼러 URL 가져오기
        String referer = request.getHeader("referer");

        //로그아웃 처리
        hs.invalidate();

        return "redirect:" + (referer!=null ? referer : "/");
    }

    /*-----------------------가영 유저페이지 -------------------------*/
    @GetMapping("/memberInfo/info")
    public String getMemberInfo(HttpSession session, Model model){

        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else{
            MemberDto member = (MemberDto) session.getAttribute("user");
            model.addAttribute("member", memberMapper.getMemberId(member.getMemberId()));
            return "users/member/memberInfo/info";
        }
    }

    @GetMapping("/memberInfo/infoUpdate")
    public String getMemberInfoUpdate(HttpSession session, Model model) {
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/login";
        }else{
            MemberDto user = (MemberDto) session.getAttribute("user");

            MemberDto memberDto = memberMapper.getMemberId(user.getMemberId());
            model.addAttribute("member", memberDto);
            return "users/member/memberInfo/infoUpdate";
        }

    }

    @PostMapping("/memberInfo/infoUpdate")
    @ResponseBody
    public Map<String, Object> setMemberInfoUpdate(@ModelAttribute MemberDto memberDto, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(memberDto);
        if(session.getAttribute("user")==null){
            map.put("message","login");
            return map;
        }else{
            if(memberDto != null) {
                memberMapper.setMemberInfoUpdate(memberDto);
                map.put("message", "success");
            }else {
                map.put("message", "failure");
            }
        }
        return map;
    }

    @GetMapping("/memberInfo/memberCancel")
    public String setCancel(@RequestParam int memberId) {
        memberService.setCancel(memberId);
        return "redirect:/index";
    }

    @PostMapping("/memberInfo/memberCancel")
    @ResponseBody
    public Map<String, Object> cancelMembership(@RequestParam int memberId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            memberService.setCancel(memberId);
            session.invalidate(); // 현재 세션 무효화
            response.put("status", "success");
        } catch (Exception e) {
            response.put("status", "error");
            e.printStackTrace();
        }
        return response;
    }

    /*-------------------------------------------------------------------------*/
    @GetMapping("/memberInfo/sentForms")
    public String getSentForm(HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else {
            return "users/member/memberInfo/sentForms";
        }
    }
    @PostMapping("/memberInfo/sentForms")
    @ResponseBody
    public Map<String,Object> getSentFormList(String renterId){
        return rentalService.getSentFormList(renterId);
    }

    @GetMapping("/memberInfo/receivedForms")
    public String getMemberRequest(HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else {
            return "users/member/memberInfo/receivedForms";
        }
    }

    @PostMapping("/memberInfo/receivedForms")
    @ResponseBody
    public Map<String,Object> getRequestList(@RequestParam int memberId){
        return rentalService.getRentalList(memberId);
    }

    @GetMapping("/memberInfo/receivedForms/requestDetail")
    public String getDomesticRequestDetail(@RequestParam int id, Model model, HttpSession session){
        MemberDto member = (MemberDto) session.getAttribute("user");
        if(member==null){
            return "redirect:/users/member/memberLogin/login";
        }else{
            rentalService.updateCheckRequest(member.getMemberId(),id); //유저가 요청을 읽었는지 확인

            RentalFormDto userForm = rentalService.getRentalDetail(id);

            model.addAttribute("form",userForm);
            model.addAttribute("ownerItem",rentalService.getOwnersItem(id));

            List<String> ourCarrier = Arrays.asList("CJ", "PANTOS", "OOCL", "KCTC");
            boolean showCarrierBtn = ourCarrier.contains(userForm.getCarrier());
            model.addAttribute("carrierBtn", showCarrierBtn);

            return "users/member/memberInfo/requestDetail";
        }
    }
    @GetMapping("/memberInfo/requestDetail/confirmForm")
    public String confirmForm(@RequestParam int formId){
        rentalService.confirmForm(formId);
        return "redirect:/users/member/memberInfo/receivedForms/requestDetail?id="+formId;
    }
    @GetMapping("/memberInfo/requestDetail/rejectForm")
    public String rejectForm(@RequestParam int formId){
        rentalService.rejectForm(formId);
        return "redirect:/users/member/memberInfo/receivedForms";
    }
    @GetMapping("/memberInfo/sentFormDetail")
    public String getSentFormDetail(@RequestParam int id, Model model, HttpSession settion){
        if(settion.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else{
            model.addAttribute("sentForm", rentalMapper.getRentalDetail(id));
            model.addAttribute("ownerItem",rentalMapper.getOwnersItem(id));
            return "users/member/memberInfo/sentFormDetail";
        }
    }

    @PostMapping("/sendMail")
    @ResponseBody
    public Map<String, Object> sendMail(@RequestBody OwnerMailDto mdto) throws MessagingException {
        System.out.println(mdto);
        return mailService.mailSend(mdto);
    }

    /*-----------------------------------가영 로그인 ---------------------------------------*/
    @GetMapping("/memberLogin/login")
    public String getMemberLogin(){
        return "users/member/memberLogin/login";
    }

    @PostMapping("/memberLogin/login")
    @ResponseBody
    public Map<String, Object> loginCheck(@ModelAttribute MemberDto mdto, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        MemberDto member = memberMapper.loginCheck(mdto);

        if(member!=null){
            HttpSession memberSession =  request.getSession();
            memberSession.setAttribute("user",member);
            memberSession.setMaxInactiveInterval(3600);
            map.put("success",true);
        }else{
            map.put("success",false);
        }
        return map;
    }

    @GetMapping("/memberLogin/searchEmail")
    public String getSearchEmail() {
        return "users/member/memberLogin/searchEmail";
    }

    @GetMapping("/memberLogin/searchPasswd")
    public String getSearchPasswd() {
        return "users/member/memberLogin/searchPasswd";
    }

    @GetMapping("/memberLogin/resultEmail")
    public String resultEmail() {
        return "users/member/memberLogin/resultEmail";
    }
    @GetMapping("/memberLogin/resultPasswd")
    public String getResultPasswd() {
        return "users/member/memberLogin/resultPasswd";
    }

    @PostMapping("/memberLogin/resultEmail")
    public String getResultEmail(@ModelAttribute MemberDto mdto, Model model) {
        MemberDto user = memberMapper.resultEmail(mdto.getMemberName(), mdto.getMemberPhone());

        if(user!=null){
            model.addAttribute("result", user);
        }else{
            model.addAttribute("result","none");
        }
        return "users/member/memberLogin/resultEmail";
    }
    @PostMapping("/memberLogin/resultPasswd")
    public String getResultPasswd(@ModelAttribute MemberDto mdto, Model model) {
        model.addAttribute("result", memberMapper.resultPasswd(mdto.getMemberNickName(), mdto.getMemberEmail()));
        return "users/member/memberLogin/resultPasswd";
    }

    /*------------------------------가영 회원가입 유효성---------------------------------------*/

    @PostMapping("/memberLogin/join/emailCheck")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestParam String memberEmail){
        return memberService.emailCheck(memberEmail);
    }

    @GetMapping("/memberLogin/join")
    public String getMemberJoin(){
        return "users/member/memberLogin/join";
    }

    @PostMapping("/memberLogin/join")
    @ResponseBody
    public Map<String, Object> insertMember(@ModelAttribute MemberDto mdto) {
        Map<String, Object> map = new HashMap<>();
        if(mdto!=null){
            memberMapper.insertMember(mdto);
            map.put("message", "success");
        }else{
            map.put("message", "failure");
        }
        return map;
    }

    @GetMapping("/memberLogin/join/nickCheck")
    @ResponseBody
    public Map<String,Object> nickNameCheck(String nickName){
        return memberService.nickNameCheck(nickName);
    }

}
