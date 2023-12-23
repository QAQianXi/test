package com.example.bigevent.service.impl;

import com.example.bigevent.mapper.UserMapper;
import com.example.bigevent.pojo.User;
import com.example.bigevent.service.UserService;
import com.example.bigevent.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {

        User u =userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        // password must be safe!
        String md5password = Md5Util.getMD5String(password);
        userMapper.addNewUser(username, md5password);

    }
}
