package com.example.springedu.dao;

import com.example.springedu.domain.BookDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapperDAO {

    @Select("SELECT id, title,  CONCAT(FORMAT(price, 0), '원') bookPrice, kind FROM book")
    public List<BookDTO> b1();

    @Select("SELECT id, title,  CONCAT(FORMAT(price, 0), '원') bookPrice, kind FROM book ORDER BY price DESC")
    public List<BookDTO> b2();

    @Select("SELECT title,  CONCAT(FORMAT(price, 0), '원') bookPrice FROM book where price >= 20000")
    public List<BookDTO> b3();

    @Select("SELECT title,  CONCAT(FORMAT(price, 0), '원') bookPrice FROM book where kind='b02'")
    public List<BookDTO> b4();

    @Select("SELECT title,  CONCAT(FORMAT(price, 0), '원') bookPrice FROM book where kind='b04' OR kind ='b05'")
    public List<BookDTO> b5();

    @Select("SELECT title, CONCAT(FORMAT(price, 0), '원') bookPrice FROM book where title LIKE CONCAT('%',#{keyword},'%')")
    public List<BookDTO> b6(String keyword);

    @Select("SELECT title, CONCAT(FORMAT(price, 0), '원') bookPrice FROM book where title LIKE CONCAT('%',#{keyword},'%')")
    public List<BookDTO> b7(String keyword);

    @Select("SELECT kind, CONCAT(FORMAT(AVG(price), 0), '원') bookPrice FROM book WHERE kind is not null GROUP BY kind")
    public List<BookDTO> b8();

    @Insert("INSERT INTO book (title, price, kind) VALUES (#{title}, #{bookPrice}, #{kind})")
    public boolean insert(BookDTO dto);
}
