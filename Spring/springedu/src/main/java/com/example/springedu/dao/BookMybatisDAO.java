package com.example.springedu.dao;

import com.example.springedu.domain.BookDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookMybatisDAO {

    @Autowired
    SqlSession session;
    public List<BookDTO> b1(String book) {
        List<BookDTO> list = null;
        String statement= "lab1.exam1";
        list = session.selectList(statement);
        return list;
    }
    public List<BookDTO> b2(String book) {
        List<BookDTO> list = null;
        String statement= "lab1.exam2";
        list = session.selectList(statement);
        return list;
    }
    public List<BookDTO> b3(String book) {
        List<BookDTO> list = null;
        String statement= "lab1.exam3";
        list = session.selectList(statement);
        return list;
    }
    public List<BookDTO> b4(String book) {
        List<BookDTO> list = null;
        String statement= "lab1.exam4";
        list = session.selectList(statement);
        return list;
    }
    public List<BookDTO> b5(String book) {
        List<BookDTO> list = null;
        String statement= "lab1.exam5";
        list = session.selectList(statement);
        return list;
    }
    public List<BookDTO> b6(String book, String keyword) {
        List<BookDTO> list = null;
        String statement= "lab1.exam6";
        list = session.selectList(statement, keyword);
        return list;
    }
    public List<BookDTO> b7(String book, String keyword) {
        List<BookDTO> list = null;
        String statement= "lab1.exam6";
        list = session.selectList(statement, keyword);
        return list;
    }
    public List<BookDTO> b8(String book) {
        List<BookDTO> list = null;
        String statement= "lab1.exam7";
        list = session.selectList(statement);
        return list;
    }

    public boolean insert(BookDTO dto) {
        boolean result = true;
        String statement = "lab1.exam8";
        if(session.insert(statement, dto) != 1)
            result = false;
        return result;
    }
}
