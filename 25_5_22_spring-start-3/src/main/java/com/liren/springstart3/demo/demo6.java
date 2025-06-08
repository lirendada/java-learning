package com.liren.springstart3.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/demo6")
public class demo6 {
    @RequestMapping("/file")
    public String uploadfile(@RequestPart MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename()); // 获取照片原名
        System.out.println(file.getName());             // 获取请求时的键名
        System.out.println(file.getContentType());      // 获取文件类型

        // 将文件上传到指定路径
        file.transferTo(new File("E:/杂物文档/" + file.getOriginalFilename()));
        return "接收到的文件名为：" + file.getOriginalFilename();
    }
}
