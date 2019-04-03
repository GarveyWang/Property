package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.ExpenseItem;
import com.garvey.property.model.IncomeItem;
import com.garvey.property.model.User;
import com.garvey.property.service.FundService;
import com.garvey.property.util.IpfsUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String fundPage(User user, Model model) {
        //Map<Integer, List<IncomeItem>> monthlyIncomeItems = fundService.getMonthlyIncomeItems(user.getCredentials());
        //Map<Integer, List<IncomeItem>> monthlyIncomeItems = fundService.mockMonthlyIncomeItems(user.getCredentials());
        //Map<Integer, List<ExpenseItem>> monthlyExpenseItems = fundService.getMonthlyExpenseItems(user.getCredentials());
        //Map<Integer, List<ExpenseItem>> monthlyExpenseItems = fundService.mockMonthlyExpenseItems(user.getCredentials());

        //model.addAttribute("monthlyIncomeItems", monthlyIncomeItems);
        //model.addAttribute("monthlyExpenseItems", monthlyExpenseItems);
        model.addAttribute("user", user);
        return "fund";
    }

    @PostMapping("/income")
    @ResponseBody
    public Map<Integer, List<IncomeItem>> getMonthlyIncomeItems(User user){
        return fundService.mockMonthlyIncomeItems(user.getCredentials());
    }

    @PostMapping("/expense")
    @ResponseBody
    public Map<Integer, List<ExpenseItem>> getMonthlyExpenseItems(User user){
        return fundService.mockMonthlyExpenseItems(user.getCredentials());
    }

    @PostMapping("/addIncome")
    @ResponseBody
    @NeededAuthority(authorities = {Authority.PUBLISH_FUND})
    public String addIncome(@RequestParam("payer") String payer, @RequestParam("amountInCents") long amountInCents,
                            @RequestParam("desc") String desc, @RequestParam("attachments") MultipartFile[] attachments,
                            User user) {
        IncomeItem incomeItem = new IncomeItem();
        incomeItem.setPayer(payer);
        incomeItem.setAmountInCents(amountInCents);
        incomeItem.setDesc(desc);
        if (attachments != null && attachments.length > 0) {
            Map<String, String> attachmentsMap = new HashMap<>(attachments.length);
            try {
                for (MultipartFile multipartFile : attachments) {
                    if (!multipartFile.isEmpty()) {
                        File file = new File("", multipartFile.getOriginalFilename());
                        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
                        String hash = ipfsUtil.upload(file);
                        attachmentsMap.put(hash, multipartFile.getOriginalFilename());
                    }
                }
            } catch (IOException e) {
                return "400";
            }
            incomeItem.setAttachments(attachmentsMap);
        }
        fundService.addIncomeItem(incomeItem, user);
        return "200";
    }

    @PostMapping("/addExpense")
    @ResponseBody
    @NeededAuthority(authorities = {Authority.PUBLISH_FUND})
    public String addExpense(@RequestParam("payee") String payee, @RequestParam("amountInCents") long amountInCents,
                            @RequestParam("desc") String desc, @RequestParam("attachments") MultipartFile[] attachments,
                            User user) {
        ExpenseItem expenseItem = new ExpenseItem();
        expenseItem.setPayee(payee);
        expenseItem.setAmountInCents(amountInCents);
        expenseItem.setDesc(desc);
        if (attachments != null && attachments.length > 0) {
            Map<String, String> attachmentsMap = new HashMap<>(attachments.length);
            try {
                for (MultipartFile multipartFile : attachments) {
                    if (!multipartFile.isEmpty()) {
                        File file = new File("", multipartFile.getOriginalFilename());
                        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
                        String hash = ipfsUtil.upload(file);
                        attachmentsMap.put(hash, multipartFile.getOriginalFilename());
                    }
                }
            } catch (IOException e) {
                return "400";
            }
            expenseItem.setAttachments(attachmentsMap);
        }
        fundService.addExpenseItem(expenseItem, user);
        return "200";
    }
}
