package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.Log;
import com.garvey.property.model.User;
import com.garvey.property.service.LogService;
import com.garvey.property.util.IpfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/4/3
 */
@Controller
@RequestMapping("/publicity")
@NeededAuthority(authorities = {Authority.BASIC_READ})
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/log")
    public String logPage(User user, Model model) {
        model.addAttribute("user", user);
        return "log";
    }

    @PostMapping("/logs")
    @ResponseBody
    public List<Log> getLogs(User user){
        List<Log> logs = logService.getLogs(user.getCredentials());
        return logs;
    }
}
