package com.liren.springstart3.demo;

import com.liren.springstart3.model.StudentInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo4")
@RestController
public class demo4 {
    @RequestMapping("/saveuser")
    public String saveUser(@RequestBody StudentInfo stu) {
        return "json对象保存成功：" + stu.toString();
    }

    @RequestMapping("/getuser")
    public StudentInfo getUser() {
        return new StudentInfo("lirendada", 18, "123123");
    }
}
