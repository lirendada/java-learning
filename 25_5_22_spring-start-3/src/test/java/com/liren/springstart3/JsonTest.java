package com.liren.springstart3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liren.springstart3.model.StudentInfo;
import org.junit.jupiter.api.Test;

public class JsonTest {
    @Test
    public void ObjectToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // writeValueAsString：将对象转化为JSON字符串
        StudentInfo stu = new StudentInfo();
        stu.setName("lirendada");
        stu.setAge(18);
        stu.setPasswd("123123");
        String jsonstr = mapper.writeValueAsString(stu);
        System.out.println("序列化结果：" + jsonstr);

        // readValue：将JSON字符串转化为对象
        StudentInfo new_stu = mapper.readValue(jsonstr, StudentInfo.class);
        System.out.println("反序列化结果：" + new_stu);
    }
}
