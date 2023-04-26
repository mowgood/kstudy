package com.example.springedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudyPathController {
    @RequestMapping(value = "/study/{num}/thymeleaf", produces="text/html; charset=utf-8")
    public String moveThymeleaf(@PathVariable int num) {
        String url = "";
        switch(num) {
            case 1:
                url = "https://abbo.tistory.com/56";
                break;
            case 2:
                url = "https://abbo.tistory.com/57";
                break;
            case 3:
                url = "https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html";
                break;
            case 4:
                url = "https://www.baeldung.com/dates-in-thymeleaf";
                break;
        }
        return "redirect:" + url;
    }
}
