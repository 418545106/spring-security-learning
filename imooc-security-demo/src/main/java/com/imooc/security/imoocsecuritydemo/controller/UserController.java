package com.imooc.security.imoocsecuritydemo.controller;

import com.imooc.security.imoocsecuritydemo.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zpd
 * @Date: 2019/1/15 0015 17:11
 * @Description:
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public List<User> query(){
        List<User> users = new ArrayList<>();
        users.add(new User("uuz","1"));
        users.add(new User("uuz","1"));
        users.add(new User("uuz","1"));
        return users;
    }

}
