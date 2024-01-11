package com.example.project.mappers.user;

import com.example.project.dto.user.CartDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {
    @Insert("INSERT INTO cart VALUES(NULL, #{itemId}, #{memberId}, 0, CURRENT_TIMESTAMP)")
    public void setCart(CartDto cartDto);

    @Select("SELECT c.cartId, c.memberId, c.itemId, c.formWritten, i.name, i.folderName, i.fileName, i.item_category, i.title, i.rentalPrice, i.rentalStartDate, i.rentalEndDate FROM cart AS c INNER JOIN item AS i ON c.itemId = i.itemId  WHERE c.memberId = #{memberId} ORDER BY cartId ASC")
    List<CartDto> getCart(int memberId);

    @Select("SELECT COUNT(*) FROM cart WHERE memberId = #{memberId}")
    int getCartCount(int memberId);

    @Delete("DELETE FROM cart WHERE cartId = #{cartId}")
    void setDelete(int cartId);

    @Delete("DELETE FROM cart WHERE itemId = #{itemId} AND memberId = #{memberId}")
    void setDeleteItemAndMemberId(CartDto cartDto);

    @Delete("DELETE FROM cart WHERE memberId = #{memberId}")
    void setDeleteAll(int memberId);

    @Select("SELECT COUNT(*) FROM cart WHERE memberId = #{memberId} AND itemId = #{itemId}")
    int getCartDuplicateCheck(CartDto cartDto);

    @Select("SELECT COUNT(*) FROM rentalforms where itemId = #{itemId} AND renterId = #{memberId} AND formStatus = 'saving'")
    int getSavingCheck(int itemId, int memberId);

    @Update("UPDATE rentalforms SET formStatus = 'waiting' WHERE itemId = #{itemId} AND renterId = #{memberId} AND formStatus = 'saving'")
    void setChangeFormStatusToWaiting(int itemId, int memberId);




}
