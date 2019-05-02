package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.User;
import com.garvey.property.service.AuthService;
import com.garvey.property.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author GarveyWong
 * @date 2019/4/7
 */
@Controller
@RequestMapping("/account")
@NeededAuthority(authorities = Authority.BASIC_READ)
public class AccountManagementController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @GetMapping("/management")
    public String managementPage(User user, Model model) {
        List<User> userList = userService.getUsers(user.getCredentials());
        int settledMinCount = authService.getSettledMinCount(user.getCredentials());
        int settleRatio = authService.getSettledRatio(user.getCredentials());
        model.addAttribute("userList", userList);
        model.addAttribute("user", user);
        model.addAttribute("settledMinCount", settledMinCount);
        model.addAttribute("settledRatio", settleRatio);
        return "accountManagement";
    }

    @PostMapping("/addPropertyActiveCode")
    @ResponseBody
    @NeededAuthority(authorities = Authority.ADD_PROPERTY_ACCOUNT)
    public String addPropertyActiveCode(@RequestParam("encryptedPhone") String encryptedPhone, User user) {
        if (userService.addPropertyRegistryCode(user.getCredentials(), encryptedPhone)) {
            return "200";
        }
        return "400";
    }

    @PostMapping("/addProprietorActiveCode")
    @ResponseBody
    @NeededAuthority(authorities = Authority.ADD_PROPRIETOR_ACCOUNT)
    public String addProprietorActiveCode(@RequestParam("encryptedPhone") String encryptedPhone, User user) {
        if (userService.addProprietorRegistryCode(user.getCredentials(), encryptedPhone)) {
            return "200";
        }
        return "400";
    }


}
