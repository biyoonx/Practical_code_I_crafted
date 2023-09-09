package com.biyoonx.quizExercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDO {
	private String question;
	private String content;
	private String answer;

	public QuizDO(String question, String content) {
		this.setQuestion(question);
		this.setContent(content);
	}
}