package com.garvey.property.service;

import com.garvey.property.dao.UserMapper;
import com.garvey.property.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GarveyWong
 * @date 2019/3/26
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserByCellphone(String cellphone){
        return userMapper.selectByCellphone(cellphone);
    }
}
