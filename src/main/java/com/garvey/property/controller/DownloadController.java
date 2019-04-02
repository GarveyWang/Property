package com.garvey.property.controller;

import com.garvey.property.util.IpfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author GarveyWong
 * @date 2019/4/1
 */
@Controller
public class DownloadController {

    @Autowired
    private IpfsUtil ipfsUtil;

    @PostMapping("/download")
    public String download(@RequestParam("fileHash") String fileHash, @RequestParam("fileName") String fileName,
                           HttpServletResponse response) throws UnsupportedEncodingException {
        FileInputStream fileInputStream = ipfsUtil.getFileInputStream(fileHash);
        if (fileInputStream != null) {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName,"UTF-8"));
            byte[] buffer = new byte[1024];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                OutputStream outputStream = response.getOutputStream();
                int i = bufferedInputStream.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bufferedInputStream.read(buffer);
                }
                return "200";
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileInputStream.close();
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "400";
    }
}
