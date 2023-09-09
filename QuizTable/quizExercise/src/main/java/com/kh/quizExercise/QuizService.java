package com.kh.quizExercise;

import java.util.List;

public interface QuizService {
	public List<QuizDO> getAll();
	public int checkAnswer(QuizDO answer); // abstract 안붙여도 되지 않나
}