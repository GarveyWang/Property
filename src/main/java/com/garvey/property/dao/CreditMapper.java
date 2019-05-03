package com.garvey.property.dao;

import com.garvey.property.model.Credit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreditMapper {
    int deleteByPrimaryKey(String userId);

    int insert(Credit record);

    int insertSelective(Credit record);

    Credit selectByPrimaryKey(String userId);

    List<Credit> getAllCredits();

    int updateByPrimaryKeySelective(Credit record);

    int updateByPrimaryKey(Credit record);
}