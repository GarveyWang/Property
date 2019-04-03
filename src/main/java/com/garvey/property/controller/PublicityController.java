package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.PublicityInfo;
import com.garvey.property.model.PublicityInfoProp;
import com.garvey.property.model.User;
import com.garvey.property.service.PublicityService;
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
    public String infoPage(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                           User user, Model model) {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        List<PublicityInfo> infoList = publicityService.getPublicityInfoList(user.getCredentials(), pageNum);
        model.addAttribute("infoList", infoList);
        model.addAttribute("page", pageNum);
        model.addAttribute("totalPage", publicityService.getPublicityInfoTotalPage(user.getCredentials()));
        model.addAttribute("user", user);
        return "info";
    }

    @PostMapping("/updateInfoProp")
    @ResponseBody
    public String updateInfoProp(@RequestBody PublicityInfoProp prop) {
        int result = publicityService.updatePublicityInfoProp(prop);
        return String.valueOf(result);
    }

    @PostMapping("/uploadInfo")
    @ResponseBody
    @NeededAuthority(authorities = {Authority.PUBLISH_ANNOUNCEMENT})
    public String uploadInfo(@RequestParam("title") String title, @RequestParam("content") String content,
                             @RequestParam("attachments") MultipartFile[] attachments, User user) {
        PublicityInfo info = new PublicityInfo();
        info.setTitle(title);
        info.setContent(content);
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
            info.setAttachments(attachmentsMap);
        }
        publicityService.addPublicityInfo(info, user);
        return "200";
    }
}
