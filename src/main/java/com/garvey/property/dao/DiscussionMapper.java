package com.garvey.property.dao;

import com.garvey.property.model.Discussion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Discussion record);

    int insertSelective(Discussion record);

    Discussion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Discussion record);

    int updateByPrimaryKeyWithBLOBs(Discussion record);

    int updateByPrimaryKey(Discussion record);

    List<Discussion> getDiscussions(@Param("pageSize") Integer pageSize,@Param("offset") Integer offset);

    long getDiscussionsCount();
}