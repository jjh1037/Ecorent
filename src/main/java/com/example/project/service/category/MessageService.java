package com.example.project.service.category;

import com.example.project.dto.user.MessageDto;
import com.example.project.mappers.user.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {
    @Autowired
    MemberMapper memberMapper;

    public Map<String,Object> getMessageList(int memberId){ //메시지 메뉴 리스트 출력
        Map<String,Object> map = new HashMap<>();
        if(memberMapper.getMessageList(memberId).isEmpty()){
            map.put("list","zero");
        }else{
            map.put("list",memberMapper.getMessageList(memberId));
        }
        return map;
    }
    public Map<String,Object> setDeleteMsgs(int memberId, List<MessageDto> selectedMsgs){
        Map<String,Object> map = new HashMap<>();
        for(MessageDto msg : selectedMsgs){
            memberMapper.setDeleteMsgs(memberId, msg.getOtherMemberId(),msg.getItemId());
            System.out.println(memberId + msg.getOtherMemberId() +msg.getItemId());
        }
        map.put("result","success");
        return map;
    }

    public Map<String,Object> getMessages(MessageDto mdto){
        Map<String,Object> map = new HashMap<>();
        map.put("result",memberMapper.getMessages(mdto));
        return map;
    }

}
