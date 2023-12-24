package com.example.bigevent.mapper;

import com.example.bigevent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    @Insert("insert into user(username,password,create_time,update_time)"+"value(#{username},#{md5password},now(),now())")
    void addNewUser(String username, String md5password);
    @Update("update user ser nickname = #{nickname},email=#{email},update_time=#{update_time} where id=#{id}")
    void update(User user);
}
