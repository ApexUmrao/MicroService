package com.question.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.question.entities.Question;
import com.question.repositories.QuestionRepo;
import com.question.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    QuestionRepo questionRepo;

    QuestionServiceImpl(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepo.findById(id).orElseThrow();
    }

    @Override
    public Question addQuestion(Question question) {
            return questionRepo.save(question);
    }

    @Override
    public Question updateQuestion(Long id, Question question) {
        Question questionToUpdate = questionRepo.findById(id).orElseThrow();
        questionToUpdate.setQuestion(question.getQuestion());
        questionToUpdate.setQuizId(question.getQuizId());
        return questionRepo.save(questionToUpdate);
    }

    @Override
    public Question deleteQuestion(Long id) {
        Question question = questionRepo.findById(id).orElseThrow();
        questionRepo.deleteById(id);
        return question;   
    }

    @Override
    public List<Question> getQuestionsByQuiz(Long quizId) {
        return questionRepo.findByQuizId(quizId);
    }


}
