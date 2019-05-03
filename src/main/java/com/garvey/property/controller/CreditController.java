package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.Credit;
import com.garvey.property.model.CreditLog;
import com.garvey.property.model.User;
import com.garvey.property.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/5/2
 */
@Controller
@RequestMapping("/credit")
@NeededAuthority(authorities = Authority.BASIC_READ)
public class CreditController {
    @Autowired
    private CreditService creditService;

    @GetMapping
    public String showCreditPage(User user, Model model) {
        Credit myCredit = creditService.getCredit(user.getAddress(), user.getCredentials());
        model.addAttribute("myCredit", myCredit);
        model.addAttribute("user", user);
        return "credit";
    }

    @PostMapping("/all")
    @ResponseBody
    public List<Credit> getAllCredits(User user) {
        List<Credit> allCredits = creditService.getAllCredits(user.getCredentials());
        return allCredits;
    }

    @PostMapping("/logs")
    @ResponseBody
    public List<CreditLog> getCreditLogs(@RequestParam("userId") String userId, User user) {
        List<CreditLog> creditLogs = creditService.getCreditLogs(user.getCredentials(), userId);
        return creditLogs;
    }

    @NeededAuthority(authorities = {Authority.SUPER})
    @PostMapping("/update")
    @ResponseBody
    public String updateCredit(@RequestBody CreditLog creditLog, User user) {
        creditService.updateCredit(creditLog);
        return "200";
    }
}
