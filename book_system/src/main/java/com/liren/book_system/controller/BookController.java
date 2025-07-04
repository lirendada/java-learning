package com.liren.book_system.controller;

import com.liren.book_system.constant.Constants;
import com.liren.book_system.model.*;
import com.liren.book_system.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/getListByPage")
    public Result getListByPage(PageRequest pageRequest) {
        log.info("获取图书，PageRequest：{}", pageRequest);

        PageResult<BookInfo> pageResult = bookService.getBookByPage(pageRequest);
        return Result.success(pageResult);
    }

    @PostMapping("/addBook")
    public Result addBook(BookInfo bookInfo) {
        log.info("添加图书，bookInfo：{}", bookInfo);
        if(!StringUtils.hasLength(bookInfo.getBookName()) ||
            !StringUtils.hasLength(bookInfo.getAuthor()) ||
            bookInfo.getCount() == null || bookInfo.getCount() <= 0 ||
            bookInfo.getPrice() == null || bookInfo.getPrice().compareTo(BigDecimal.ZERO) <= 0 ||
            !StringUtils.hasLength(bookInfo.getPublish()) ||
            bookInfo.getStatus() == null) {
            log.warn("添加图书参数不合法，bookInfo：{}", bookInfo);
            return Result.fail("参数有误，请检查书籍信息~");
        }

        try {
            bookService.addBook(bookInfo);
            return Result.success(""); // 不抛异常则走到这里
        } catch (Exception e) {
            log.error("添加书籍失败，请重新尝试，e：{}", e);
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/queryBookById")
    public Result queryBookById(Integer bookId) {
        log.info("queryBookById: " + bookId);
        if(bookId == null || bookId <= 0) {
            return null;
        }
        return Result.success(bookService.getBookById(bookId));
    }

    @PostMapping("/updateBook")
    public Result updateBook(BookInfo bookInfo) {
        log.info("updateBook: " + bookInfo);
        try {
            bookService.updateBook(bookInfo);
            return Result.success("");
        } catch(Exception e) {
            log.error("更新图书失败，e：", e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/batchDeleteBook")
    public Result batchDeleteBook(Integer[] ids) {
        log.info("batchDeleteBook: " + ids);
        if(ids == null) {
            return Result.fail("列表为空，请重新选择要删除的数据~");
        }
        try {
            bookService.batchDeleteBook(ids);
            return Result.success("");
        } catch (Exception e) {
            log.error("批量删除图书失败，e：", e);
            return Result.fail(e.getMessage());
        }
    }
}
