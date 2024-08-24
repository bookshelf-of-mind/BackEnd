package com.example.demo.controller;


import com.example.demo.domain.member.Member;
import com.example.demo.repositroy.UserRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserService userService;


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
    public String getMembers(Model model) {
        System.out.println("UserService: " + userService);    // 로그 추가
        System.out.println("MemberService: " + memberService); // 로그 추가

        String lastNick = userService.findLastNick();
        if (lastNick != null) {
            List<Member> members = memberService.findMembersByNick(lastNick);
            model.addAttribute("members", members);
        } else {
            model.addAttribute("members", new ArrayList<>()); // 빈 리스트 추가
        }
        return "check";
    }
}
