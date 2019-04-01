package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.PublicityInfo;
import com.garvey.property.model.User;
import com.garvey.property.service.IpfsService;
import com.garvey.property.service.Web3Service;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GarveyWong
 * @date 2019/3/26
 */
@Controller
@RequestMapping("/publicity")
@NeededAuthority(authorities = {Authority.BASIC_READ})
public class PublicityController {
    @Autowired
    private IpfsService ipfsService;

    @Autowired
    private Web3Service web3Service;

    @GetMapping("/info")
    public String infoPage(User user, Model model) {
        List<PublicityInfo> infoList = web3Service.getPublicityInfos(user.getCredentials());
        model.addAttribute("user", user);
        return "info";
    }

    @GetMapping("/fund")
    public String fundPage(User user, Model model) {
        model.addAttribute("user", user);
        return "fund";
    }

    @GetMapping("/log")
    public String logPage(User user, Model model) {
        model.addAttribute("user", user);
        return "log";
    }

    @PostMapping("/uploadInfo")
    @ResponseBody
    @NeededAuthority(authorities = {Authority.PUBLISH_ANNOUNCEMENT})
    public String uploadInfo(@Param("title") String title, @Param("content") String content,
                             @RequestParam("attachments") MultipartFile[] attachments, User user) {
        PublicityInfo info = new PublicityInfo();
        info.setTitle(title);
        info.setContent(content);
        if (attachments != null && attachments.length > 0) {
            Map<String, String> attachmentsMap = new HashMap<>(attachments.length);
            try {
                for (MultipartFile multipartFile : attachments) {
                    if (!multipartFile.isEmpty()) {
                        File file = new File(multipartFile.getName(), multipartFile.getOriginalFilename());
                        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
                        String hash = ipfsService.upload(file);
                        attachmentsMap.put(hash, multipartFile.getName());
                    }
                }
                info.setAttachments(attachmentsMap);
            } catch (IOException e) {
                return "200";
            }
        }
        web3Service.addPublicityInfo(info, user.getCredentials());
        return "400";
    }
}
