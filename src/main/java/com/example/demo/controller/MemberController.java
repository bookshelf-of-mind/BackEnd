package com.example.demo.controller;


import com.example.demo.domain.member.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/main")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member createMember(@RequestParam("nick") String nick, Member member) {
        member.setNick(nick);
        return memberService.saveMember(member);
    }

    @GetMapping
    public String getUsers(Model model) {
        List<Member> members = memberService.findAllUsers();
        model.addAttribute("members", members);
        return "main";
    }
}
