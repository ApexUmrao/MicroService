package com.quiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/quizTest")
public class TestController {

    @GetMapping
    public String print() {
        return "This is Success Test Controller for Quiz Service";
    }
}
