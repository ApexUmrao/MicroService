package com.quiz.services;

import java.util.List;

import com.quiz.entity.Quiz;

public interface QuizService {

    Quiz geQuizById(Long id);

    List<Quiz> getAllQuizzes();

    Quiz addQuiz(Quiz quiz);

    Quiz updateQuiz(Long id, Quiz quiz);

    Quiz deleteQuiz(Long id);

}
