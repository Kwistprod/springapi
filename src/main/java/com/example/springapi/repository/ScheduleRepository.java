package com.example.springapi.repository;

import com.example.springapi.models.Schedule;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {


}
