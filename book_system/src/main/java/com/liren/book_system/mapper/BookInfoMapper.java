package com.liren.book_system.mapper;

import com.liren.book_system.model.BookInfo;
import org.apache.ibatis.annotations.Mapper;

import java.awt.print.Book;
import java.util.List;

@Mapper
public interface BookInfoMapper {
    Integer insertBook(BookInfo bookInfo);

    List<BookInfo> selectBookByPage(Integer pageSize, Integer offset);

    Integer selectTotalBook();

    BookInfo selectBookById(Integer id);

    Integer updateBook(BookInfo bookInfo);

    Integer deleteBatchBook(Integer[] ids);
}
