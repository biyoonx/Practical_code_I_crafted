package com.biyoonx.quizExercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizDAO quizDAO;

	@Override
	public List<QuizDO> getAll() {
		return quizDAO.getAll();
	}

	@Override
	public int checkAnswer(QuizDO answer) {
		if (answer == null) {
			return 0;
		}
		return quizDAO.checkAnswer(answer);
	}
}