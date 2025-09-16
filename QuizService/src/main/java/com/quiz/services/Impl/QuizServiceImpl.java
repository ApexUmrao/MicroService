package com.quiz.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quiz.entity.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

    QuizRepository quizRepository;

    QuestionClient questionClient;

    QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient = questionClient;
    }
    //Both Injection of QuizRepository and QuestionClient
    //Constructor for QuizServiceImpl constructor --Injection of QuizRepository

    @Override
    public Quiz geQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        quiz.setQuestions(questionClient.getQuestionsByQuizId(id)); 
        return quiz;
    }

    @Override
    public List<Quiz> getAllQuizzes() {
       List<Quiz> quizzes = quizRepository.findAll();

       List<Quiz> quizNewList = quizzes.stream().map(quiz -> {
           quiz.setQuestions(questionClient.getQuestionsByQuizId(quiz.getId()));
           return quiz;
       }).collect(Collectors.toList());

           return quizNewList;

    }

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz deleteQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow();
        quizRepository.deleteById(id);
        return quiz;
    }

    @Override
    public Quiz updateQuiz(Long id, Quiz updatedquiz) {
     Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
       quiz.setTitle(updatedquiz.getTitle());
        return quizRepository.save(quiz);  
    }

   

}
