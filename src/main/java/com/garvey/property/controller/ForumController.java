package com.garvey.property.controller;

import com.garvey.property.annotation.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author GarveyWong
 * @date 2019/3/26
 */
@Controller
@RequestMapping("/forum")
@Login
public class ForumController {
    @GetMapping
    public String index(){
        return "forum";
    }
}
