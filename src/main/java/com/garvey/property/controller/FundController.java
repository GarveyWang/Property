package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.User;
import com.garvey.property.service.FundService;
import com.garvey.property.util.IpfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/4/3
 */
@Controller
@RequestMapping("/publicity")
@NeededAuthority(authorities = {Authority.BASIC_READ})
public class FundController {
    @Autowired
    private IpfsUtil ipfsUtil;

    @Autowired
    private FundService fundService;

    @GetMapping("/fund")
    public String fundPage(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                           User user, Model model) {
        if (pageNum <= 0) {
            pageNum = 1;
        }

        return "fund";
    }
}
