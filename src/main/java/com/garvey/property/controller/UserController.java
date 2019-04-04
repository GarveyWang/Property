package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.User;
import com.garvey.property.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author GarveyWong
 * @date 2019/4/4
 */
@Controller
@RequestMapping("/user")
@NeededAuthority(authorities = Authority.BASIC_READ)
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/searchByPhone")
    public User getUserByPhone(@RequestParam("encryptedPhone") String encryptedPhone, User user) {
        return userService.getUserByPhone(user.getCredentials(), encryptedPhone);
    }
}
