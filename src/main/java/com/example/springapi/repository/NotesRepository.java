package com.example.springapi.repository;

import com.example.springapi.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotesRepository extends JpaRepository<Note, Long> {
    @Query("select n from notes n where n.user_id = :id")
    List<Note> getAll(@Param("id") Long id);
}
