package com.garvey.property.dao;

import com.garvey.property.model.ExpenseGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExpenseGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExpenseGroup record);

    int insertSelective(ExpenseGroup record);

    ExpenseGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExpenseGroup record);

    int updateByPrimaryKey(ExpenseGroup record);
}