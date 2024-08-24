package com.example.demo.controller;


import com.example.demo.domain.member.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;
    private Model model;

//    @PostMapping("/main")
//    public Member createMember(@RequestParam("nick") String nick, Member member) {
//        member.setNick(nick);
//        return memberService.saveMember(member);
//    }

    @RequestMapping("/send")
    public String send() {
        return "send";
    }

    @RequestMapping("/ok")
    public String ok() {
        return "ok";
    }

    @PostMapping("/ok")
    public Member createMember2(@RequestParam("nick") String nick, @RequestParam("book") String book, @RequestParam("message") String message, Member member) {
        member.setBook(book);
        member.setMessage(message);
        member.setNick(nick);
        return memberService.saveMember(member);
    }

    @GetMapping("/check")
    public String getUsers(Model model) {
        List<Member> members = memberService.findAllUsers();
        model.addAttribute("members", members);
        return "check";
    }

    @RequestMapping("/main")
    public String submitText(@RequestParam("nick") String nick, Model model) {
        model.addAttribute("nick", nick);
        return "main";
    }
}
