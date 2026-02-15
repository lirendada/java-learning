package com.liren.langchain4jdemo.ai.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

public class OrderCalculatorTool {

    @Tool("根据商品单价、数量和折扣计算订单总价")
    public double calculateTotal(@P(value = "单价") double price,
                                 @P(value = "数量") int quantity,
                                 @P(value = "折扣率") double discount) {
        double total = price * quantity * discount;
        return Math.round(total * 100.0) / 100.0;
    }
}
