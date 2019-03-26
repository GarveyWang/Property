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
@RequestMapping("/publicity")
public class PublicityController {
    @Login
    @GetMapping("/info")
    public String infoPage(){
        return "info";
    }

    @Login
    @GetMapping("/fund")
    public String fundPage(){
        return "fund";
    }

    @Login
    @GetMapping("/log")
    public String logPage(){
        return "log";
    }
}
