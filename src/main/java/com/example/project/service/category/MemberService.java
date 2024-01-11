package com.example.project.service.category;

import com.example.project.dto.user.MemberDto;
import com.example.project.mappers.user.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberService {
    @Autowired
    MemberMapper memberMapper;

    public Map<String,Object> emailCheck(String memberEmail){
        Map<String, Object> map = new HashMap<>();
        int result = memberMapper.emailCheck(memberEmail);
        if(result>0){
            map.put("result","duplicated");
        }else{
            map.put("result","good");
        }
        return map;
    }

    public Map<String,Object> nickNameCheck(String nickName){
        Map<String, Object> map = new HashMap<>();
        int result = memberMapper.nickNameCheck(nickName);
        if(result>0){
            map.put("result","duplicated");
        }else{
            map.put("result","good");
        }
        return map;
    }

    public void setCancel(int memberId) {

        if(memberId > 0) {
            MemberDto memberDto = memberMapper.getMemberId(memberId);
            if(memberDto != null) {
                memberMapper.setCancel(memberId);
            }
        }
    }
}
