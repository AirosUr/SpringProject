package com.wonderturn.wonderturn.controllers;

import com.wonderturn.wonderturn.models.Test;
import com.wonderturn.wonderturn.service.TestsChangingService;
import com.wonderturn.wonderturn.service.TestServiceImp;
import com.wonderturn.wonderturn.service.TesterService;
import com.wonderturn.wonderturn.service.TesterRadioService;
import com.wonderturn.wonderturn.service.TesterInpBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GeneralController {

    private TesterService test;
    private TestServiceImp testData;
    private TestsChangingService test2;

    @Autowired
    public GeneralController(TestsChangingService test2, TestServiceImp testData) {
        this.testData = testData;
        this.test2 = test2;
    }

    @GetMapping("/")
    public String login_form(Model model) {
        test = null;
        Iterable<Test> test2Iterable = test2.get_tests();
        model.addAttribute("test_variants", test2Iterable);
        return "login";
    }

    @PostMapping("/")
    public RedirectView login(RedirectAttributes redirectAttributes,
                              @RequestParam("test_choice") String test_choice,
                              @RequestParam("name") String name,
                              @RequestParam("surname") String surname,
                              @RequestParam("test_choice2") String test_choice_q,
                              Model model) {
        RedirectView redirectView = new RedirectView();
        redirectAttributes.addFlashAttribute("name", name);
        redirectAttributes.addFlashAttribute("surname", surname);
        redirectAttributes.addFlashAttribute("test_choice", test_choice);
        redirectAttributes.addFlashAttribute("test_choice_q", test_choice_q);
        redirectView.setUrl("/" + test_choice);
        return redirectView;
    }

    @GetMapping("/test1")
    public String tester(@ModelAttribute("name") String name, @ModelAttribute("surname") String surname, @ModelAttribute("test_choice_q") String test_choice_q, Model model) {
        test = new TesterRadioService(name, surname);
        test.setQuestions(model, test, test2, test_choice_q);
        return "tester1";
    }


    @PostMapping("/test1")
    public String ToNextQuest(@RequestParam("AnsChoice") Integer variant, Model model) {
        return (test.work(model, variant, testData));
    }

    @GetMapping("/test2")
    public String tester2(@ModelAttribute("name") String name, @ModelAttribute("surname") String surname, @ModelAttribute("test_choice_q") String test_choice_q, Model model) {
        test = new TesterInpBoxService(name, surname);
        test.setQuestions(model, test, test2, test_choice_q);
        return "tester2";
    }

    @PostMapping("/test2")
    public String ToNextQuest2(@RequestParam("answer") String answer, Model model) {
        return (test.work(model, answer, testData));
    }

    @GetMapping("/change")
    public String changer(@ModelAttribute("name") String name, @ModelAttribute("questsCount") String questsCount,Model model) {
        model.addAttribute("test_name", name);
        model.addAttribute("test_questsCount", questsCount);
        return "test_change2";
    }

    @PostMapping("/change")
    public String change(@RequestParam("question") String question,
                         @RequestParam("ans1") String ans1,
                         @RequestParam("ans2") String ans2,
                         @RequestParam("ans3") String ans3,
                         @RequestParam("ans4") String ans4,
                         @RequestParam("right_ans") Integer right_ans, Model model) {

        testData.add(question, ans1, ans2, ans3, ans4, right_ans);
        return "test_change2";
    }

    @GetMapping("/changeCh")
    public String changeChoicer(Model model) {
        Iterable<Test> test2Iterable = test2.get_tests();
        model.addAttribute("test_variants", test2Iterable);
        return "test_change_choices";
    }

    @PostMapping("/changeCh")
    public RedirectView changeChoice(RedirectAttributes redirectAttributes,
                                     @RequestParam("name") String name,
                                     @RequestParam("questsCount") String questsCount,
                                     Model model) {
        RedirectView redirectView = new RedirectView();
        redirectAttributes.addFlashAttribute("name", name);
        redirectAttributes.addFlashAttribute("questsCount", questsCount);
        redirectView.setUrl("change");
        return redirectView;
    }

    public GeneralController() {

    }

}