package com.online.exam.helper;

import com.online.exam.model.Question;
import com.online.exam.model.TitleComparator;
import com.online.exam.model.User;
import com.online.exam.repo.QuestionRepo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


public class HelperClass {

    private QuestionRepo questionRepo;
    private User user;
    MergeSort mergeSort;


    public HelperClass(QuestionRepo questionRepo,User user) {
        this.questionRepo = questionRepo;
        this.user=user;
    }

    public List<Question> findAllQuestionByUser(User user
    ) {
        return this.questionRepo.findByUser(user);
    }
    public List<Question> generateRandomQuestion(List<Question> questions, int questionLimitSize) throws Exception {
        List<Question> randomGeneratedQuestion = new ArrayList<>(questionLimitSize);
            outerLoop:
            for (int i = 0; i < questions.size(); i++) {

                int result = (int) (Math.random() * questions.size());
                //Random random=new Random();//implementation of mersenne twister algorithm
                //int result= random.nextInt(questions.size());
                if (randomGeneratedQuestion.contains(questions.get(result)) == true) {
                    continue;
                }
                randomGeneratedQuestion.add(questions.get(result));
                if (randomGeneratedQuestion.size() == questionLimitSize) {
                    break outerLoop;
                }


            }
            return randomGeneratedQuestion;

    }


    public List<Question> generateSortedQuestion(List<Question> questions, int questionLimitSize) throws Exception {
        List<Question> sortedGeneratedQuestion = new ArrayList<>(questionLimitSize);
        HelperClass helperClass=new HelperClass(questionRepo,user);
        if(helperClass.findAllQuestionByUser(user).size()>=questionLimitSize) {
            if (questionRepo.findBySelected(true).isEmpty()) {
                sortedGeneratedQuestion.addAll(setSortedQuestion(questions, questionLimitSize));
            } else {
             if (questionRepo.countQuestionBySelectedFlag(false) <= questionLimitSize) {
                    List<Question> retrievedQuestion = questionRepo.findBySelected(false);
                    sortedGeneratedQuestion.addAll(setSortedQuestion(retrievedQuestion, questionLimitSize));
                } else if (questionRepo.countQuestionBySelectedFlag(true) == questionRepo.findAll().size()) {
                    List<Question> questions1 = questionRepo.findAll();
                    for (Question eachQuestion : questions1
                    ) {
                        eachQuestion.setSelected(false);
                        questionRepo.save(eachQuestion);
                    }
                    sortedGeneratedQuestion.addAll(setSortedQuestion(questions1, questionLimitSize));
                }
            }
        }else {
            throw new Exception("invalid questionDisplayLimit");
        }
        return sortedGeneratedQuestion;

    }
    private List<Question> setSortedQuestion(List<Question> questions,int questionLimit){
        List<Question> resultSortedQuestion=new ArrayList<>();
        List<Question> sortedQuestion=mergeSort.mergeSort(questions,new TitleComparator());
        for (int i=0;i<questionLimit;i++){
            resultSortedQuestion.add(sortedQuestion.get(i));
            Question question=sortedQuestion.get(i);
            question.setSelected(true);
            questionRepo.save(question);
        }
        return resultSortedQuestion;
    }


    }




