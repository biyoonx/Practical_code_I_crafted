package com.kh.quizExercise;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizController {
	@Autowired
	private QuizService quizService;

	@RequestMapping("/")
	public String toMainPage(Model model) {
		List<QuizDO> quizList = quizService.getAll();
		model.addAttribute("quizList", quizList);
		return "index";
	}

	@RequestMapping("quizSubmitted")
	public String answerSubmit(
			@RequestParam String[] answers,
			@RequestParam String[] contents,
			@RequestParam String[] questions,
			Model model) {
		List<QuizDO> quizList = new ArrayList<QuizDO>();

		List<String> results = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			QuizDO quizToCheck = new QuizDO(questions[i], contents[i], answers[i]);
			quizList.add(quizToCheck);

			if (answers[i] == null || answers[i].isEmpty()) {
				results.add("땡");
				continue;
			}

			int quizResult = quizService.checkAnswer(quizToCheck);

			if (quizResult == 1) {
				results.add("딩동댕");
			} else {
				results.add("땡");
			}
		}
		model.addAttribute("quizList", quizList);
		model.addAttribute("quizResults", results);
		return "index";
	}
}