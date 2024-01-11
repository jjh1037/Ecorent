package com.example.project.mappers.user;

import com.example.project.dto.user.CarTypeCountDto;
import com.example.project.dto.user.UsersItemDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //자동차 매퍼
    @Insert("INSERT INTO item VALUES(NULL, TRUE, #{title}, #{name}, #{rentalStartDate}, #{rentalEndDate}, #{rentalPrice}, #{address}, #{carrier}, #{content}, NOW(), #{folderName}, #{fileName}, #{carType}, #{carAge}, #{carFuelType}, '자동차', #{memberId})")
    public void setCarInfo(UsersItemDto carDto);
    @Select("SELECT * FROM item WHERE item_category='자동차' ORDER BY itemId DESC")
    public List<UsersItemDto> getCarList();
    @Select("SELECT COUNT(*) FROM item WHERE item_category='자동차'")
    public int getTotalCar(); //자동차리스트 total 개수
    @Select("SELECT car_type, COUNT(*) as carTypeCount FROM item WHERE item_category='자동차' GROUP BY car_type;")
    public List<CarTypeCountDto> getCountCarType();

    @Select("SELECT * FROM item WHERE car_type=#{carType} ORDER BY itemId DESC")
    public List<UsersItemDto> getcarTypeList(String carType); //자동차 타입별 리스트 가져오기

    @Update("UPDATE item SET title=#{title},name=#{name},rentalStartDate=#{rentalStartDate},rentalEndDate=#{rentalEndDate},rentalPrice=#{rentalPrice},address=#{address},carrier=#{carrier},content=#{content},folderName=#{folderName},fileName=#{fileName},car_type=#{carType},car_age=#{carAge},car_fuelType=#{carFuelType} WHERE itemId=#{itemId}")
    public void updateCar(UsersItemDto itemDto);


    //해외렌탈매퍼
    @Insert("INSERT INTO item VALUES(NULL, false, #{title}, #{name}, #{rentalStartDate}, #{rentalEndDate}, #{rentalPrice}, #{address}, #{carrier}, #{content}, NOW(), #{folderName}, #{fileName}, #{carType}, #{carAge}, #{carFuelType}, '해외', #{memberId})")
    public void setOverseaInfo(UsersItemDto overseaDto);

    @Update("UPDATE item SET title=#{title},name=#{name},rentalStartDate=#{rentalStartDate},rentalEndDate=#{rentalEndDate},rentalPrice=#{rentalPrice},address=#{address}, carrier=#{carrier}, content=#{content},folderName=#{folderName},fileName=#{fileName} WHERE itemId=#{itemId}")
    public void updateOversea(UsersItemDto itemDto);


    //ALL 카테고리 공통
    @Select("SELECT * FROM item WHERE item_category=#{category} ORDER BY itemId DESC")
    public List<UsersItemDto> getListProductView(String category);
    @Insert("INSERT INTO item VALUES(NULL, true, #{title}, #{name}, #{rentalStartDate}, #{rentalEndDate}, #{rentalPrice}, #{address}, #{carrier}, #{content}, NOW(), #{folderName}, #{fileName}, #{carType}, #{carAge}, #{carFuelType}, #{itemCategory}, #{memberId})")
    public void setProductInfo(UsersItemDto OfficeDto);
    @Select("SELECT item.*, member.memberName, member.memberNickName FROM item JOIN member ON item.memberId=member.memberId WHERE item.itemId = #{itemId}")
    public UsersItemDto getviewItem(int itemId);
    @Select("SELECT * FROM item ORDER BY itemId DESC limit 8")
    public List<UsersItemDto> getNewItemList();
    @Delete("DELETE FROM item WHERE itemId=#{itemId}")
    public void deleteItem(int itemId);
    @Select("SELECT * FROM item WHERE title LIKE CONCAT('%',#{searchWord},'%')")
    public List<UsersItemDto> getMainSearch(String searchWord);
    @Select("SELECT item_category FROM item WHERE itemId=#{itemNum}")
    public String getCategoryName(int itemNum);
    @Update("UPDATE item SET title=#{title},name=#{name},rentalStartDate=#{rentalStartDate},rentalEndDate=#{rentalEndDate},rentalPrice=#{rentalPrice},address=#{address}, carrier=#{carrier}, content=#{content},folderName=#{folderName},fileName=#{fileName} WHERE itemId=#{itemId}")
    public void setEditProduct(UsersItemDto itemDto);


}
