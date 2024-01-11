package com.example.project.controller.users.categoty;

import com.example.project.dto.user.MemberDto;
import com.example.project.dto.user.UsersItemDto;
import com.example.project.mappers.user.CategoryMapper;
import com.example.project.service.category.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/users/category/car")
public class CarController {

    @Autowired
    CarService carService;
    @Autowired
    CategoryMapper categoryMapper;

    @Value("${fileDir}")
    String fileDir;

    @GetMapping("")
    public String getListCar(){
        return "users/category/car/listCar";
    }

    @PostMapping("")
    @ResponseBody
    public Map<String, Object> getItemList(@RequestParam(required = false) String carType){
        return carService.getCarList(carType);
    }

    @GetMapping("/viewCar")
    public String getViewCar(@RequestParam int id,Model model){
        model.addAttribute("card",categoryMapper.getviewItem(id));
        return "users/category/car/viewCar";
    }


    @GetMapping("/deleteCar")
    @ResponseBody
    public Map<String,Object> getDeleteCar(@RequestParam Integer itemNum){
        System.out.println(itemNum);
        if(itemNum!=null){
            carService.deleteItem(itemNum);
            return Map.of("msg","success");
        }else{
            return Map.of("msg","success");
        }
    }

    @GetMapping("/editCar")
    public String getUpdateCar(@RequestParam Integer id, Model model,HttpSession session){

        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else{
            model.addAttribute("item",carService.getEditView(id));
            return "users/category/car/editCar";
        }
    }

    @PostMapping("/editCar")
    public String updateCar(@RequestParam("file") MultipartFile file, @ModelAttribute UsersItemDto itemDto, HttpSession session) throws IOException {
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else {
            MemberDto user = (MemberDto) session.getAttribute("user");
            carService.updateCar(file, itemDto);
            return "redirect:/users/category/car";
        }
    }

    @GetMapping("/postCar")
    public String getPostCar(HttpSession session){

        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }
        return "users/category/car/postCar";
    }

    @PostMapping("/postCar")
    public String setRentalCar(@RequestParam("file") MultipartFile file, @ModelAttribute UsersItemDto itemDto, HttpSession session, Model model) throws IOException {
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else {
            MemberDto user = (MemberDto) session.getAttribute("user");
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);

            if (!makeFolder.exists()) {
                makeFolder.mkdir();
            }

            //첨부한 파일의 원래이름 가져오기
            String originName = file.getOriginalFilename();

            //난수생성 하기
            String uuid = UUID.randomUUID().toString();

            //파일이름의 온점을 기준으로 잘라내기
            String extention = originName.substring(originName.lastIndexOf("."));

            //앞서 생성했던 랜덤문자열과 잘라낸 확장자 붙여주기 (이미지를 표시할 때)
            String savedFileName = uuid + extention;

            //저장될 이미지의 최종 경로 변수로 지정
            String savedPathFileName = fileDir + folderName + "/" + savedFileName;

            file.transferTo(new File(savedPathFileName));

            itemDto.setMemberId(user.getMemberId());
            itemDto.setFolderName(folderName);
            itemDto.setFileName(savedFileName);
            categoryMapper.setCarInfo(itemDto);

            return "redirect:/users/category/car";
        }
    }


}
