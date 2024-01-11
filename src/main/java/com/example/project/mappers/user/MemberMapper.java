package com.example.project.mappers.user;

import com.example.project.dto.user.MemberDto;
import com.example.project.dto.user.MessageDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    //로그인
    @Select("SELECT * FROM member WHERE memberEmail=#{memberEmail} AND memberPasswd=#{memberPasswd}")
    public MemberDto loginCheck(MemberDto mdto);

    //메시지
    @Insert("INSERT INTO message VALUES(NULL,#{itemId},#{renterId},#{ownerId},#{sentBy},#{msgContent}, false, CURRENT_TIMESTAMP)")
    public void setMessage(MessageDto msgDto);

    @Select("SELECT \n" +
            "    i.itemId, \n" +
            "    i.title, \n" +
            "    i.fileName, \n" +
            "    i.folderName, \n" +
            "    m.memberId AS otherMemberId, \n" +
            "    m.memberName AS otherMemberName, \n" +
            "    msg.* \n" +
            "    FROM \n" +
            "    message msg\n" +
            "    JOIN \n" +
            "    (SELECT \n" +
            "         itemId, \n" +
            "         renterId,\n" +
            "         ownerId,\n" +
            "         MAX(msgTime) AS LatestMsgTime\n" +
            "     FROM \n" +
            "         message\n" +
            "     WHERE \n" +
            "         renterId = #{memberId} OR ownerId = #{memberId}\n" +
            "     GROUP BY \n" +
            "         itemId, renterId, ownerId) AS LatestMessages \n" +
            "ON \n" +
            "    msg.itemId = LatestMessages.itemId AND \n" +
            "    msg.renterId = LatestMessages.renterId AND\n" +
            "    msg.ownerId = LatestMessages.ownerId AND\n" +
            "    msg.msgTime = LatestMessages.LatestMsgTime\n" +
            "JOIN \n" +
            "    item i ON msg.itemId = i.itemId\n" +
            "JOIN \n" +
            "    member m ON (msg.renterId = m.memberId AND msg.renterId != #{memberId}) OR (msg.ownerId = m.memberId AND msg.ownerId != #{memberId}) ORDER BY \n" +
            "    msg.msgTime DESC;")
    public List<MessageDto> getMessageList(int memberId);

    @Delete("DELETE FROM message WHERE (renterId=#{memberId} And ownerId=#{otherMemberId} AND itemId=#{itemId}) OR(renterId=#{otherMemberId} And ownerId=#{memberId} AND itemId=#{itemId})")
    public void setDeleteMsgs(@Param("memberId") int memberId,@Param("otherMemberId") int otherMemberId,@Param("itemId") int itemId);

    @Select("SELECT * FROM message WHERE itemId=#{itemId} AND renterId=#{renterId} AND ownerId=#{ownerId}")
    public List<MessageDto> getMessages(MessageDto mdto);

    @Insert("INSERT INTO message VALUES(NULL,#{itemId},#{renterId},#{ownerId},#{sentBy},#{msgContent},false, CURRENT_TIMESTAMP)")
    public void setMessageContent(MessageDto mdto);

    @Select("SELECT message.*, item.title, member.memberName as 'ownerName', item.folderName, item.fileName from message join item on message.itemId=item.itemId join member on item.memberId= member.memberId where message.itemId=#{itemId} and message.renterId=#{renterId} and message.ownerId=#{ownerId} limit 1")
    public MessageDto getmsgView(@Param("itemId") int itemId,@Param("renterId") int renterId,@Param("ownerId") int ownerId);
    @Update("UPDATE message SET checked=true WHERE msgId=#{msgId}")
    public void setCheckIsMsgRead(int msgId);

    @Select("SELECT COUNT(*) FROM message m JOIN (SELECT renterId, ownerId, MAX(msgTime) as msgTime FROM message GROUP BY renterId, ownerId) as sub ON m.renterId=sub.renterId AND m.ownerId=sub.ownerId AND m.msgTime=sub.msgTime WHERE (m.checked=false AND sentBy!=#{memberId}) and (m.renterId=#{memberId} Or m.ownerId=#{memberId});")
    public int getCountUnreadMsg(int memberId);

    /*------------------------------가영 회원가입 유효성---------------------------------------*/

    @Select("SELECT COUNT(*) FROM member WHERE memberEmail = #{memberEmail}")
    public int emailCheck(String memberEmail); //이메일 유효성체크

    @Insert("INSERT INTO member VALUES (NULL, #{memberEmail}, #{memberPasswd}, #{memberName}, #{memberPhone}, #{memberNickName}, #{memberBirth}, #{memberPostcode}, #{memberAddress}, #{memberInterest}, now(), #{memberPoint}, '회원')")
    public void insertMember(MemberDto memberDto); // 회원가입 저장

    @Select("SELECT COUNT(*) FROM member WHERE memberNickName = #{nickName}")
    public int nickNameCheck(String nickName); //가입시 닉네임 체크

    @Select("SELECT memberEmail, memberRegDate FROM member WHERE memberName = #{memberName} AND memberPhone = #{memberPhone}")
    public MemberDto resultEmail(@Param("memberName") String memberName,@Param("memberPhone") String memberPhone);

    @Select("SELECT memberPasswd FROM member WHERE memberNickName = #{memberNickName} AND memberEmail = #{memberEmail}")
    public MemberDto resultPasswd(@Param("memberNickName") String memberNickName,@Param("memberEmail") String memberEmail);


    /*------------------------------가영 관리자페이지 회원관리---------------------------------------*/

    @Select("SELECT * FROM member WHERE memberId = #{memberId}")
    MemberDto getMemberId(int memberId);

    /*-----------------------------가영 유저페이지 수정하기 ------------------------------------*/
    @Update("UPDATE member SET memberName = #{memberName}, memberEmail = #{memberEmail}, memberNickName = #{memberNickName}, memberPhone = #{memberPhone}, memberPasswd = #{memberPasswd}," +
            " memberBirth = #{memberBirth}, memberAddress = #{memberAddress}, memberPostcode = #{memberPostcode}, memberInterest = #{memberInterest}" +
            " WHERE memberId = #{memberId}")
    void setMemberInfoUpdate(MemberDto memberDto);

    // 탈퇴하기
    @Delete("DELETE FROM member WHERE memberId = #{memberId}")
    void setCancel(int memberId);


    /*-----------------------------가영 카카오 회원가입 유효성 ------------------------------------*/

    @Select("SELECT COUNT(*) FROM member WHERE memberEmail  = #{kakaoEmail}")
    int getCheckKakaoEmail(String kakaoEmail);

    @Select("SELECT * FROM member WHERE memberEmail = #{memberEmail}")
    MemberDto findByEmail(String memberEmail);

}
