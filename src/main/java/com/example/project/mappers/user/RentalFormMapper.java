package com.example.project.mappers.user;

import com.example.project.dto.user.GetRentalFormsDto;
import com.example.project.dto.user.RentalFormDto;
import com.example.project.dto.user.UsersItemDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RentalFormMapper {


    @Insert("INSERT INTO rentalForms VALUES(NULL, true, #{userName}, #{userPhone}, #{rentalStartDate},#{rentalEndDate}, #{postcode}, #{address}, #{carrier}, #{userContent}, #{itemId}, #{ownerId}, #{renterId}, 'waiting',CURRENT_TIMESTAMP)")
    @Options(useGeneratedKeys = true, keyProperty = "formId")
    public void setRentalForm(RentalFormDto rentalForm); //국내렌탈신청폼

    @Select("SELECT rf.*, i.title, i.folderName, i.fileName, cr.checked FROM rentalForms rf JOIN item i ON rf.itemId = i.itemId LEFT JOIN checkRequest cr ON rf.formId = cr.formId AND cr.memberId = #{memberId} WHERE rf.ownerId = #{memberId} AND (rf.formStatus = 'waiting' OR rf.formStatus = 'confirmed' OR rf.formStatus='rejected') ORDER BY rf.createdAt DESC;")
    public List<RentalFormDto> getRentalList(int memberId);

    @Select("SELECT rf.*, i.title, i.item_category, i.folderName, i.fileName From rentalForms rf JOIN item i ON rf.itemId = i.itemId WHERE rf.formId=#{formId}")
    public RentalFormDto getRentalDetail(int formId);

    @Insert("INSERT INTO checkRequest values(#{memberId},#{formId}, false) ON DUPLICATE KEY UPDATE formId = formId")
    public void setCheckRequest(@Param("memberId") int memberId, @Param("formId")int formId);

    @Update("UPDATE checkRequest SET checked=true WHERE memberId=#{memberId} AND formId=#{formId}")
    public void updateCheckRequest(@Param("memberId")int memberId, @Param("formId")int formId);

    @Select("SELECT count(*) FROM rentalForms rf LEFT JOIN checkRequest cr ON rf.formId = cr.formId AND cr.memberId = #{memberId} WHERE rf.ownerId = #{memberId} AND cr.checked=false")
    public int countCheckRequest(int memberId);

    @Select("SELECT item.* FROM item JOIN rentalForms ON item.itemId=rentalForms.itemId WHERE rentalForms.formId=#{formId}")
    public UsersItemDto getOwnersItem(int formId);

    @Select("SELECT rf.*, i.title, i.rentalStartDate as 'originStartDate', i.rentalEndDate as 'originEndDate', i.folderName, i.fileName, memberName as 'ownerName' FROM rentalForms rf JOIN item i ON rf.itemId = i.itemId JOIN member m on i.memberId=m.memberId WHERE rf.renterId = #{renterId} ORDER BY rf.createdAt DESC")
    public List<GetRentalFormsDto> getSentFormList(String renterId);

    @Select("UPDATE rentalForms set formStatus='confirmed' where formId=#{formId}")
    public void confirmForm(int formId);
    @Select("UPDATE rentalForms set formStatus='rejected' where formId=#{formId}")
    public void rejectForm(int formId);

    @Select("SELECT item_category FROM item i JOIN rentalForms r ON i.itemId=r.itemId WHERE r.formId=#{formId}")
    public String getCategoryName(int itemId); //어떤 카테고리의 렌탈신청폼인지 구별위해



    // 장바구니를 통한 렌탈 폼 저장
    @Insert("INSERT INTO rentalForms VALUES(NULL, true, #{userName}, #{userPhone}, #{rentalStartDate},#{rentalEndDate}, #{postcode}, #{address}, #{carrier}, #{userContent}, #{itemId}, #{ownerId}, #{renterId}, 'saving',CURRENT_TIMESTAMP)")
    @Options(useGeneratedKeys = true, keyProperty = "formId")
    public void setCartRentalFormSaving(RentalFormDto rentalForm); // 장바구니 렌탈폼 저장

    @Update("UPDATE cart SET formWritten = 1 WHERE itemId = #{itemId} AND memberId = #{renterId}")
    void setChangeCartFormWritten(RentalFormDto rentalDto);

}
