package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.constant.BasicConst;
import com.garvey.property.model.Discussion;
import com.garvey.property.model.Reply;
import com.garvey.property.model.User;
import com.garvey.property.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/3/26
 */
@Controller
@RequestMapping("/forum")
@NeededAuthority(authorities = {Authority.BASIC_READ})
public class ForumController {
    @Autowired
    private ForumService forumService;

    @GetMapping
    public String showDiscussions(@RequestParam(value = "page", defaultValue = "1") int page,
                                  User user, Model model) {
        List<Discussion> discussions = forumService.getDiscussions(user.getCredentials(), page, BasicConst.FORUM_DISCUSSION_PAGE_SIZE);
        model.addAttribute("totalPage", forumService.getDiscussionTotalPageCount());
        model.addAttribute("page", page);
        model.addAttribute("discussions", discussions);
        model.addAttribute("user", user);
        return "forum";
    }

    @GetMapping("/discussion")
    public String showDetailDiscussion(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam("id") long discussionId,
                                       @RequestParam(value = "forumPage", defaultValue = "1") int forumPage,
                                       User user, Model model) {
        Discussion discussion = forumService.getDiscussion(user.getCredentials(), discussionId);
        List<Reply> replies = forumService.getReplies(discussionId, user.getCredentials(), page, BasicConst.FORUM_REPLY_PAGE_SIZE);

        model.addAttribute("totalPage", forumService.getReplyTotalPageCount(discussionId));
        model.addAttribute("page", page);
        model.addAttribute("forumPage", forumPage);
        model.addAttribute("discussion", discussion);
        model.addAttribute("replies", replies);
        model.addAttribute("user", user);
        return "discussion";
    }

    @PostMapping("/discussion/add")
    @ResponseBody
    @NeededAuthority(authorities = {Authority.PUBLISH_DISCUSSION})
    public String addDiscussion(@RequestParam("title") String title, @RequestParam("content") String content,
                                User user) {
        Discussion discussion = new Discussion();
        discussion.setAuthorId(user.getAddress());
        discussion.setTitle(title);
        discussion.setContent(content);
        long now = System.currentTimeMillis();
        discussion.setCreateTime(now);
        discussion.setUpdateTime(now);
        return String.valueOf(forumService.addDiscussion(discussion));
    }

    @PostMapping("/discussion/delete")
    @ResponseBody
    public String deleteDiscussion(@RequestParam("id") long id, User user) {
        Discussion discussion = forumService.getDiscussion(user.getCredentials(), id);
        if (discussion != null) {
            if (discussion.getAuthorId().equals(user.getAddress())
                    || user.canDeleteDiscussion()){
                forumService.deleteDiscussion(id);
            }
            return "400";
        }
        return "200";
    }

    @PostMapping("/reply/add")
    @ResponseBody
    @NeededAuthority(authorities = {Authority.PARTICIPATE_DISCUSSION})
    public String addReply(@RequestParam("discussionId") long discussionId, @RequestParam("content") String content,
                           User user) {
        Discussion discussion = forumService.getDiscussion(user.getCredentials(), discussionId);
        if (discussion != null) {
            Reply reply = new Reply();
            reply.setAuthorId(user.getAddress());
            reply.setDiscussionId(discussionId);
            reply.setContent(content);
            long now = System.currentTimeMillis();
            reply.setCreateTime(now);
            int result = forumService.addReply(reply);
            if (result == 1) {
                discussion.setUpdateTime(System.currentTimeMillis());
                return String.valueOf(forumService.updateDiscussion(discussion));
            }
        }
        return "400";
    }

    @PostMapping("/reply/delete")
    @ResponseBody
    public String deleteReply(@RequestParam("id") long id, User user) {
        Reply reply = forumService.getReply(id);
        if (reply != null) {
            if (reply.getAuthorId().equals(user.getAddress())
                    || user.canDeleteDiscussion()){
                forumService.deleteReply(id);
            }
            return "400";
        }
        return "200";
    }
}
