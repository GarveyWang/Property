package com.garvey.property.dao;

import com.garvey.property.model.CreditLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CreditLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CreditLog record);

    int insertSelective(CreditLog record);

    CreditLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreditLog record);

    int updateByPrimaryKey(CreditLog record);

    List<CreditLog> getCreditLogsByUserId(@Param("userId") String userId);
}