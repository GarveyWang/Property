package com.garvey.property.dao;

import com.garvey.property.model.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);

    long getRepliesCount(@Param("discussionId") long discussionId);

    List<Reply> getReplies(@Param("discussionId") long discussionId, @Param("pageSize") int pageSize, @Param("offset") int offset);

}