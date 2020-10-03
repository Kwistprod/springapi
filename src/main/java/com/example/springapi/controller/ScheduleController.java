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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins="*")
public class ScheduleController {
    @Autowired
    ScheduleRepository scheduleRepository;

    @GetMapping("/get")
    List<Schedule> get(){
        return scheduleRepository.findAll();
    }

    @GetMapping("/{id}")
    String getSchedule(@PathVariable("id") long id) throws Exception{
        Schedule sch = scheduleRepository.gett(id);
        List<Day> days = new ArrayList<>();
        System.out.println(sch.getMonday());
        Day monday = getDay(sch.getMonday());
        Day tuesday = getDay(sch.getTuesday());
        Day wednesday = getDay(sch.getWednesday());
        Day thursday = getDay(sch.getThursday());
        Day friday = getDay(sch.getFriday());
        Day saturday = getDay(sch.getSaturday());
        days.add(monday);
        days.add(tuesday);
        days.add(wednesday);
        days.add(thursday);
        days.add(friday);
        days.add(saturday);
        return new ObjectMapper().writeValueAsString(days);
    }


    public Day getDay(String day){
        Day newDay = new Day();
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
                }
                case 2:{
                    newDay.setClass2(alphaMatch.group());
                }
                case 3:{
                    newDay.setClass3(alphaMatch.group());
                }
                case 4:{
                    newDay.setClass4(alphaMatch.group());
                }
                case 5:{
                    newDay.setClass5(alphaMatch.group());
                }
                case 6:{
                    newDay.setClass6(alphaMatch.group());
                }
            }
        }
        return newDay;
    }

}
