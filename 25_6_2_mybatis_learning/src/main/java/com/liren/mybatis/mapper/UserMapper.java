package com.liren.mybatis.mapper;

import com.liren.mybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    // 一、参数传递
    // （三种方式处理java字段名和数据库字段名不同的问题）
    //  1. 使用数据库中的as别名
    //  2. 使用@Results、@Result、@ResultMap解决
    //  3. 配置yml文件设置mybatis:configuration:map-underscore-to-camel-case: true
//    @Select("select * from user_info")
//    @Select("select id, username, password, age, gender, phone, delete_flag as deleteFlag, " +
//            "create_time as createTime, update_time as updateTime from user_info")
//    @Results(id = "BaseMap", value = {
//            @Result(column = "delete_flag", property = "deleteFlag"),
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
//    @Select("select * from user_info")
    List<User> selectAll();

//    @ResultMap("BaseMap")
    @Select("select * from user_info where id = #{id}")
    User selectByID(Integer id);

    @Select("select * from user_info where username = #{username} and password = #{passwd}")
    User selectByName(@Param("username") String name, String passwd);

    // 二、增
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user_info (username, password, age) " +
            "values (#{username}, #{password}, #{age})")
    Integer insertUser(User user);

    // 三、删
    @Delete("delete from user_info where id = #{id}")
    Integer deleteUser(Integer id);

    // 四、改
    @Update("update user_info set age = #{age}, phone = #{phone}, delete_flag = #{deleteFlag} " +
            "where id = #{id}")
    Integer updateUser(User user);
}
