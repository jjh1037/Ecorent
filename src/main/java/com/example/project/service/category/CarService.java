package com.example.project.service.category;

import com.example.project.dto.user.UsersItemDto;
import com.example.project.mappers.user.CategoryMapper;
import com.example.project.mappers.user.RentalFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CarService {
    @Value("${fileDir}")
    String fileDir;
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    RentalFormMapper rentalMapper;

    public Map<String, Object> getCarList(String carType){
        Map<String, Object> map = new HashMap<>();

        if(categoryMapper.getCarList().isEmpty()){
            map.put("list","zero");
        }else{
            if(carType==null || carType.equals("all")){
                map.put("type",categoryMapper.getCarList());
            }else{
                map.put("type",categoryMapper.getcarTypeList(carType));
            }
            map.put("list",categoryMapper.getCarList());
        }
        map.put("typeTotal",categoryMapper.getCountCarType());
        map.put("total",categoryMapper.getTotalCar());
        return map;
    }




    public void deleteItem(int itemId){
        categoryMapper.deleteItem(itemId);
    }

    public UsersItemDto getEditView(int id){
        return categoryMapper.getviewItem(id);
    }


    //자동차 카테고리 게시물 수정
    public void updateCar(MultipartFile file, @ModelAttribute UsersItemDto itemDto) throws IOException {
        if(!file.isEmpty()){
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);

            if (!makeFolder.exists()) {
                makeFolder.mkdir();
            }

            //첨부한 파일의 원래이름 가져오기
            String originName = file.getOriginalFilename();
            //파일이름의 온점을 기준으로 잘라내기
            String extention = originName.substring(originName.lastIndexOf("."));
            //난수생성 하기
            String uuid = UUID.randomUUID().toString();
            //앞서 생성했던 랜덤문자열과 잘라낸 확장자 붙여주기 (이미지를 표시할 때)
            String savedFileName = uuid + extention;
            //저장될 이미지의 최종 경로 변수로 지정
            String savedPathFileName = fileDir + folderName + "/" + savedFileName;

            itemDto.setFolderName(folderName);
            itemDto.setFileName(savedFileName);
            file.transferTo(new File(savedPathFileName));
        }else{
            //update.html 에서 hidden input. 에서 넘어온 값
            //첨부파일이 안바뀌면 기존 정보 그대로 업데이트
            itemDto.setFolderName(itemDto.getFolderName());
            itemDto.setFileName(itemDto.getFileName());
        }
        categoryMapper.updateCar(itemDto);
    }


}
