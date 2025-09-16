package com.question.services;

import java.util.List;

import com.question.entities.Question;

public interface QuestionService{

    public List<Question> getAllQuestions();

    public Question getQuestionById(Long id);

    public Question addQuestion(Question question);

    public Question updateQuestion(Long id, Question question);

    public Question deleteQuestion(Long id);

    public List<Question> getQuestionsByQuiz(Long quizId);

}
