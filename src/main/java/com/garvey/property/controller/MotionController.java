package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.Motion;
import com.garvey.property.model.User;
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

    @GetMapping
    public String motionPage(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                             User user, Model model) {
        long totalPage = motionService.getMotionTotalPageCount(user.getCredentials());
        List<Motion> motions = motionService.getMotions(user.getCredentials(), pageNum);

        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", pageNum);
        model.addAttribute("motions", motions);
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
        Motion motion = new Motion();
        motion.setTitle(title);
        motion.setContent(content);
        motion.setMultipleVote(multipleVote);
        motion.setOptions(options);
        motion.setLength(options.length);
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
        return "200";
    }
}
