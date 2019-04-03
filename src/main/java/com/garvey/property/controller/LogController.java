package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.User;
import com.garvey.property.util.IpfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author GarveyWong
 * @date 2019/4/3
 */
@Controller
@RequestMapping("/publicity")
@NeededAuthority(authorities = {Authority.BASIC_READ})
public class LogController {
    @Autowired
    private IpfsUtil ipfsUtil;

    @GetMapping("/log")
    public String logPage(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                           User user, Model model) {
        if (pageNum <= 0) {
            pageNum = 1;
        }

        return "fund";
    }
}
