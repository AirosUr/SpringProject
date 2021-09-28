package com.wonderturn.wonderturn.service;

import com.wonderturn.wonderturn.models.Question;
import com.wonderturn.wonderturn.models.User;
import com.wonderturn.wonderturn.repositories.QuestionsRepository;
import com.wonderturn.wonderturn.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImp implements TestService<Question> {

    private QuestionsRepository questsRep2;
    private UsersRepository UserRep;

    @Autowired
    public TestServiceImp(QuestionsRepository questRep2, UsersRepository UserRep){
        this.questsRep2 = questRep2;
        this.UserRep = UserRep;
    }

    public Iterable<Question> get_quests(){
        return this.questsRep2.findAll();
    }

    public void add(String question, String ans1, String ans2, String ans3, String ans4, Integer right_ans) {
        Question questions_portion = new Question(question, ans1, ans2, ans3, ans4, right_ans);
        this.questsRep2.save(questions_portion);
    }

    public void save_result(String name, String surname, Integer score, Integer count) {
        User result = new User(name, surname, score, count);
        this.UserRep.save(result);
    }
}
