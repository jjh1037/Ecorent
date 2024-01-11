package com.example.project.mappers.admin;

import com.example.project.dto.admin.ReportDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberReportMapper {
    @Select("SELECT report.*, renter.memberNickName AS renterNickName, owner.memberNickName AS ownerNickName, item.title AS title FROM report AS report " +
            "INNER JOIN member AS renter ON report.renterId = renter.memberId " +
            "INNER JOIN item AS item ON report.itemId = item.itemId " +
            "INNER JOIN member AS owner ON report.ownerId = owner.memberId " +
            "${searchQuery} ${searchQuery2} ORDER BY reportId DESC LIMIT #{startNum}, #{offset}")
    List<ReportDto> getReportList(Map<String, Object> map);

    @Select("SELECT report.*, renter.memberNickName AS renterNickName, owner.memberNickName AS ownerNickName, item.title AS title FROM report AS report " +
            "INNER JOIN member AS renter ON report.renterId = renter.memberId " +
            "INNER JOIN item AS item ON report.itemId = item.itemId " +
            "INNER JOIN member AS owner ON report.ownerId = owner.memberId WHERE report.reportId = #{reportId}")
    ReportDto getReportView(int reportId);

    @Select("SELECT COUNT(*) FROM report AS report " +
            "INNER JOIN member AS renter ON report.renterId = renter.memberId " +
            "INNER JOIN item AS item ON report.itemId = item.itemId " +
            "INNER JOIN member AS owner ON report.ownerId = owner.memberId " +
            "${searchQuery} ${searchQuery2}")
    int getReportListCount(Map<String, Object> map);

    @Select("SELECT COUNT(*) FROM report AS report " +
            "INNER JOIN member AS renter ON report.renterId = renter.memberId " +
            "INNER JOIN item AS item ON report.itemId = item.itemId " +
            "INNER JOIN member AS owner ON report.ownerId = owner.memberId " +
            "${searchQuery} ${searchQuery2}")
    int getSearchReportListCount(String searchQuery, String searchQuery2);

    @Select("SELECT COUNT(*) FROM report")
    int getReportTotalCount();

    @Insert("INSERT INTO report VALUES(NULL,#{itemId}, #{renterId}, #{ownerId}, #{renterNickName}, #{ownerNickName} ,#{reportType}, #{reportContent}, CURRENT_TIMESTAMP)")
    void setReport(ReportDto reportDto);

    @Delete("DELETE FROM report WHERE reportId = #{reportId}")
    void setDeleteSelected(int reportId);

    @Delete("DELETE FROM item WHERE itemId = #{itemId}")
    void setDeleteItem(int itemId);

    @Delete("DELETE FROM member WHERE memberId = #{ownerId}")
    void setDeleteMember(int ownerId);

    @Select("Select COUNT(*) FROM item WHERE itemId = #{itemId} AND item_category = '자동차'")
    int getCheckIsCar(int itemId);
}
