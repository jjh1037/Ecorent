package com.example.project.service.category;

import com.example.project.dto.user.UsersItemDto;
import com.example.project.mappers.user.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CategoryService {
    @Value("${fileDir}")
    String fileDir;

    @Autowired
    CategoryMapper categoryMapper;

    public void deleteItem(int itemNum){
        categoryMapper.deleteItem(itemNum);
    }

    public void setEditProduct(MultipartFile file, UsersItemDto itemDto) throws IOException {
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
        categoryMapper.setEditProduct(itemDto);
    }

    public List<UsersItemDto> getMainSearch(String searchWord){
        return categoryMapper.getMainSearch(searchWord);
    }


    public void setOverseaInfo(MultipartFile file, UsersItemDto itemDto, int memberId) throws IOException {
        String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
        File makeFolder = new File(fileDir + folderName);

        if (!makeFolder.exists()) {
            makeFolder.mkdir();
        }

        //첨부한 파일의 원래이름 가져오기
        String originName = file.getOriginalFilename();
        System.out.println(originName);

        //난수생성 하기
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);

        //파일이름의 온점을 기준으로 잘라내기
        String extention = originName.substring(originName.lastIndexOf("."));
        System.out.println(extention);

        //앞서 생성했던 랜덤문자열과 잘라낸 확장자 붙여주기 (이미지를 표시할 때)
        String savedFileName = uuid + extention;

        //저장될 이미지의 최종 경로 변수로 지정
        String savedPathFileName = fileDir + folderName + "/" + savedFileName;

        file.transferTo(new File(savedPathFileName));

        itemDto.setMemberId(memberId);
        itemDto.setFolderName(folderName);
        itemDto.setFileName(savedFileName);
        categoryMapper.setOverseaInfo(itemDto);
    }

    public void updateOversea(MultipartFile file,UsersItemDto itemDto) throws IOException {
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
            itemDto.setFolderName(itemDto.getFolderName());
            itemDto.setFileName(itemDto.getFileName());
        }
        categoryMapper.updateOversea(itemDto);
    }

}
