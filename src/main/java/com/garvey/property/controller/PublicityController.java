package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.PublicityInfo;
import com.garvey.property.model.PublicityInfoProp;
import com.garvey.property.model.User;
import com.garvey.property.service.PublicityService;
import com.garvey.property.util.IpfsUtil;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
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
 * @date 2019/3/26
 */
@Controller
@RequestMapping("/publicity")
@NeededAuthority(authorities = {Authority.BASIC_READ})
public class PublicityController {
    @Autowired
    private IpfsUtil ipfsUtil;

    @Autowired
    private PublicityService publicityService;

    @GetMapping("/info")
    public String infoPage(User user, Model model) {
        List<PublicityInfo> infoList = publicityService.getPublicityInfoList(user);
        model.addAttribute("infoList", infoList);
        model.addAttribute("user", user);
        return "info";
    }

    @PostMapping("/updateInfoProp")
    @ResponseBody
    public String hideInfo(@RequestBody PublicityInfoProp prop){
        int result = publicityService.updatePublicityInfoProp(prop);
        return String.valueOf(result);
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
                        File file = new File(multipartFile.getOriginalFilename(), multipartFile.getOriginalFilename());
                        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
                        String hash = ipfsUtil.upload(file);
                        attachmentsMap.put(hash, multipartFile.getOriginalFilename());
                    }
                }
                info.setAttachments(attachmentsMap);
            } catch (IOException e) {
                return "200";
            }
        }
        publicityService.addPublicityInfo(info, user);
        return "400";
    }
}
