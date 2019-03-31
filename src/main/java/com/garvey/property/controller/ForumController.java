package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author GarveyWong
 * @date 2019/3/26
 */
@Controller
@RequestMapping("/forum")
@NeededAuthority(authorities = {Authority.BASIC_READ})
public class ForumController {
    @GetMapping
    public String index(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "forum";
    }
}
