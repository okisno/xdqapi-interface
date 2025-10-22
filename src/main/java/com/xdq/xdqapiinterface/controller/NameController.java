package com.xdq.xdqapiinterface.controller;

import com.xdq.xdqapiinterface.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author xdq
 * @description
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/")
    public String getNameByGet(String name) {
        return "GET 你的名字是" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam String name) {
        System.out.println(1);
        return "POST 你的名字是" + name;
    }

    @PostMapping("/json")
    public String getUserNameByPost(@RequestBody User user) {
        System.out.println(2);
        return "POST 用户名字是" + user.getUsername();
    }


}
