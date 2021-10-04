package com.udacity.jwdnd.course1.superduperdrive.mapper;

import com.udacity.jwdnd.course1.superduperdrive.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE filename=#{fileName}")
    File findFileByName(String fileName);

    @Select("SELECT * FROM FILES WHERE fileid=#{fileId}")
    File findFile(Long fileId);

    @Select("SELECT * FROM FILES WHERE userid=#{userId}")
    List<File> findFilesByUserId(Long userId);

    @Insert("INSERT INTO FILES (filename,contenttype,filesize,filedata,userid) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid=#{fileId}")
    void deleteFile(Long fileId);
}