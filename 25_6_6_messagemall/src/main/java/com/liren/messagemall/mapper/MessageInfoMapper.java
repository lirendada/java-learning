package com.liren.messagemall.mapper;

import com.liren.messagemall.model.MessageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageInfoMapper {
    List<MessageInfo> selectAll();

    Integer insertMessage(MessageInfo messageInfo);
}
