package com.example.scheduler.dto;

import com.example.scheduler.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private final Long id;
    private String name;
    private String email;
    private String content;
    private final LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.email = schedule.getEmail();
        this.content = schedule.getContent();
        this.createdDate = schedule.getCreatedDate();
        this.updatedDate = schedule.getUpdatedDate();
    }
}
