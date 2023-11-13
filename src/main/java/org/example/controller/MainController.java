package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/member/regist")
    public String registPage() {
        return "member/regist";
    }

    @RequestMapping("/auth/logIn")
    public String loginPage() {
        return "auth/login";
    }

    @RequestMapping("/member/password")
    public String passwordCheckPage() {
        return "member/password";
    }

    @RequestMapping("/member/home")
    public String hoemPage() {
        return "member/home";
    }

    @RequestMapping("/auth/logout")
    public String logout(){return "/auth/logout";}


}
