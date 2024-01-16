package com.example.project.controller.users.categoty;

import com.example.project.dto.user.MemberDto;
import com.example.project.dto.user.UsersItemDto;
import com.example.project.mappers.user.CategoryMapper;
import com.example.project.service.category.CategoryService;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class CommonController {
    @Value("${fileDir}")
    String fileDir;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/users/category/{category}")
    public String getProductList(@PathVariable String category, Model model){

        if(engToKorCategory(category)=="무대"){
            model.addAttribute("menu","무대∙방송");
        }else if(engToKorCategory(category)=="오피스"){
            model.addAttribute("menu","IT∙오피스");
        }else{
            model.addAttribute("menu",engToKorCategory(category));
        }
        return "users/category/listProduct";
    }

    @PostMapping("/users/category/{category}")
    @ResponseBody
    public Map<String, Object> getItemList(@PathVariable String category){
        Map<String, Object> map = new HashMap<>();

        if(categoryMapper.getListProductView(engToKorCategory(category)).isEmpty()){
            map.put("list","zero");
        }else{
            map.put("list",categoryMapper.getListProductView(engToKorCategory(category)));
        }

        return map;
    }

    @GetMapping("/users/category/viewProduct")
    public String getProductView(@RequestParam int id, Model model){
        model.addAttribute("menu", korToEngCategory(categoryMapper.getCategoryName(id)));
        model.addAttribute("card",categoryMapper.getviewItem(id));
        return "users/category/viewProduct";
    }

    @GetMapping("/users/category/postProduct")
    public String getPostProduct(@RequestParam String category, Model model){
        model.addAttribute("category",category);
        return "users/category/postProduct";
    }

    @PostMapping("/users/category/postProduct")
    public String setPostProduct(@RequestParam("category") String category, @RequestParam("file") MultipartFile file, @ModelAttribute UsersItemDto itemDto, HttpSession session) throws IOException {
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

            itemDto.setItemCategory(engToKorCategory(category));

            if(category.equals("oversea")){
                categoryMapper.setOverseaInfo(itemDto);
            }else{
                categoryMapper.setProductInfo(itemDto);
            }
            return "redirect:/users/category/"+category;
        }
    }

    @GetMapping("/users/category/editProduct")
    public String getEditProduct(@RequestParam int id, Model model,HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else{
            model.addAttribute("item",categoryMapper.getviewItem(id));
            return "users/category/editProduct";
        }
    }
    @PostMapping("/users/category/editProduct")
    public String setEditOffice(@RequestParam("file") MultipartFile file, @ModelAttribute UsersItemDto itemDto, HttpSession session) throws IOException {
        if(session.getAttribute("user")==null){
            return "redirect:/users/member/memberLogin/login";
        }else {
            MemberDto user = (MemberDto) session.getAttribute("user");
            categoryService.setEditProduct(file, itemDto);
            String category = korToEngCategory(categoryMapper.getCategoryName(itemDto.getItemId()));

            return "redirect:/users/category/"+category;
        }
    }

    @GetMapping("/users/category/deleteProduct")
    @ResponseBody
    public Map<String,Object> setDeleteProduct(@RequestParam int itemNum){
        Map<String,Object> map = new HashMap<>();
        map.put("category",korToEngCategory(categoryMapper.getCategoryName(itemNum)));
        map.put("result","success");
        categoryMapper.deleteItem(itemNum);
        return map;
    }

    public String engToKorCategory(String word){
        String changedWord="";
        switch (word){
            case "stage":
                changedWord= "무대";
                break;
            case "restaurant":
                changedWord= "상업용가전";
                break;
            case "office":
                changedWord= "오피스";
            break;
            case "construction":
                changedWord= "현장장비";
            break;
            case "instrument":
                changedWord= "악기";
            break;
            case "etc":
                changedWord= "기타장비";
            break;
            case "oversea":
                changedWord= "해외";
            break;
        }
        return changedWord;
    }
    public String korToEngCategory(String word){
        String changedWord="";
        switch (word){
            case "무대":
                changedWord= "stage";
                break;
            case "상업용가전":
                changedWord= "restaurant";
                break;
            case "오피스":
                changedWord= "office";
                break;
            case "현장장비":
                changedWord= "construction";
                break;
            case "악기":
                changedWord= "instrument";
                break;
            case "기타장비":
                changedWord= "etc";
                break;
            case "해외":
                changedWord= "oversea";
                break;
        }
        return changedWord;
    }
}
