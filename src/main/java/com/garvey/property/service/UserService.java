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
}
