package com.example.scheduler.repository;

import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.dto.UserResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.User;

import java.util.List;

public interface UserRepository {
    UserResponseDto saveUser(User user);
//    List<ScheduleResponseDto> findAllSchedules();
//    Schedule findScheduleByIdOrElseThrow(Long id);
//    List<ScheduleResponseDto> findScheduleByNameOrDate(String name, String updatedDate);
//    int updateSchedule(Long id, String name, String content);
//    int deleteSchedule(Long id);

}
