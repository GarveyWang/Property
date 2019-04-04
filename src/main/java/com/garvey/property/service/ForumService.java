package com.garvey.property.service;

import com.garvey.property.constant.BasicConst;
import com.garvey.property.dao.DiscussionMapper;
import com.garvey.property.dao.ReplyMapper;
import com.garvey.property.model.Discussion;
import com.garvey.property.model.Reply;
import com.garvey.property.model.User;
import com.garvey.property.util.Web3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/4/4
 */
@Service
public class ForumService {
    @Autowired
    private DiscussionMapper discussionMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private Web3Util web3Util;

    public Discussion getDiscussion(Credentials credentials, long discussionId) {
        Discussion discussion = discussionMapper.selectByPrimaryKey(discussionId);
        User user = web3Util.getUser(credentials, discussion.getAuthorId());
        if (user != null){
            discussion.setAuthorName(user.getNickName());
        }
        return discussion;
    }

    public List<Discussion> getDiscussions(Credentials credentials, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Discussion> discussions = discussionMapper.getDiscussions(pageSize, offset);
        for (Discussion discussion: discussions){
            User user = web3Util.getUser(credentials, discussion.getAuthorId());
            if (user != null){
                discussion.setAuthorName(user.getNickName());
            }
        }
        return discussions;
    }

    public List<Reply> getReplies(long discussionId, Credentials credentials, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Reply> replies = replyMapper.getReplies(discussionId, pageSize, offset);
        for (Reply reply: replies){
            User user = web3Util.getUser(credentials, reply.getAuthorId());
            if (user != null){
                reply.setAuthorName(user.getNickName());
            }
        }
        return replies;
    }

    public long getDiscussionTotalPageCount() {
        long totalDiscussionCount = discussionMapper.getDiscussionsCount();
        if (totalDiscussionCount == 0) {
            return 1;
        }
        if (totalDiscussionCount % BasicConst.FORUM_DISCUSSION_PAGE_SIZE == 0) {
            return totalDiscussionCount / BasicConst.FORUM_DISCUSSION_PAGE_SIZE;
        }
        return totalDiscussionCount / BasicConst.FORUM_DISCUSSION_PAGE_SIZE + 1;
    }

    public long getReplyTotalPageCount(long discussionId) {
        long totalReplyCount = replyMapper.getRepliesCount(discussionId);
        if (totalReplyCount == 0) {
            return 1;
        }
        if (totalReplyCount % BasicConst.FORUM_REPLY_PAGE_SIZE == 0) {
            return totalReplyCount / BasicConst.FORUM_REPLY_PAGE_SIZE;
        }
        return totalReplyCount / BasicConst.FORUM_REPLY_PAGE_SIZE + 1;
    }
}
