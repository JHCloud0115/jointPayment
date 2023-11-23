package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.StringJoiner;

@Controller
public class MainController {

<<<<<<< Updated upstream
    @RequestMapping("/member/regist")
    public String registPage() {
        return "/member/regist";
    }

    @RequestMapping("/member/login")
    public String loginPage() {
        return "/member/login";
    }

    @RequestMapping("/member/password")
    public String passwordCheckPage() {
        return "/member/password";
    }

    @RequestMapping("/member/home")
    public String hoemPage() {
        return "/member/home";
    }

    @RequestMapping("/auth/logout")
    public String logout(){return "/auth/logout";}


=======
    private static final String PAGE = "{page}";
<<<<<<< Updated upstream
    private static final String DEPTH = "/{depth}";
=======

    private static final String DEPTH = "/{depth}";


    @GetMapping(PAGE + DEPTH)
    public String pageDepth(@PathVariable("page") String page
    , @PathVariable("depth") String depth){
        StringJoiner stringJoiner = new StringJoiner("/");
        return stringJoiner.add(page)
                .add(depth)
                .toString();
    }

    /*
    @RequestMapping("/member/regist")
    public String registPage() {
        return "/member/regist";
    }

    @RequestMapping("/member/login")
    public String loginPage() {
        return "/member/login";
    }
>>>>>>> Stashed changes

    @GetMapping(PAGE + DEPTH)
    public String pageDepth(@PathVariable("page") String page, @PathVariable("depth") String depth){
        return page + depth;
    }

<<<<<<< Updated upstream
=======
    @RequestMapping("/member/home")
    public String hoemPage() {
        return "/member/home";
    }*/



>>>>>>> Stashed changes
>>>>>>> Stashed changes
}
