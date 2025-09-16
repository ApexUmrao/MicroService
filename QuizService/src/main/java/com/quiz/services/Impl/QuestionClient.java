package com.quiz.services.Impl;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.entity.Question;

//@FeignClient(url = "http://localhost:9092", value = "Question-Service")
@FeignClient(name = "QuestionService")
public interface QuestionClient {

    @RequestMapping("/question/getQuiz/{quizId}")
    List<Question>  getQuestionsByQuizId(@PathVariable Long quizId);
}
