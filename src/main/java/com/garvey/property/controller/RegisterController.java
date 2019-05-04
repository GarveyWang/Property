package com.garvey.property.controller;

import com.garvey.property.constant.Authority;
import com.garvey.property.model.Credit;
import com.garvey.property.model.Log;
import com.garvey.property.model.User;
import com.garvey.property.service.CreditService;
import com.garvey.property.service.LogService;
import com.garvey.property.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GarveyWong
 * @date 2019/3/30
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private CreditService creditService;

    @Autowired
    private LogService logService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register/property")
    @ResponseBody
    public Map registerPropertyAccount(@RequestParam("activeCode") int activeCode,
                                          @RequestParam("encryptedPhone") String encryptedPhone,
                                          @RequestParam("md5Psw") String md5Psw,
                                          @RequestParam("nickName") String nickName) throws CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        User user = new User();
        user.setEncryptedPhone(encryptedPhone);
        user.setNickName(nickName);
        String encryptPsw = DigestUtils.md5DigestAsHex(md5Psw.getBytes()).toLowerCase();
        user.setEncryptedPwd(encryptPsw);

        String filePath = WalletUtils.generateLightNewWalletFile(md5Psw, null);
        Credentials credentials = WalletUtils.loadCredentials(md5Psw, filePath);
        user.setCredentials(credentials);
        user.setAddress(credentials.getAddress());
        userService.registerProperty(activeCode, user);
        Map<String, String> map = new HashMap<>();
        map.put("filePath", filePath);
        Credit credit = new Credit(user.getAddress(), 0L);
        creditService.insertCredit(credit);
        User etherUser = userService.getUserByAddress(credentials, user.getAddress());
        if (etherUser == null){
            map.put("registerResult", "fail");
        } else {
            map.put("registerResult", "success");
            logService.writeLog(new Log(System.currentTimeMillis(), etherUser.getAddress(), etherUser.getNickName(), "账号注册"), user.getCredentials());
        }
        return map;
    }

    @PostMapping("/register/proprietor")
    @ResponseBody
    public Map registerProprietorAccount(@RequestParam("activeCode") int activeCode,
                                            @RequestParam("encryptedPhone") String encryptedPhone,
                                            @RequestParam("md5Psw") String md5Psw,
                                            @RequestParam("nickName") String nickName) throws CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        User user = new User();
        user.setEncryptedPhone(encryptedPhone);
        user.setNickName(nickName);
        String encryptPsw = DigestUtils.md5DigestAsHex(md5Psw.getBytes()).toLowerCase();
        user.setEncryptedPwd(encryptPsw);

        String filePath = WalletUtils.generateLightNewWalletFile(md5Psw, null);
        Credentials credentials = WalletUtils.loadCredentials(md5Psw, filePath);
        user.setCredentials(credentials);
        user.setAddress(credentials.getAddress());
        userService.registerProprietor(activeCode, user);
        Map<String, String> map = new HashMap<>();
        map.put("filePath", filePath);
        Credit credit = new Credit(user.getAddress(), 0L);
        creditService.insertCredit(credit);
        User etherUser = userService.getUserByAddress(credentials, user.getAddress());
        if (etherUser == null){
            map.put("registerResult", "fail");
        } else {
            map.put("registerResult", "success");
            logService.writeLog(new Log(System.currentTimeMillis(), etherUser.getAddress(), etherUser.getNickName(), "账号注册"), user.getCredentials());
        }
        return map;
    }

}
