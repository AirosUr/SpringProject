package com.wonderturn.wonderturn.service;

import org.springframework.ui.Model;

public interface TesterService<T> {
    void setQuestions(Model model, TesterService test, TestsChangingService tests_data, String test_choice_q);
    void start(Model model, Iterable quests1);
    String work(Model model, T variant, TestServiceImp test_q1_service);

}
