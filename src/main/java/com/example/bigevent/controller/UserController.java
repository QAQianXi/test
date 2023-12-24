package com.example.bigevent.controller;

import com.auth0.jwt.interfaces.Claim;
import com.example.bigevent.pojo.Result;
import com.example.bigevent.pojo.User;
import com.example.bigevent.service.UserService;
import com.example.bigevent.utils.JwtUtil;
import com.example.bigevent.utils.Md5Util;
import com.example.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户
        User u = userService.findByUserName(username);
        if (u == null) {
            //没有占用则注册
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
        //注册用户
    }
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
    //查询用户
        User loginUser = userService.findByUserName(username);
        if (loginUser == null) {
            //没有占用则注册
            return Result.error("未注册的用户");
        } else {
            if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
                Map<String, Object> claims = new HashMap<>();
                claims.put("id",loginUser.getId());
                claims.put("username",loginUser.getUsername());
                JwtUtil.genToken(claims);
                return Result.success(JwtUtil.genToken(claims));
            }
            return Result.error("密码错误");
        }
    }
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
        //查询用户信息
//        Map<String, Object> claims = JwtUtil.parseToken(token);
//        String username = (String) claims.get("username");
        Map<String, Object> claims =ThreadLocalUtil.get();
        User user = userService.findByUserName((String) claims.get("username"));
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        UserService.update(user);
        return Result.success();
    }
}
