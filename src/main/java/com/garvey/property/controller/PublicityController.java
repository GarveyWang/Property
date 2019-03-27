package com.garvey.property.controller;

import com.garvey.property.annotation.Authority;
import com.garvey.property.util.IpfsUtil;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GarveyWong
 * @date 2019/3/26
 */
@Controller
@RequestMapping("/publicity")
@Authority(property = true, proprietor = true)
public class PublicityController {

    @GetMapping("/info")
    public String infoPage(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "info";
    }

    @GetMapping("/fund")
    public String fundPage(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "fund";
    }

    @GetMapping("/log")
    public String logPage(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "log";
    }

    @PostMapping("/uploadInfo")
    @ResponseBody
    @Authority(property = true)
    public String uploadInfo(@Param("title") String title, @Param("content") String content,
                             @RequestParam("attachments") MultipartFile[] attachments) {

        if (attachments != null && attachments.length > 0) {
            Map<String, File> map = new HashMap<>(attachments.length);
            try {
                for (MultipartFile multipartFile : attachments) {
                    if (!multipartFile.isEmpty()){
                        File file = new File(multipartFile.getName(), multipartFile.getOriginalFilename());
                        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
                        String hash = IpfsUtil.upload(file);
                        map.put(hash, file);
                    }
                }
            }catch (IOException e){
                return "fail";
            }
        }

        return "success";
    }
}
