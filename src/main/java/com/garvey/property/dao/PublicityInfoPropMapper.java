package com.garvey.property.dao;

import com.garvey.property.model.PublicityInfoProp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PublicityInfoPropMapper {
    int deleteByPrimaryKey(Long idx);

    int insert(PublicityInfoProp record);

    int insertSelective(PublicityInfoProp record);

    PublicityInfoProp selectByPrimaryKey(Long idx);

    int updateByPrimaryKeySelective(PublicityInfoProp record);

    int updateByPrimaryKeyWithBLOBs(PublicityInfoProp record);

    int updateByPrimaryKey(PublicityInfoProp record);
}