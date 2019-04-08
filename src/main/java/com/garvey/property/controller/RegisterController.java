package com.garvey.property.controller;

import com.garvey.property.model.User;
import com.garvey.property.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author GarveyWong
 * @date 2019/3/30
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register/property")
    @ResponseBody
    public String registerPropertyAccount(@RequestParam("activeCode") int activeCode,
                                          @RequestParam("encryptedPhone") String encryptedPhone,
                                          @RequestParam("md5Psw") String md5Psw,
                                          @RequestParam("nickName") String nickName,
                                          HttpServletResponse response) throws CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
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
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        if (fileInputStream != null) {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(),"UTF-8"));
            byte[] buffer = new byte[1024];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                OutputStream outputStream = response.getOutputStream();
                int i = bufferedInputStream.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bufferedInputStream.read(buffer);
                }
                return "200";
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileInputStream.close();
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "400";
    }

    @PostMapping("/register/proprietor")
    @ResponseBody
    public String registerProprietorAccount(@RequestParam("activeCode") int activeCode,
                                            @RequestParam("encryptedPhone") String encryptedPhone,
                                            @RequestParam("md5Psw") String md5Psw,
                                            @RequestParam("nickName") String nickName,
                                            HttpServletResponse response) throws CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
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
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        if (fileInputStream != null) {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(),"UTF-8"));
            byte[] buffer = new byte[1024];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                OutputStream outputStream = response.getOutputStream();
                int i = bufferedInputStream.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bufferedInputStream.read(buffer);
                }
                return "200";
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileInputStream.close();
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "400";
    }

}
