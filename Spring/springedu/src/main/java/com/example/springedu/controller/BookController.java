package com.example.springedu.controller;

import com.example.springedu.dao.BookMapperDAO;
import com.example.springedu.dao.BookMybatisDAO;
import com.example.springedu.domain.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

//    @Autowired
//    BookMybatisDAO dao;

    @Autowired
    BookMapperDAO dao;

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
                list = dao.b1();
                break;
            case "b2":
                list = dao.b2();
                break;
            case "b3":
                list = dao.b3();
                break;
            case "b4":
                list = dao.b4();
                break;
            case "b5":
                list = dao.b5();
                break;
            case "b6":
                list = dao.b6("자바");
                break;
            case "b7":
                list = dao.b7("스프링");
                break;
            case "b8":
                list = dao.b8();
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

    @GetMapping("/bookCreate")
    public String bookForm() {
        return "bookCreatePage";
    }

    @PostMapping("/bookCreate")
    public ModelAndView insert(BookDTO dto) {
        boolean result = dao.insert(dto);
        ModelAndView mav = new ModelAndView();
        if(result) {
            mav.addObject("book", dto); // 객체에 담지 않아도 view에서 BookDTO로 데이터를 꺼내올 수 있다.
        }
        mav.setViewName("bookCreatePage");
        return mav;
    }
}
