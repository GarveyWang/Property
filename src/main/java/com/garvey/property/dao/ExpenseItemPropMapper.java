package com.garvey.property.dao;

import com.garvey.property.model.ExpenseItemProp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExpenseItemPropMapper {
    int deleteByPrimaryKey(Long idx);

    int insert(ExpenseItemProp record);

    int insertSelective(ExpenseItemProp record);

    ExpenseItemProp selectByPrimaryKey(Long idx);

    int updateByPrimaryKeySelective(ExpenseItemProp record);

    int updateByPrimaryKey(ExpenseItemProp record);
}