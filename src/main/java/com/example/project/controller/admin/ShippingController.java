package com.example.project.controller.admin;

import com.example.project.dto.admin.BoardDto;
import com.example.project.dto.admin.ShippingDto;
import com.example.project.mappers.admin.ShippingMapper;
import com.example.project.service.admin.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/shipping")
public class ShippingController {
    @Autowired
    ShippingMapper shippingMapper;

    @Autowired
    ShippingService shippingService;

    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/shippingList")
    public String getList(Model model,
                          @RequestParam(value="searchType", defaultValue = "") String searchType,
                          @RequestParam(value="words", defaultValue = "") String words,
                          @RequestParam(value = "page", defaultValue = "1") int page) {

        model.addAttribute("cnt", shippingService.getSearchCnt(searchType, words));
        model.addAttribute("list", shippingService.getSearch(page, searchType, words));
        model.addAttribute("page", shippingService.shippingPageCalc(page, searchType, words));

        return "admin/shipping/shippingList";
    }


    @GetMapping("/shippingWrite")
    public String getWrite() {
        return "admin/shipping/shippingWrite";
    }

    @PostMapping("/shippingWrite")
    public String setWrite(@ModelAttribute ShippingDto shippingDto,
                           @RequestParam("file") MultipartFile mf) throws IOException {
        if( !mf.isEmpty() ) {
            String folderName = "shipping";

            String orgName = mf.getOriginalFilename();
            String ext = orgName.substring(orgName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            String savedFileName = uuid + ext;
            String savedFilePathName = fileDir + folderName + "/" + savedFileName;

            shippingDto.setOrgName(orgName);
            shippingDto.setSavedFileName(savedFileName);
            shippingDto.setSavedFilePathName(savedFilePathName);
            shippingDto.setSavedFileSize(mf.getSize());
            shippingDto.setFolderName(folderName);
            shippingDto.setExt(ext);


            mf.transferTo( new File(savedFilePathName) );
        }

        shippingMapper.setWrite(shippingDto);
        return "redirect:/shipping/shippingList";
    }

    @GetMapping("/shippingView")
    public String getView(@RequestParam int id, Model model){

        model.addAttribute("view", shippingMapper.getView(id));
        return "admin/shipping/shippingView";
    }

    @GetMapping("/shippingDelete")
    public String setDelete(@RequestParam int id) {
        shippingService.setDelete(id);
        return "redirect:/shipping/shippingList";
    }

    @PostMapping("/shippingDeleteSelected")
    @ResponseBody
    public Map<String, Object> setDeleteSelected(@RequestBody List<ShippingDto> checkSelected) {
        return shippingService.setShippingDeleteSelected(checkSelected);
    }


    @GetMapping("/shippingUpdate")
    public String getUpdate(@RequestParam int id, Model model) {
        ShippingDto shippingDto = shippingMapper.getView(id);

        model.addAttribute("shipList", shippingDto);
        return "admin/shipping/shippingUpdate";
    }

    @PostMapping("/shippingUpdate")
    public String setUpdate(@ModelAttribute ShippingDto shippingDto,
                            @RequestParam("file") MultipartFile mf) throws IOException {

        if( !mf.isEmpty() ) {
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);
            if( !makeFolder.exists() ) {
                makeFolder.mkdir();
            }

            String orgName = mf.getOriginalFilename();
            String ext = orgName.substring(orgName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            String savedFileName = uuid + ext;
            String savedFilePathName = fileDir + folderName + "/" + savedFileName;

            shippingDto.setOrgName(orgName);
            shippingDto.setSavedFileName(savedFileName);
            shippingDto.setSavedFilePathName(savedFilePathName);
            shippingDto.setSavedFileSize(mf.getSize());
            shippingDto.setFolderName(folderName);
            shippingDto.setExt(ext);

            mf.transferTo( new File(savedFilePathName) );
        }else {
            // 값을 미변경시 원래값인 hidden에 있는 값을 저장
            // html hidden 값이 넘어온것
            // db      저장객체    html      hidden
            shippingDto.setOrgName(shippingDto.getOrgName());
            shippingDto.setSavedFileName(shippingDto.getSavedFileName());
            shippingDto.setSavedFilePathName(shippingDto.getSavedFilePathName());
            shippingDto.setSavedFileSize(shippingDto.getSavedFileSize());
            shippingDto.setFolderName(shippingDto.getFolderName());
            shippingDto.setExt(shippingDto.getExt());
        }

        shippingMapper.setUpdate(shippingDto);

        return "redirect:/shipping/shippingView?&id=" + shippingDto.getId();
    }
}
