package com.liren.messagemall.mapper;

import com.liren.messagemall.model.MessageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageInfoMapperTest {
    @Autowired
    private MessageInfoMapper messageInfoMapper;

    @Test
    void insertMessage() {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setFrom("liren");
        messageInfo.setTo("tt");
        messageInfo.setMessage("hhhhahhhhawolidnlawd");
        messageInfo.setDeleteFlag(0);
        Date now = new Date();
        messageInfo.setCreateTime(now);
        messageInfo.setUpdateTime(now);
        messageInfoMapper.insertMessage(messageInfo);
    }
}