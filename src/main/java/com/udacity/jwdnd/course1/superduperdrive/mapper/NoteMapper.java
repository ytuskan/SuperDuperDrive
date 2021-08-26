package com.udacity.jwdnd.course1.superduperdrive.mapper;

import com.udacity.jwdnd.course1.superduperdrive.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid=#{userId}")
    List<Note> findAllNotes(Long userId);

    @Select("SELECT * FROM NOTES WHERE noteid=#{noteId}")
    Note findNoteById(Long noteId);

    @Select("SELECT * FROM NOTES WHERE noteid=#{noteId}")
    Note findNote(Note note);

    @Insert("INSERT INTO NOTES (notetitle,notedescription,userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);


    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription}, userid=#{userId} WHERE noteid=#{noteId}")
    void updateNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid=#{noteId}")
    void deleteNote(Long noteId);
}
