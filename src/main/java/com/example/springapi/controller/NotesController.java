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
    @ResponseBody
    Map<String, String> addAll(@RequestBody List<Note> notes){
        //List<Note> response = (List<Note>) notesRepository.saveAll(notes);
        Iterable<Note> adas = notes;
        notesRepository.saveAll(adas);
        Map<String, String> map = new HashMap<>();
        map.put("result", "done");
        return map;
    }

}
