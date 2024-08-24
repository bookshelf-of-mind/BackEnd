package com.example.demo.controller;

import com.example.demo.domain.member.Member;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.demo.domain.member.User;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/main")
    public String createUser(@RequestParam("lastnick") String lastnick, User user) {
        user.setNick(lastnick);
        userService.saveUser(user);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String getLastNick(Model model) {
        User lastUser = userService.findLastUser();

        // 마지막 유저가 존재할 경우 nick을 모델에 추가
        if (lastUser != null) {
            model.addAttribute("lastnick", lastUser.getNick());
        }

        return "main";
    }
}
