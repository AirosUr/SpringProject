package com.wonderturn.wonderturn.service;

import com.wonderturn.wonderturn.models.Test;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;

@Service
public class TesterRadioService implements TesterService<Integer> {

    private TestRadioDatContain test1;
    private String[] quest;
    private int questsCount;
    private String name;
    private String surname;
    private int questNum = 1;
    private int rightAnsCount = 0;


    public TesterRadioService() {
    }

    public TesterRadioService(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }


    public void setQuestions(Model model, TesterService test, TestsChangingService testsData, String testQuestChoice) {
        test1 = new TestRadioDatContain();
        Iterable<Test> tests1 = testsData.get_tests();
        tests1.forEach((element) -> {
                    if (element.getTest_name().equals(testQuestChoice)) {
                        element.getQuestions().forEach((element2) -> {
                            test1.setQuests(questNum, element2.getQuestion(), element2.getAns1(), element2.getAns2(), element2.getAns3(), element2.getAns4(), element2.getRight_ans());
                            questNum +=1;
                        });

                    }
                }
        );
        test.start(model, tests1);
    }

    public void start(Model model, Iterable quests1) {
        questNum = 1;
        questsCount = test1.getQuestsCount();
        quest = test1.getQuest(questNum);
        for (int iline = 0; iline < quest.length; iline++) {
            model.addAttribute("line" + String.valueOf(iline), quest[iline]);
        }
    }


    public String work(Model model, Integer variant, TestServiceImp testData) {
        if (test1.getRightAns(questNum) == variant) {
            rightAnsCount +=1;
        }
        if (questNum >= questsCount) {
            model.addAttribute("r_qCount", rightAnsCount);
            model.addAttribute("qCount", questsCount);
            testData.save_result(name, surname, rightAnsCount, questsCount);

            return "result";
        }
        questNum +=1;
        quest = test1.getQuest(questNum);
        for (int iline = 0; iline < quest.length; iline++) {
            model.addAttribute("line" + String.valueOf(iline), quest[iline]);
        }
        return "tester1";
    }

}

class TestRadioDatContain {
    private HashMap<Integer, String[]> quests = new HashMap<Integer, String[]>();
    private HashMap<Integer, Integer> rightAnswers = new HashMap<Integer, Integer>();

    public void setQuests(Integer quest_num, String question, String ans1, String ans2, String ans3, String ans4, Integer rAns) {
        quests.put(quest_num, new String[]{question, ans1, ans2, ans3, ans4});
        rightAnswers.put(quest_num, rAns);
    }

    public String[] getQuest(int questNum) {
        String[] quest = quests.get(questNum);
        return quest;
    }

    public Integer getRightAns(int quest_num) {
        Integer rgh_ans = rightAnswers.get(quest_num);
        return rgh_ans;
    }

    public int getQuestsCount() {
        return quests.size();
    }
}