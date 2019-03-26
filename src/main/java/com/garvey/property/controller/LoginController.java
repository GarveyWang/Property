package com.garvey.property.controller;

import com.garvey.property.model.User;
import com.garvey.property.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;

/**
 * @author GarveyWong
 * @date 2019/3/9
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login/validate")
    public String validate(@RequestParam("cellphone") String cellphone, @RequestParam("password") String md5Password,
                           RedirectAttributes redirectAttributes, HttpSession session) {
        User user = userService.getUserByCellphone(cellphone);
        if (user == null || !user.getMd5password().equals(md5Password)) {
            redirectAttributes.addFlashAttribute("info", "手机号或密码不正确！");
            return "redirect:/login";
        }
        session.setAttribute("user",user);
        return "redirect:/publicity/info";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
