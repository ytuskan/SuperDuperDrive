package com.udacity.jwdnd.course1.superduperdrive.mapper;

import com.udacity.jwdnd.course1.superduperdrive.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE filename={fileName}")
    File findFileByName(String fileName);

    @Select("SELECT * FROM FILES WHERE fileid=#{fileId}")
    File findFile(Long fileId);

    @Select("SELECT filename FROM FILES WHERE  fileid=#{fileId}")
    String findFileName(Long fileId);

    @Select("SELECT * FROM FILES WHERE userid=#{userId}")
    List<File> findFilesByUserId(Long userId);

    @Insert("INSERT INTO FILES (filename,contenttype,filesize,filedata,userid) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);


    @Update("UPDATE FILES SET filename=#{fileName}, contenttype=#{contentType}, filesize=#{fileSize}, filedata=#{fileData}, userid=#{userId} WHERE fileid=#{fileId}")
    void updateFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid=#{fileId}")
    void deleteFile(Long fileId);
}