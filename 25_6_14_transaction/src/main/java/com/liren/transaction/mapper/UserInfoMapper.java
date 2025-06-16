package com.liren.transaction.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    @Insert("insert into user_info (`user_name`, `password`) values (#{name}, #{password})")
    Integer insertUser(String name, String password);
}
