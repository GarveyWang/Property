package com.garvey.property.controller;

import com.garvey.property.model.User;
import com.garvey.property.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author GarveyWong
 * @date 2019/3/9
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String indexPage() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

//    @PostMapping("/login/validate")
//    public String validate(@RequestParam("cellphone") String cellphone, @RequestParam("password") String md5Password,
//                           RedirectAttributes redirectAttributes, HttpSession session) {
////        User user = userService.getUserByCellphone(cellphone);
////        String doubleEncryptedPwd = DigestUtils.md5DigestAsHex(md5Password.getBytes());
////        if (user == null || !user.getDoubleEncryptedPwd().equals(doubleEncryptedPwd)) {
////            redirectAttributes.addFlashAttribute("info", "手机号或密码不正确！");
////            return "redirect:/login";
////        }
////        user.setMd5Pwd(md5Password);
////        user.setRole(1);
////        session.setAttribute("user",user);
////        return "redirect:/keystore";
//    }

    @GetMapping("/keystore")
    public String keystorePage() {
        return "keystore";
    }

    @PostMapping("/login/validate")
    @ResponseBody
    public String validate(@Param("keystore") MultipartFile keystore, @Param("md5Password") String md5Password, HttpSession session) {
        if (keystore.isEmpty() || StringUtils.isEmpty(md5Password)) {
            return "400";
        }
        File file = new File(keystore.getName(), keystore.getOriginalFilename());
        try {
            FileUtils.copyInputStreamToFile(keystore.getInputStream(), file);
            Credentials credentials = WalletUtils.loadCredentials(md5Password, file);
            if (credentials != null) {
                User user = userService.validate(credentials, md5Password);
                if (user != null) {
                    session.setAttribute("user", user);
                    return "200";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "400";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
