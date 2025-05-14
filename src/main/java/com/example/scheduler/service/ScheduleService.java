package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);
    List<ScheduleResponseDto> findAllSchedules();
    ScheduleResponseDto findScheduleById(Long id);
    List<ScheduleResponseDto> findScheduleByNameOrDate(String name, String updatedDate);
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto);

    void deleteSchedule(Long id, String pw);
}
