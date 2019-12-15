package com.ashang.springsecurity.controller;

import com.ashang.springsecurity.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-12-5 下午5:11
 * <p>
 * 类说明：
 */
@RestController
public class indexWeb {


    @PostMapping("/login")
    public User login(User user) {
        return user;
    }

    @GetMapping("/index")
    public String index(){
        return "nihao!";
    }
}
