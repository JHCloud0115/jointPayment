package org.example.controller;

import org.example.model.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/member")
public class MemberController {

    @Autowired
    public MemberController() {

    }

    @GetMapping
    public CommonResponse<Void> selectMember() throws Exception {

        return new CommonResponse<>();
    }

}
