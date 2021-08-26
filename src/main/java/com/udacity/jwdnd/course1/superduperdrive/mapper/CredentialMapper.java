package com.udacity.jwdnd.course1.superduperdrive.mapper;

import com.udacity.jwdnd.course1.superduperdrive.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userId}")
    List<Credential> findCredentials(Long userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid=#{credentialId}")
    Credential findCredential(Long credentialId);

    @Insert("INSERT INTO CREDENTIALS  (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key},#{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credential credential);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username},key=#{key},  password=#{password}, userid=#{userId} WHERE credentialid=#{credentialId}")
    void updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialId}")
    void deleteCredential(Long credentialId);
}
