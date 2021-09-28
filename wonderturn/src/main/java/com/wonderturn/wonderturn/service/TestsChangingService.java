package com.wonderturn.wonderturn.service;

import com.wonderturn.wonderturn.models.Question;
import com.wonderturn.wonderturn.models.Test;
import com.wonderturn.wonderturn.repositories.TestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestsChangingService {

    private TestsRepository test2Rep;

    @Autowired
    public TestsChangingService(TestsRepository test2Rep){
        this.test2Rep = test2Rep;
    }

    public Iterable<Test> get_tests(){
        return this.test2Rep.findAll();
    }

    public void add_test(String name, List<Question> quests) {
        Test test_portion = new Test(name, quests);
        this.test2Rep.save(test_portion);
    }

}