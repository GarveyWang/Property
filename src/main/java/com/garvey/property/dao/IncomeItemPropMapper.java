package com.garvey.property.dao;

import com.garvey.property.model.IncomeItemProp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IncomeItemPropMapper {
    int deleteByPrimaryKey(Long idx);

    int insert(IncomeItemProp record);

    int insertSelective(IncomeItemProp record);

    IncomeItemProp selectByPrimaryKey(Long idx);

    int updateByPrimaryKeySelective(IncomeItemProp record);

    int updateByPrimaryKey(IncomeItemProp record);
}