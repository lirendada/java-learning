package com.liren.springstart3.controller;

import com.liren.springstart3.model.MessageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    List<MessageInfo> list = new ArrayList<>();

    /**
     * 获取所有数据（暂且放在内存中）
     */
    @GetMapping("/getList")
    public List<MessageInfo> getList() {
        return list; // 返回List的话，spring会自动转化为json数组
    }

    /**
     * 发布新留言，将留言存到内存中（学了数据库后可以存放到数据库中）
     */
//    @PostMapping(value = "/publish")
    @PostMapping(value = "/publish", produces = "application/json")
    public String publish(@RequestBody MessageInfo mes) {
        // 判断合法性
        String from = mes.getFrom();
        String to = mes.getTo();
        String message = mes.getMessage();
        if(!StringUtils.hasLength(from) ||
           !StringUtils.hasLength(to) ||
           !StringUtils.hasLength(message)) {
            return "{\"ok\":0}";
        }

        // 合法的话，将留言存放到内存中
        list.add(mes);
        return "{\"ok\":1}";
    }
}
