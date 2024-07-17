package com.example.demo.src.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/login/oauth2/code/google")
    public String loginWait() {
        return "/wait";
    }
}
