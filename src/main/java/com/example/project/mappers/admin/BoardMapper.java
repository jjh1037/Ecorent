package com.example.project.mappers.admin;

import com.example.project.dto.admin.BoardDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    @Select("SELECT i.*, m.memberNickName FROM item AS i INNER JOIN member AS m ON i.memberId = m.memberId ${searchQuery} ${searchQuery2} ORDER BY itemId DESC LIMIT #{startNum}, #{offset}")
    List<BoardDto> getBoardList(Map<String, Object> map);

    @Select("SELECT COUNT(*) FROM item AS i INNER JOIN member AS m ON i.memberId = m.memberId ${searchQuery} ${searchQuery2}")
    int getBoardListCount(Map<String, Object> map);

    @Select("SELECT COUNT(*) FROM item AS i INNER JOIN member AS m ON i.memberId = m.memberId ${searchQuery} ${searchQuery2}")
    int getSearchBoardListCount(String searchQuery, String searchQuery2);

    @Select("SELECT i.*, m.memberNickName FROM item AS i INNER JOIN member AS m ON i.memberId = m.memberId WHERE itemId = #{itemId}")
    BoardDto getBoardView(int itemId);

    @Delete("DELETE FROM item WHERE itemId = #{itemId}")
    void setBoardDelete(int itemId);

    @Update("UPDATE item SET title=#{title},name=#{name},rentalStartDate=#{rentalStartDate},rentalEndDate=#{rentalEndDate},rentalPrice=#{rentalPrice},address=#{address},carrier=#{carrier},content=#{content},folderName=#{folderName},fileName=#{fileName},car_type=#{carType},car_age=#{carAge},car_fuelType=#{carFuelType} WHERE itemId=#{itemId}")
    void setBoardUpdateCar(BoardDto boardDto);

    @Update("UPDATE item SET title=#{title},name=#{name},rentalStartDate=#{rentalStartDate},rentalEndDate=#{rentalEndDate},rentalPrice=#{rentalPrice},address=#{address},carrier=#{carrier},content=#{content},folderName=#{folderName},fileName=#{fileName},car_type=null,car_age=null,car_fuelType=null WHERE itemId=#{itemId}")
    void setBoardUpdate(BoardDto boardDto);

}
