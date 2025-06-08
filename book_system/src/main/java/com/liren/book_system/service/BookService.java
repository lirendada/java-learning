package com.liren.book_system.service;

import com.liren.book_system.enums.BookStatusEnum;
import com.liren.book_system.mapper.*;
import com.liren.book_system.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {
    @Autowired
    private BookInfoMapper bookInfoMapper;

    public void addBook(BookInfo bookInfo) {
        bookInfoMapper.insertBook(bookInfo);
    }

    public PageResult getBookByPage(PageRequest req) {
        // 1. 获取元素
        List<BookInfo> books = bookInfoMapper.selectBookByPage(req.getPageSize(), req.getOffset());
        for(BookInfo book : books) {
            book.setStatusCN(BookStatusEnum.getStatusByCode(book.getStatus()));
        }

        // 2. 获取总页数
        Integer total = bookInfoMapper.selectTotalBook();

        // 3. 打包成PageResult返回
        return new PageResult(total, books, req);
    }

    public BookInfo getBookById(Integer id) {
        return bookInfoMapper.selectBookById(id);
    }

    public void updateBook(BookInfo bookInfo) {
        bookInfoMapper.updateBook(bookInfo);
    }

    public void batchDeleteBook(Integer[] ids) {
        bookInfoMapper.deleteBatchBook(ids);
    }
}
