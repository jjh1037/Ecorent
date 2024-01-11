package com.example.project.mappers.admin;

import com.example.project.dto.admin.BoardDto;
import com.example.project.dto.admin.CommunityDto;
import com.example.project.dto.admin.ReportDto;
import com.example.project.dto.admin.ShippingDto;
import com.example.project.dto.user.MemberDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM member ${searchQuery} ORDER BY memberId DESC LIMIT #{startNum}, #{offset}")
    List<MemberDto> getList(Map<String, Object> map);

    @Select("SELECT COUNT(*) FROM member ${searchQuery}")
    int getListCount(Map<String, Object> map);

    @Select("SELECT COUNT(*) FROM member ${searchQuery}")
    int getSearchListCount(String searchQuery);

    @Select("SELECT COUNT(*) FROM member")
    int totalCount();

    @Update("UPDATE member SET memberEmail = #{memberEmail}, memberName = #{memberName}, memberNickName = #{memberNickName}, " +
            "memberPhone = #{memberPhone}, memberBirth = #{memberBirth}, memberAddress = #{memberAddress},memberPoint = #{memberPoint}," +
            " memberPostcode = #{memberPostcode}, memberInterest = #{memberInterest} WHERE memberId = #{memberId}")
    void setMemberUpdate(MemberDto membersDto);

    @Delete("DELETE FROM member WHERE memberId = #{memberId}")
    void setDelete(int memberId);

    @Select("SELECT i.*, m.memberNickName FROM item AS i INNER JOIN member AS m ON i.memberId = m.memberId ORDER BY itemId DESC LIMIT 0, 5")
    List<BoardDto> getAdminIndexBoardList(Map<String, Object> map);

    @Select("SELECT * FROM post ORDER BY post_num DESC LIMIT 0, 5")
    List<CommunityDto> getAdminIndexCommunityList(Map<String, Object> map);

    @Select("SELECT * FROM member ORDER BY memberId DESC LIMIT 0, 5")
    List<MemberDto> getAdminIndexMemberSearch(Map<String, Object> map);

    @Select("SELECT report.*, renter.memberNickName AS renterNickName, owner.memberNickName AS ownerNickName, item.title AS title FROM report AS report " +
            "INNER JOIN member AS renter ON report.renterId = renter.memberId " +
            "INNER JOIN item AS item ON report.itemId = item.itemId " +
            "INNER JOIN member AS owner ON report.ownerId = owner.memberId " +
            "ORDER BY reportId DESC LIMIT 0, 5")
    List<ReportDto> getAdminIndexReportSearch(Map<String, Object> map);

    @Select("SELECT * FROM shipping ORDER BY id DESC LIMIT 0, 5")
    List<ShippingDto> getAdminIndexShippingSearch(Map<String, Object> map);

  }
