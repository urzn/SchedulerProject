package com.example.scheduler.repository;

import com.example.scheduler.entity.Schedule;
import com.example.scheduler.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);
    List<ScheduleResponseDto> findAllSchedules();
    Schedule findScheduleByIdOrElseThrow(Long id);
    List<ScheduleResponseDto> findScheduleByNameOrDate(String name, String updatedDate);
    List<ScheduleResponseDto> findScheduleByUserId(Long userId);
    int updateSchedule(Long id, String name, String content);
    int deleteSchedule(Long id);

}
