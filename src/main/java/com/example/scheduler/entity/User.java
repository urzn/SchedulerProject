package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public User(String name, String email, LocalDateTime createdDate, LocalDateTime updatedDate){
        this.name = name;
        this.email = email;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

//    public void update(String name, String pw, String content, LocalDateTime updatedDate){
//        this.name = name;
//        this.email = content;
//        this.updatedDate = updatedDate;
//    }

}