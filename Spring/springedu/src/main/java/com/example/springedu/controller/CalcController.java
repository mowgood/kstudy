package com.example.springedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalcController {
    @PostMapping(value="/calc.do")
    public ModelAndView print_score(@RequestParam(value="firstNum") int firstNum,
                                    @RequestParam(value="operator") String operator,
                                    @RequestParam(value="secondNum") int secondNum) {
        ModelAndView mav = new ModelAndView();
        int result = 0;

        if(operator.equals("divide") && secondNum == 0) {
            mav.addObject("error", "나눗셈 연산시 두 번째 숫자는 0일 수 없습니다!!");
            mav.setViewName("errorResult");
        } else {
            if(operator.equals("divide")) {
                result = firstNum / secondNum;
            } else if(operator.equals("plus")) {
                result = firstNum + secondNum;
            } else if(operator.equals("minus")) {
                result = firstNum - secondNum;
            } else {
                result = firstNum * secondNum;
            }
            mav.addObject("result", result);
            mav.setViewName("calcResult");
        }
        return mav;
    }
}
