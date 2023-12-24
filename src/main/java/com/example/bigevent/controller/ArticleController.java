package com.example.bigevent.controller;

import com.example.bigevent.pojo.Result;
import com.example.bigevent.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> getArticleList(@RequestHeader(name = "Authorization")String token, HttpServletResponse response){
        //验证接口

        return Result.success("1上述");
    }
}
