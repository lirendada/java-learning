package com.liren.messagemall.controller;

import com.liren.messagemall.model.MessageInfo;
import com.liren.messagemall.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/getList")
    public List<MessageInfo> getList() {
        // 读取数据库中的消息，然后进行返回
        return messageService.getList();
    }

    @RequestMapping("publish")
    public Boolean publish(@RequestBody MessageInfo messageInfo) {
        String from = messageInfo.getFrom();
        String to = messageInfo.getTo();
        String mes = messageInfo.getMessage();
        if(!StringUtils.hasLength(from) ||
                !StringUtils.hasLength(to) ||
                !StringUtils.hasLength(mes)) {
            return false;
        }
        return messageService.publish(messageInfo);
    }
}
