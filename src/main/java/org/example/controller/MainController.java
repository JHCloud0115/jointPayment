package org.example.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.StringJoiner;

@Controller
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
public class MainController {

    private static final String PAGE = "{page}";
    private static final String DEPTH = "/{depth}";

    @GetMapping(PAGE + DEPTH)
    public String pageDepth(@PathVariable("page") String page
    , @PathVariable("depth") String depth){
        StringJoiner stringJoiner = new StringJoiner("/");
        return stringJoiner.add(page)
                .add(depth)
                .toString();
    }

}

