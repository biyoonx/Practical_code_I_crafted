package com.kh.quizExercise;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// Component, Repository, Service 뭐든 상관없음
@Repository
public class QuizDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<QuizDO> getAll() {
		return sqlSession.selectList("quizMapper.getAll");
	}
	public int checkAnswer(QuizDO answer) {
		return sqlSession.selectOne("quizMapper.checkAnswer", answer);
	}
}