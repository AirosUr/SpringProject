package com.wonderturn.wonderturn.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String Test_name;

    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Question> questions = new ArrayList<Question>();

    public String getTest_name() {
        return Test_name;
    }

    public void setTest_name(String test_name) {
        Test_name = test_name;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Test(String test_name, List<Question> quests_list) {
        this.Test_name = test_name;
        this.questions = quests_list;
    }

    public Test() {
    }

    public List<Question> getQuestions() {
        return questions;
    }


}
