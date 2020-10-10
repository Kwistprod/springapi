package com.example.springapi.controller;

import com.example.springapi.models.Note;
import com.example.springapi.repository.NotesRepository;
import com.example.springapi.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins="*")
public class NotesController {
    @Autowired
    NotesRepository notesRepository;

    @GetMapping("/notes/{id}")
    List<Note> getAll(@PathVariable Long id){
        return notesRepository.getAll(id);
    }
    @PostMapping("/notes")
    Map<String, String> addOne(@RequestBody Note note){
        Map<String, String> map = new HashMap<>();
        notesRepository.save(note);
        map.put("result", "done");
        return map;
    }

}
