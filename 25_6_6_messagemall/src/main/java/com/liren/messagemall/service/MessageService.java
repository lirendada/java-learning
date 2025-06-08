package com.liren.messagemall.service;

import com.liren.messagemall.mapper.MessageInfoMapper;
import com.liren.messagemall.model.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageInfoMapper messageInfoMapper;

    // ✅ 普通业务方法，不需要 @Bean
    public List<MessageInfo> getList() {
        return messageInfoMapper.selectAll();
    }

    public Boolean publish(MessageInfo messageInfo) {
//        Date date = new Date();
//        messageInfo.setCreateTime(date);
//        messageInfo.setUpdateTime(date);
        Integer aff_num = messageInfoMapper.insertMessage(messageInfo);
        return aff_num == 0 ? false : true;
    }
}
