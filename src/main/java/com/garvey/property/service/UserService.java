package com.garvey.property.service;

import com.garvey.property.model.User;
import com.garvey.property.util.Web3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.web3j.crypto.Credentials;

/**
 * @author GarveyWong
 * @date 2019/3/26
 */
@Service
public class UserService {
    @Autowired
    private Web3Util web3Util;

    public User validate(Credentials credentials, String md5Password) {
        User user = web3Util.getUser(credentials, credentials.getAddress());
        if (user != null) {
            String encryptedPwd = DigestUtils.md5DigestAsHex(md5Password.getBytes()).toLowerCase();
            if (user.getEncryptedPwd().equals(encryptedPwd)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByPhone(Credentials credentials, String encryptedPhone){
        return web3Util.getUserByPhone(credentials, encryptedPhone);
    }

    public boolean addProprietorRegistryCode(Credentials credentials, String encryptedPhone){
        int code = generateCode();
        System.out.println("【业主激活码】" + encryptedPhone + ":" + code);
        web3Util.addProprietorActiveCode(encryptedPhone, code, credentials);
        return web3Util.hasProprietorRegistryCode(encryptedPhone, credentials);
    }

    public boolean addPropertyRegistryCode(Credentials credentials, String encryptedPhone){
        int code = generateCode();
        System.out.println("【物业激活码】" + encryptedPhone + ":" + code);
        web3Util.addPropertyActiveCode(encryptedPhone, code, credentials);
        return web3Util.hasPropertyRegistryCode(encryptedPhone, credentials);
    }

    public boolean registerProperty(int code, User user){
        web3Util.addPropertyAccount(code, user);
        return web3Util.getUser(user.getCredentials(), user.getAddress()) != null;
    }

    public boolean registerProprietor(int code, User user){
        web3Util.addProprietorAccount(code, user);
        return web3Util.getUser(user.getCredentials(), user.getAddress()) != null;
    }

    private int generateCode(){
        return (int)(1 + Math.random()*(999999));
    }
}
