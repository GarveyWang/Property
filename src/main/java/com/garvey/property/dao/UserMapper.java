package com.garvey.property.dao;

import com.garvey.property.model.db.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User selectByCellphone(String cellphone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}