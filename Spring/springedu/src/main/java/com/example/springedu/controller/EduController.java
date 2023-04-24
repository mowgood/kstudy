package com.example.springedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EduController {
    @PostMapping(value="/educontroller")
    public ModelAndView print_score(@RequestParam(value="name") String name,
                                    @RequestParam(value="avgScore") int avgScore) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", name);

        if(avgScore >= 90) {
            mav.setViewName("gradeA");
        } else if(80 <= avgScore && avgScore <= 89) {
            mav.setViewName("gradeB");
        } else if(70 <= avgScore && avgScore <= 79) {
            mav.setViewName("gradeC");
        } else {
            mav.setViewName("gradeD");
        }
        return mav;
    }
}
