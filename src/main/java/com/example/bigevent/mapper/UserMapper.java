package com.example.bigevent.mapper;

import com.example.bigevent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    @Insert("insert into user(username,password,creat_time,update_time)"+"value(#{username},#{md5password},now(),now())")
    void addNewUser(String username, String md5password);
}
