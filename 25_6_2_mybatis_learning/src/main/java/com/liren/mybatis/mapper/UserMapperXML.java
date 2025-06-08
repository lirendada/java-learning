package com.liren.mybatis.mapper;

import com.liren.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapperXML {
    List<User> selectAll();

    Integer insertUser(User user);

    Integer updateUser(User user);

    Integer deleteUser(Integer id);

    Integer insertUserTestIf(User user);

    List<User> selectUserTestWhere(User user);

    Integer updateUserTestSet(User user);

    Integer deleteUserTestForeach(List<Integer> ids);

    List<User> selectUserTestInclude(Integer id);
}
