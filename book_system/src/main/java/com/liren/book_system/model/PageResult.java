package com.liren.book_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    private Integer total;          // 总记录数
    private List<BookInfo> records; // 所查询到的数据列表

    private PageRequest pageRequest;
}
