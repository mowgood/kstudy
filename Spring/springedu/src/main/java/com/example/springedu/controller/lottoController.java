package com.example.springedu.controller;

import com.example.springedu.domain.LottoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class lottoController {
    @RequestMapping(value="/lotto")
    public String lotto(HttpSession s, @ModelAttribute("lottoResult")LottoDTO vo1, int lottoNum) {
        if(s.getAttribute("count") == null) {
            s.setAttribute("count", 0);
        }
        if((int)s.getAttribute("count") == 3) {
            vo1.setLottoNum(lottoNum);
            vo1.setResult("로또 응모는 낙첨된 경우에 한하여 3번 까지만 가능합니다. <br> 브라우저를 재기동한 후에 응모해 주세요");
            vo1.setImgName("../images/tube.jpg");
        } else {
            int num = (int) (Math.random() * 6) + 1;
            if(lottoNum == num) {
                vo1.setLottoNum(lottoNum);
                vo1.setResult("추카추카!!");
                vo1.setImgName("../images/jayg.jpg");
                s.setAttribute("count", 3);
            } else {
                vo1.setLottoNum(lottoNum);
                vo1.setResult("아쉽네요 .. 다음 기회를!!");
                vo1.setImgName("../images/ryan.jpg");
                s.setAttribute("count", (int)s.getAttribute("count")+1);
            }
        }
        return "lottoView";
    }
}
