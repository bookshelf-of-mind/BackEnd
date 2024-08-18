package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class NaverController {
    @RequestMapping("/naverlogin")
        public String Naver() {
            return "naverlogin";
    }

    @RequestMapping("/callback")
    public String callBack(){
        return "callback";
    }
}
