package interfaces.implementation;

import interfaces.GradeSystem;

public class General implements GradeSystem {
	private General() {}
	private static General instance = new General();
	public static General getInstance() {
		return instance;
	}
	
	@Override
	public String getGrade(int score) {
		String grade = null;
		if (score == -1) {
			System.out.println("점수가 아직 입력되지 않았습니다.");
		} else if (score > 100 || score < 0) {
			System.out.println("점수는 0 ~ 100점 사이의 정수만 가능");
		} else if (100 >= score && score >= 90) {
			grade = "A";
		} else if (score >= 80) {
			grade = "B";
		} else if (score >= 70) {
			grade = "C";
		} else if (score >= 55) {
			grade = "D";
		} else {
			grade = "F";
		}
		return grade;
	}
}
