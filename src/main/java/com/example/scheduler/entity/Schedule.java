package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule{

    private Long id;
    private String name;
    private String pw;
    private String email;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public Schedule(String name, String pw, String email, String content, LocalDateTime createdDate, LocalDateTime updatedDate){
        this.name = name;
        this.pw = pw;
        this.email = email;
        this.content = content;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public void update(String name, String pw, String content, LocalDateTime updatedDate){
        this.name = name;
        this.pw = pw;
        this.content = content;
        this.updatedDate = updatedDate;
    }

}