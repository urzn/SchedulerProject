package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto){

        Schedule schedule = Schedule.builder()
                .name(requestDto.getName())
                .pw(requestDto.getPw())
                .email(requestDto.getEmail())
                .content(requestDto.getContent())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {

        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByNameOrDate(String name, String updatedDate){

        // 필수값 검증
        if(name == null && updatedDate == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name or updatedDate are required values.");
        }

        return scheduleRepository.findScheduleByNameOrDate(name, updatedDate);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id,ScheduleRequestDto requestDto) {

        // 비밀번호 확인, 틀리면 예외 던짐
        checkPw(id, requestDto.getPw());

        int updatedRow = scheduleRepository.updateSchedule(id,requestDto.getName(), requestDto.getContent());

        if(updatedRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "변경된 일정이 없습니다.");
        }

        Schedule updatedSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(updatedSchedule);
    }

    @Override
    public void deleteSchedule(Long id, String pw) {

        // 비밀번호 확인, 틀리면 예외 던짐
        checkPw(id, pw);

        int deletedRow = scheduleRepository.deleteSchedule(id);

        if(deletedRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제된 일정이 없습니다.");
        }

    }

    private void checkPw(Long id, String pw){
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        if(!schedule.getPw().equals(pw)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.");
        }
    }
}
