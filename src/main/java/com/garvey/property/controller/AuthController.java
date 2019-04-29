package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.AuthOperation;
import com.garvey.property.model.User;
import com.garvey.property.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/4/29
 */
@Controller
@RequestMapping("/auth")
@NeededAuthority(authorities = Authority.BASIC_READ)
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/applicationList")
    @ResponseBody
    public List<AuthOperation> getAuthApplications(User user){
        return authService.getAuthApplications(user.getCredentials());
    }

    @PostMapping("/cancellationList")
    @ResponseBody
    public List<AuthOperation> getAuthCancellations(User user){
        return authService.getAuthCancellations(user.getCredentials());
    }
}
