package com.example.scheduler.repository;

import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.dto.UserResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserResponseDto saveUser(User user){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("createdDate", Timestamp.valueOf(user.getCreatedDate()));
        parameters.put("updatedDate", Timestamp.valueOf(user.getUpdatedDate()));

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new UserResponseDto(key.longValue(), user.getName(), user.getEmail(), user.getCreatedDate(), user.getUpdatedDate());
    }

//    @Override
//    public List<ScheduleResponseDto> findAllSchedules(){
//        return jdbcTemplate.query("select * from schedule",scheduleRowMapper());
//    }
//
//    @Override
//    public Schedule findScheduleByIdOrElseThrow(Long id){
//        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
//
//        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
//
//    }
//
//    @Override
//    public List<ScheduleResponseDto> findScheduleByNameOrDate(String name, String updatedDate){
//        StringBuilder sql = new StringBuilder("select * from schedule where ");
//        List<Object> args = new ArrayList<>();
//
//        // 이름과 날짜에 따라 sql 설정하는 로직
//        // 이름으로 조회하려고 할 때
//        if(name != null){
//            sql.append("name = ?");
//            args.add(name);
//
//            // 이름과 날짜로 조회하려고 할 때
//            if(updatedDate != null){
//                sql.append(" and DATE(updatedDate) = ?");
//                args.add(updatedDate);
//            }
//        }
//        else{
//            // 날짜로만 조회하려고 할 때
//            if(updatedDate != null){
//                sql.append("DATE(updatedDate) = ?");
//                args.add(updatedDate);
//            }
//        }
//        sql.append(" order by updatedDate DESC");
//
//        return jdbcTemplate.query(sql.toString(), scheduleRowMapper(), args.toArray());
//    }
//
//    @Override
//    public int updateSchedule(Long id, String name, String content) {
//        int result = jdbcTemplate.update("update schedule set name = ?, content = ? where id = ?", name, content, id);
//
//        return result;
//    }
//
//    @Override
//    public int deleteSchedule(Long id){
//        return jdbcTemplate.update("delete from schedule where id = ?", id);
//    }
//
//    private RowMapper<ScheduleResponseDto> scheduleRowMapper(){
//        return new RowMapper<ScheduleResponseDto>(){
//            @Override
//            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException{
//                return new ScheduleResponseDto(
//                        rs.getLong("id"),
//                        rs.getString("name"),
//                        rs.getString("content"),
//                        rs.getTimestamp("createdDate").toLocalDateTime(),
//                        rs.getTimestamp("updatedDate").toLocalDateTime()
//                );
//            }
//        };
//    }
//    private RowMapper<Schedule> scheduleRowMapperV2(){
//        return new RowMapper<Schedule>(){
//            @Override
//            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException{
//                return new Schedule(
//                        rs.getLong("id"),
//                        rs.getString("name"),
//                        rs.getString("pw"),
//                        rs.getString("content"),
//                        rs.getTimestamp("createdDate").toLocalDateTime(),
//                        rs.getTimestamp("updatedDate").toLocalDateTime()
//                );
//            }
//        };
//    }
}
