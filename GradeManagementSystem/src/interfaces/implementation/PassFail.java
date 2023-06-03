package interfaces.implementation;

import interfaces.GradeSystem;

public class PassFail implements GradeSystem {
	private PassFail () {}
	private static PassFail instance = new PassFail();
	public static PassFail getInstance() {
		return instance;
	}

	@Override
	public String getGrade(int score) {
		String grade = null;
		if (score == -1) {
			System.out.println("점수가 아직 입력되지 않았습니다.");
		} else if (score > 100 || score < 0) {
			System.out.println("점수는 0 ~ 100점 사이의 정수만 가능");
		} else if (100 >= score && score >= 70) {
			grade = "P";
		} else {
			grade = "F";
		}
		return grade;
	}
}
