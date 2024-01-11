package com.example.project.service.member;

import com.example.project.dto.user.CartDto;
import com.example.project.mappers.user.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

//    public Map<String,Object> getCartList(int memberId) {
//        Map<String, Object> map = new HashMap<>();
//
//        map.put("list", cartMapper.getCart(memberId));
//        map.put("cnt", cartMapper.getCartCount(memberId));
//
//    return map;
//    }

    public int getCartCount(int memberId) {
        return cartMapper.getCartCount(memberId);
    }

    public Map<String, Object> setSelDelete(List<CartDto> checkSelected) {
        Map<String, Object> map = new HashMap<>();
        for(CartDto cartDto : checkSelected) {
            cartMapper.setDelete(cartDto.getCartId());
        }
        map.put("msg", "success");
        return map;
    }

    public Map<String, Object> setChangeToWaitingAll(List<CartDto> sendFormAll) {
        Map<String, Object> map = new HashMap<>();
        int isFormWritten = 1;

        for(CartDto cartDto : sendFormAll) { // 미작성 폼 확인 
            isFormWritten *= cartMapper.getSavingCheck(cartDto.getItemId(), cartDto.getMemberId());
        }

        if(isFormWritten == 0) { // 미작성 폼이 있을시
            map.put("msg", "failure");
        }else { // 폼이 다 작성됐을시
            for(CartDto cartDto : sendFormAll) {
                cartMapper.setChangeFormStatusToWaiting(cartDto.getItemId(), cartDto.getMemberId());
                cartMapper.setDelete(cartDto.getCartId());
            }
            map.put("msg", "success");
        }

        return map;
    }


}
