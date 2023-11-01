package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/member/regist")
    public String registPage() {
        return "member/regist";
    }

    @RequestMapping("/member/login")
    public String loginPage() {
        return "member/login";
    }

    @RequestMapping("/member/password")
    public String passwordCheckPage() {
        return "member/password";
    }

    @RequestMapping("/member/home")
    public String hoemPage() {
        return "member/home";
    }


}
