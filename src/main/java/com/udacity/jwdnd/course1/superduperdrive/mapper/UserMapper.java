package com.udacity.jwdnd.course1.superduperdrive.mapper;

import com.udacity.jwdnd.course1.superduperdrive.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User findUser(String username);

    @Select("SELECT * FROM USERS WHERE userid = #{userId}")
    User findUserById(Integer userId);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(User user);

    @Update("UPDATE USERS SET username=#{username}, firstname=#{firstName}, lastname=#{lastName} WHERE userid=#{userId}")
    void updateUser(User user);

    @Delete("DELETE FROM USERS WHERE userid=#{userId}")
    void deleteUser(Integer userId);
}
