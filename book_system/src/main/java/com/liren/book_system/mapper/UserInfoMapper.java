package com.liren.book_system.mapper;

import com.liren.book_system.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    UserInfo selectByName(String name);
}
