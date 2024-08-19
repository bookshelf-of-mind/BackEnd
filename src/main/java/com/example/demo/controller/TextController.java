package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TextController {
    @RequestMapping("/main")
    public String submitText(@RequestParam("nick") String nick, Model model) {
        // 입력된 텍스트를 모델에 추가하여 뷰에 전달
        model.addAttribute("nick", nick);
        return "main";
    }
}
