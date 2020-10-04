package com.example.springapi.controller;

import com.example.springapi.models.Day;
import com.example.springapi.models.Schedule;
import com.example.springapi.repository.ScheduleRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins="*")
public class ScheduleController {
    @Autowired
    ScheduleRepository scheduleRepository;

    @GetMapping("/schedule/getAll")
    List<Schedule> get(){
        return scheduleRepository.findAll();
    }

    @PostMapping("/schedule")
    Map<String, Object> addSchedule(@RequestBody Schedule schedule){
        Map<String, Object> map = new HashMap<>();
        scheduleRepository.save(schedule);
        map.put("result", "done");
        return map;
    }

    @GetMapping("/schedule/{id}")
    List<Day> getSchedule(@PathVariable("id") long id) throws Exception{
        Schedule sch = scheduleRepository.getOne(id);
        List<Day> days = new ArrayList<>();
        Day monday = getDay(sch.getMonday(), 1);
        Day tuesday = getDay(sch.getTuesday(), 2);
        Day wednesday = getDay(sch.getWednesday(), 3);
        Day thursday = getDay(sch.getThursday(), 4);
        Day friday = getDay(sch.getFriday(), 5);
        Day saturday = getDay(sch.getSaturday(), 6);
        days.add(monday);
        days.add(tuesday);
        days.add(wednesday);
        days.add(thursday);
        days.add(friday);
        days.add(saturday);
        return days;

    }


    public Day getDay(String day, int dayid){
        Day newDay = new Day(dayid);
        Pattern alpha = Pattern.compile("\\D+");
        Pattern numeric = Pattern.compile("[0-9]");
        Matcher numMatch = numeric.matcher(day);
        Matcher alphaMatch = alpha.matcher(day);
        while(numMatch.find()){
            int classNum = 0;
            try{
                classNum = Integer.parseInt(numMatch.group());
            }catch(NumberFormatException e){
                System.out.println(e.toString());
            }
            alphaMatch.find();
            switch(classNum){
                case 1:{
                    newDay.setClass1(alphaMatch.group());
                    break;
                }
                case 2:{
                    newDay.setClass2(alphaMatch.group());
                    break;
                }
                case 3:{
                    newDay.setClass3(alphaMatch.group());
                    break;
                }
                case 4:{
                    newDay.setClass4(alphaMatch.group());
                    break;
                }
                case 5:{
                    newDay.setClass5(alphaMatch.group());
                    break;
                }
                case 6:{
                    newDay.setClass6(alphaMatch.group());
                    break;
                }
            }
        }
        return newDay;
    }

}
