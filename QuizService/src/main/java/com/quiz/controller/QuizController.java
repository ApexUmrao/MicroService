package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.entity.Quiz;
import com.quiz.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController  {

    @Autowired
    QuizService quizService;

    QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    //Get all quizzes
    @GetMapping("/getQuiz")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    //Get quiz by id
    @GetMapping("/getQuiz/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.geQuizById(id);
    }

    //Add quiz
    @PostMapping("/addQuiz")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    //Update quiz
    @PutMapping("/updateQuiz/{id}")
    public Quiz updateQuiz(@PathVariable Long id, @RequestBody Quiz quiz) {
        return quizService.updateQuiz(id, quiz);
    }

    //Delete quiz
    @DeleteMapping("/deleteQuiz/{id}")
    public Quiz deleteQuiz(@PathVariable Long id) {
        return quizService.deleteQuiz(id);
    }

}
