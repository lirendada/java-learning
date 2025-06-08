package com.liren.springstart3.demo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo9")
public class demo9 {
    @ResponseBody
    @RequestMapping("/returnjson")
    public Map<String, String> returnjson() {
        HashMap<String, String> table = new HashMap<>();
        table.put("java", "good");
        table.put("cpp", "not good");
        table.put("redis", "not bad");
        return table;
    }

    @ResponseBody
    @RequestMapping("/setstatus")
    public String setstatus(HttpServletResponse resp) {
        resp.setStatus(404);
        return "设置状态码404成功~";
    }

    @ResponseBody
    @RequestMapping("/setheader1")
    public String setheader(HttpServletResponse resp) {
        resp.setHeader("liren", "lirendada_love_yt");
        return "设置报文成功~";
    }

    @ResponseBody
    @RequestMapping(value="/setheader2", produces="application/json")
    public String setheader() {
        return "{'hha':'no'}";
    }
}
