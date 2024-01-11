package com.example.project.mappers.user;

import com.example.project.dto.user.CommentDto;
import com.example.project.dto.user.LoveDto;
import com.example.project.dto.user.UsersPostDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

    @Insert("INSERT INTO post VALUES(NULL, #{postType}, #{postTitle}, #{postWriter}, #{postContent}, NOW())")
    public void setPost(UsersPostDto postDto);

    @Select("SELECT POST.*, IFNULL(totalLove.loveCount,0) as countLove from POST LEFT JOIN (SELECT post_num, COUNT(*) AS loveCount FROM love WHERE state=1 GROUP BY post_num) AS totalLove ON POST.post_num=totalLove.post_num where POST.post_num=#{id};")
    public UsersPostDto viewPost(int id);

    @Delete("DELETE FROM post WHERE post_num=#{postNum}")
    public void removePost(int postNum);

    @Update("UPDATE post SET post_type=#{postType}, post_title=#{postTitle}, post_content=#{postContent} WHERE post_num=#{postNum}")
    public void editPost(UsersPostDto udto);

    @Select("SELECT post.*, IFNULL(totalLove.count, 0) AS countLove FROM post LEFT JOIN (SELECT post_num, COUNT(*) AS count FROM love WHERE state=1 GROUP BY post_num) AS totalLove ON post.post_num = totalLove.post_num ${searchQuery} ORDER BY post_num DESC LIMIT #{startNum}, #{offset}")
    public List<UsersPostDto> getPostList(Map<String, Object> map);

    @Select("SELECT COUNT(*) FROM post ${searchQuery}")
    public int getCountSearch(String searchQuery);

    @Select("SELECT COUNT(*) FROM love WHERE state=1")
    public int getCountLove();

    @Update("UPDATE member SET memberPoint=memberPoint+50 WHERE memberId=#{memberId}")
    public void updateMemberPoint(int memberId);


    // 좋아요 기능

    @Insert("INSERT INTO love VALUES(NULL, #{memberId}, #{postNum}, 1)")
    public void setLove(LoveDto loveDto);
    @Select("SELECT COUNT(*) FROM love WHERE memberId=#{memberId} AND post_num=#{postNum}")
    public int checkCountLove (LoveDto loveDto);
    @Select("SELECT * FROM love WHERE memberId=#{memberId} AND post_num=#{postNum}")
    public LoveDto checkLove (LoveDto loveDto);
    @Update("UPDATE love SET state=1 WHERE memberId=#{memberId} AND post_num=#{postNum}")
    public void updateTrueLove(LoveDto loveDto);
    @Update("UPDATE love SET state=0 WHERE memberId=#{memberId} AND post_num=#{postNum}")
    public void updateFalseLove(LoveDto loveDto);



    //코멘트 기능

    @Insert("INSERT INTO comment VALUES(NULL, #{postNum}, #{comWriter}, #{comContent}, CURRENT_TIMESTAMP)")
    public void setComment(CommentDto cdto);

    @Select("SELECT c.*, m.memberNickName FROM comment c JOIN member m ON c.comWriter=m.memberId WHERE c.postNum=#{postNum} ORDER BY comId DESC")
    public List<CommentDto> getComments(int postNum);

}
