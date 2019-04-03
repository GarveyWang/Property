package com.garvey.property.dao;

import com.garvey.property.model.IncomeGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IncomeGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IncomeGroup record);

    int insertSelective(IncomeGroup record);

    IncomeGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IncomeGroup record);

    int updateByPrimaryKey(IncomeGroup record);
}