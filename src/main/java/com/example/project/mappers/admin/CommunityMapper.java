package com.example.project.mappers.admin;

import com.example.project.dto.admin.CommunityDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityMapper {

    @Select("SELECT * FROM post ${searchQuery} ${searchQuery2} ORDER BY post_num DESC LIMIT #{startNum}, #{offset}")
    List<CommunityDto> getCommunityList(Map<String, Object> map);

    @Select("SELECT COUNT(*) FROM post ${searchQuery} ${searchQuery2}")
    int getCommunityCount(Map<String, Object> map);

    @Select("SELECT COUNT(*) FROM post ${searchQuery} ${searchQuery2}")
    int getSearchCommunityListCount(String searchQuery, String searchQuery2);

    @Delete("DELETE FROM post WHERE post_num = #{postNum}")
    void setCommunityDelete(int postNum);

    @Select("SELECT * FROM post WHERE post_num = ${postNum}")
    CommunityDto getCommunityView(int postNum);

    @Update("UPDATE post SET post_type=#{postType}, post_title=#{postTitle}, post_writer=#{postWriter}, post_content=#{postContent} WHERE post_num = #{postNum}")
    void setCommunityUpdate(CommunityDto communityDto);
}
