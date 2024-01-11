package com.example.project.service.category;

import com.example.project.dto.user.RentalFormDto;
import com.example.project.dto.user.UsersItemDto;
import com.example.project.mappers.user.RentalFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RentalFormService {
    @Autowired
    RentalFormMapper rentalMapper;
    public RentalFormDto getRentalDetail(int formId){
        return rentalMapper.getRentalDetail(formId);
    }

    public void setCheckRequest(int memberId, int formId){
        rentalMapper.setCheckRequest(memberId, formId);
    }
    public void updateCheckRequest(int memberId, int formId){
        rentalMapper.updateCheckRequest(memberId, formId);
    }

    public Map<String,Object> getRentalList(int memberId){
        Map<String,Object> map = new HashMap<>();
        if(rentalMapper.getRentalList(memberId).isEmpty()){
            map.put("list","zero");
        }else{
            map.put("list",rentalMapper.getRentalList(memberId));
        }
        return map;
    }
    public UsersItemDto getOwnersItem(int formId){
        return rentalMapper.getOwnersItem(formId);
    }


    public Map<String,Object> getSentFormList(String renterId){
        Map<String,Object> map= new HashMap<>();
        if(rentalMapper.getSentFormList(renterId).isEmpty()){
            map.put("list","zero");
        }else{
            map.put("list",rentalMapper.getSentFormList(renterId));
        }
        return map;
    }
    public void confirmForm(int formId){
        rentalMapper.confirmForm(formId);
    }

    public void rejectForm(int formId){
        rentalMapper.rejectForm(formId);
    }
}
