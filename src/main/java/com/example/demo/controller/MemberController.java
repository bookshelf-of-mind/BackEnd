package com.example.demo.controller;


import com.example.demo.domain.member.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/welcome")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getUsers() {
        return memberService.findAllUsers();
    }

    @PostMapping
    public Member createMember(@RequestBody Member nick) {
        return memberService.saveNickname(nick);
    }
}
