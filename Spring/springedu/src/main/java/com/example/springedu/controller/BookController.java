package com.example.springedu.controller;

import com.example.springedu.dao.BookMybatisDAO;
import com.example.springedu.domain.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookMybatisDAO dao;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String bookPage() {
        return "bookPage";
    }

    @RequestMapping(value = "/bookinfo/{book}")
    public ModelAndView getBookInfo(@PathVariable String book) {
        List<BookDTO> list = null;
        ModelAndView mav = new ModelAndView();
        
        switch (book) {
            case "b1":
                list = dao.b1(book);
                break;
            case "b2":
                list = dao.b2(book);
                break;
            case "b3":
                list = dao.b3(book);
                break;
            case "b4":
                list = dao.b4(book);
                break;
            case "b5":
                list = dao.b5(book);
                break;
            case "b6":
                list = dao.b6(book, "자바");
                break;
            case "b7":
                list = dao.b7(book, "스프링");
                break;
            case "b8":
                list = dao.b8(book);
                break;
        }

        if(list.size() != 0) {
            mav.addObject("list", list);
        } else {
            mav.addObject("msg", "추출된 데이터가 없네용");
        }

        mav.setViewName("bookPage");
        return mav;
    }
}
