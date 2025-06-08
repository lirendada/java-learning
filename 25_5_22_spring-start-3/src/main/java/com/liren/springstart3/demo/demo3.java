package com.liren.springstart3.demo;

import com.liren.springstart3.model.StudentInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/demo3")
public class demo3 {
    @RequestMapping(value="d1", method= RequestMethod.GET)
    public String d1(String name, String passwd) {
        return name + passwd;
    }

    @RequestMapping(value="d2", method={RequestMethod.GET, RequestMethod.POST})
    public String d2(StudentInfo stu) {
        return stu.toString();
    }

    @RequestMapping("/d3")
    public String d3(@RequestParam("time") String createtime) { // 将time重命名为createtime，只对后端有效
        return createtime;
    }

    @RequestMapping("/d4")
    public String d4(String[] arr) {
        return Arrays.toString(arr);
    }

    @RequestMapping("/d5")
    public String d5(@RequestParam List<String> list) {
        return list.toString();
    }
}
