package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.Log;
import com.garvey.property.model.Motion;
import com.garvey.property.model.User;
import com.garvey.property.service.LogService;
import com.garvey.property.service.MotionService;
import com.garvey.property.util.IpfsUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GarveyWong
 * @date 2019/4/5
 */
@Controller
@RequestMapping("/motion")
@NeededAuthority(authorities = Authority.BASIC_READ)
public class MotionController {
    @Autowired
    private MotionService motionService;

    @Autowired
    private IpfsUtil ipfsUtil;

    @Autowired
    private LogService logService;

    @GetMapping
    public String motionPage(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                             @RequestParam(value = "motionIdx", defaultValue = "-1") int motionIdx,
                             @RequestParam(value = "mine", defaultValue = "false") boolean showMine,
                             User user, Model model) {
        long totalPage;
        List<Motion> motions;
        if (showMine) {
            motions = motionService.getMyMotions(user.getCredentials(), pageNum);
            totalPage = motionService.getMyMotionTotalPageCount(user.getCredentials());
        } else {
            motions = motionService.getMotions(user.getCredentials(), pageNum);
            totalPage = motionService.getMotionTotalPageCount(user.getCredentials());
        }

        model.addAttribute("motionIdx", motionIdx);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", pageNum);
        model.addAttribute("motions", motions);
        model.addAttribute("mine", showMine);
        model.addAttribute("user", user);
        return "motion";
    }

    @PostMapping("/add")
    @NeededAuthority(authorities = Authority.PUBLISH_MOTION)
    @ResponseBody
    public String addMotion(@RequestParam("title") String title, @RequestParam("content") String content,
                            @RequestParam("multipleVote") boolean multipleVote, @RequestParam("options") String[] options,
                            @RequestParam("attachments") MultipartFile[] attachments,
                            User user) {
        if (options.length < 2) {
            return "400";
        }
        Motion motion = new Motion();
        motion.setTitle(title);
        motion.setContent(content);
        motion.setMultipleVote(multipleVote);
        motion.setOptions(options);
        if (attachments != null && attachments.length > 0) {
            Map<String, String> attachmentsMap = new HashMap<>(attachments.length);
            try {
                for (MultipartFile multipartFile : attachments) {
                    if (!multipartFile.isEmpty()) {
                        File file = new File("", multipartFile.getOriginalFilename());
                        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
                        String hash = ipfsUtil.upload(file);
                        attachmentsMap.put(hash, multipartFile.getOriginalFilename());
                    }
                }
            } catch (IOException e) {
                return "400";
            }
            motion.setAttachments(attachmentsMap);
        }
        motionService.addMotion(motion, user.getCredentials());
        logService.writeLog(new Log(System.currentTimeMillis(), user.getAddress(), user.getNickName(), "添加提案《" + title + "》"), user.getCredentials());
        return "200";
    }

    @PostMapping("/vote")
    @NeededAuthority(authorities = Authority.VOTE_MOTION)
    @ResponseBody
    public String vote(@RequestParam("motionIdx") int motionIdx, @RequestParam("optionIndexes") int[] optionIndexes, User user) {
        if (optionIndexes.length > 0) {
            motionService.vote(motionIdx, optionIndexes, user.getCredentials());
            return "200";
        }
        return "400";
    }
}
