package com.garvey.property.dao;

import com.garvey.property.model.IncomeItemProp;

public interface IncomeItemPropMapper {
    int deleteByPrimaryKey(Long idx);

    int insert(IncomeItemProp record);

    int insertSelective(IncomeItemProp record);

    IncomeItemProp selectByPrimaryKey(Long idx);

    int updateByPrimaryKeySelective(IncomeItemProp record);

    int updateByPrimaryKey(IncomeItemProp record);
}