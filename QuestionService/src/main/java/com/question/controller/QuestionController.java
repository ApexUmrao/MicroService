package com.question.controller;

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

import com.question.entities.Question;
import com.question.services.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Get all questions
    @GetMapping("/getQuestion")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // Get question by id
    @GetMapping("/getQuestion/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    // Get questions by quiz id
    @GetMapping("/getQuiz/{quizId}")
    public List<Question> getQuestionsByQuizId(@PathVariable Long quizId) {
        return questionService.getQuestionsByQuiz(quizId);
    }

    // Add question
    @PostMapping("/addQuestion")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // Update question
    @PutMapping("/updateQuestion/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

    // Delete question
    @DeleteMapping("/deleteQuestion/{id}")
    public Question deleteQuestion(@PathVariable Long id) {
        return questionService.deleteQuestion(id);
    }
}
