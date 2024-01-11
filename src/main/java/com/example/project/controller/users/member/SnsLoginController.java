package com.example.project.controller.users.member;

import com.example.project.dto.user.MemberDto;
import com.example.project.mappers.user.MemberMapper;
import com.example.project.service.category.SnsLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.util.HashMap;

@Controller
public class SnsLoginController {

    @Autowired
    SnsLoginService snsLoginService;


    @Autowired
    MemberMapper memberMapper;
    /*------------------------카카오 로그인---------------------------*/

    @RequestMapping("/oauth/kakao")
    public String kakaoCallback(@RequestParam(value = "code") String code, HttpServletRequest request) throws Exception {
        System.out.println("code: " + code);
        String accessToken = snsLoginService.getAccessToken(code);
        HashMap<String, Object> userInfo = snsLoginService.getUserInfo(accessToken);
        String kakaoEmail = (String) userInfo.get("email");

        System.out.println("Email: " + kakaoEmail);

        MemberDto member = memberMapper.findByEmail(kakaoEmail);

        if (memberMapper.getCheckKakaoEmail(kakaoEmail) == 0) {
            // 동일한 회원이 없을 경우, 회원가입 페이지
            //URLEncoder.encode(kakaoEmail, "UTF-8") 은 이메일에 특수문자 포함시 올바르게 인코딩 하기 위해 적음.
            return "redirect:/users/member/memberLogin/joinKakao?kakaoEmail=" + URLEncoder.encode(kakaoEmail, "UTF-8");
        } else {
            // 동일한 회원이 있을 경우, 로그인 처리
            HttpSession session = request.getSession();
            session.setAttribute("user", member);
            session.setMaxInactiveInterval(3600);

            return "redirect:/";
        }
    }


    @GetMapping("/users/member/memberLogin/joinKakao")
    public String getKakaoEmail(@RequestParam("kakaoEmail") String kakaoEmail, Model model) {

        model.addAttribute("kakaoEmail", kakaoEmail);
        return "users/member/memberLogin/joinKakao";
    }

}
