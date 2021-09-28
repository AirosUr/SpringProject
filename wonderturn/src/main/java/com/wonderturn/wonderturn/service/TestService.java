package com.wonderturn.wonderturn.service;

public interface TestService<T> {
    public Iterable<T> get_quests();
    public void add(String question, String ans1, String ans2, String ans3, String ans4, Integer right_ans);
    public void save_result(String name, String surname, Integer score, Integer count);
}
