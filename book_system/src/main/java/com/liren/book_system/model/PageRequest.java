package com.liren.book_system.model;

import lombok.Data;

@Data
public class PageRequest {
    private Integer currentPage = 1; // 当前页码，初始为1
    private Integer pageSize = 10;   // 每页显示条数

    // 实际后端需要获取的元素起始偏移量
    public Integer getOffset() {
        return (currentPage - 1) * pageSize;
    }
}
