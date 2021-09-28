package com.wonderturn.wonderturn.service;

import com.wonderturn.wonderturn.models.Test;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;

@Service
public class TesterInpBoxService implements TesterService<String> {

    private TestInpBoxDatContain test2;
    private String quest;
    private String name;
    private String surname;
    private int questsCount;
    private int questNum = 1;
    private int rightAnsCount = 0;

    public TesterInpBoxService() {
    }

    public TesterInpBoxService(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setQuestions(Model model, TesterService test, TestsChangingService testsData, String testQuestChoice) {
        test2 = new TestInpBoxDatContain();
        Iterable<Test> tests1 = testsData.get_tests();
        tests1.forEach((element) -> {
                    if (element.getTest_name().equals(testQuestChoice)) {
                        element.getQuestions().forEach((element2) -> {
                            switch (element2.getRight_ans()) {
                                case 1:
                                    test2.setQuestions(questNum, element2.getQuestion(), element2.getAns1());
                                    break;
                                case 2:
                                    test2.setQuestions(questNum, element2.getQuestion(), element2.getAns2());
                                    break;
                                case 3:
                                    test2.setQuestions(questNum, element2.getQuestion(), element2.getAns3());
                                    break;
                                case 4:
                                    test2.setQuestions(questNum, element2.getQuestion(), element2.getAns4());
                                    break;
                            }
                            questNum += 1;
                        });
                    }
                });
        test.start(model, tests1);
    }

    public void start(Model model, Iterable quests1) {
        questNum = 1;
        questsCount = test2.getQuestsCount();
        model.addAttribute("qCount", questsCount);
        quest = test2.getQuestions(questNum);
        model.addAttribute("question", quest);
    }


    public String work(Model model, String variant, TestServiceImp testData) {
        if (test2.getRightAnswer(questNum) == variant) {
            rightAnsCount +=1;
        }
        if (questNum >= questsCount) {
            model.addAttribute("r_qCount", rightAnsCount);
            model.addAttribute("qCount", questsCount);

            testData.save_result(name, surname, rightAnsCount, questsCount);
            return "result";
        }
        questNum +=1;
        quest = test2.getQuestions(questNum);
        model.addAttribute("question", quest);
        return "tester2";
    }

}

class TestInpBoxDatContain {
    private HashMap<Integer, String> quests = new HashMap<Integer, String>();
    private HashMap<Integer, String> right_ans = new HashMap<Integer, String>();

    public void setQuestions(Integer quest_num, String question, String rAns) {
        quests.put(quest_num, question);
        right_ans.put(quest_num, rAns);
    }

    public String getQuestions(int quest_num) {
        String quest = quests.get(quest_num);
        return quest;
    }

    public String getRightAnswer(int quest_num) {
        String rgh_ans = right_ans.get(quest_num);
        return rgh_ans;
    }

    public int getQuestsCount() {
        return quests.size();
    }
}



