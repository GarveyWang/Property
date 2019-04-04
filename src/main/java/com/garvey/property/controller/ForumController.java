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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/discuss")
    public String showDetailDiscussion(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam("id") long discussionId,
                                       @RequestParam(value = "forumPage", defaultValue = "1") int forumPage,
                                       User user, Model model) {
        Discussion discussion = forumService.getDiscussion(user.getCredentials(), discussionId);
        List<Reply> replies = forumService.getReplies(discussionId, user.getCredentials(), page, BasicConst.FORUM_REPLY_PAGE_SIZE);

        model.addAttribute("totalPage", forumService.getReplyTotalPageCount(discussionId));
        model.addAttribute("page", page);
        model.addAttribute("discussion", discussion);
        model.addAttribute("replies", replies);
        model.addAttribute("user", user);
        return "discussion";
    }

}
