package com.example.springedu.controller;

import com.example.springedu.domain.TeamDTO;
import com.example.springedu.domain.TeamMemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class MyTeamController {
    TeamMemberVO member1 = new TeamMemberVO("홍승희", "삑사리", "돈까스");
    TeamMemberVO member2 = new TeamMemberVO("김민성", "도라이", "마파두부");
    TeamMemberVO member3 = new TeamMemberVO("김대엽", "Danny", "치킨");
    TeamMemberVO member4 = new TeamMemberVO("박주희", "박주", "라면");

    @RequestMapping(value = "/team", produces = "application/json; charset=utf-8")
    @ResponseBody
    public TeamDTO getTeamMember() {
        List<TeamMemberVO> teamList = new ArrayList<>();
        teamList.add(member1);
        teamList.add(member2);
        teamList.add(member3);
        teamList.add(member4);

        TeamDTO team = new TeamDTO();
        team.setTeamName("T-KO");
        team.setTeamMember(teamList);
        return team;
    }
}
