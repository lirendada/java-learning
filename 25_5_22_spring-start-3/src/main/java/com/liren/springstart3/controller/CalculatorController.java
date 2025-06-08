package com.liren.springstart3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/calc")
@RestController
public class CalculatorController {
    @RequestMapping(value="/sum",
                    method = {RequestMethod.GET, RequestMethod.POST})
    public String sum(Integer num1, Integer num2) {
        Integer ret = num1 + num2;
        return "计算结果为：" + ret;
    }
}
