package com.example.scheduler.controller;

import com.example.scheduler.dto.*;
import com.example.scheduler.service.ScheduleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController{
    private final ScheduleService scheduleService;

    private ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules(){
        return scheduleService.findAllSchedules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<ScheduleResponseDto> findScheduleByNameOrDate(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String date
    ){
//        LocalDate updatedDate = null;
//        if(date != null){
//            updatedDate = LocalDate.parse(date);
//        }

        return scheduleService.findScheduleByNameOrDate(name, date);
    }

    @GetMapping("/search/by-user")
    public List<ScheduleResponseDto> findScheduleByUserId(
            @RequestParam Long userId
    ) {
        return scheduleService.findScheduleByUserId(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto
    ) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, requestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody String pw
    ) {
        scheduleService.deleteSchedule(id, pw);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}