package interfaces.implementation;

import interfaces.GradeSystem;

public class ForRequiredSubject implements GradeSystem {
	private ForRequiredSubject() {}
	private static ForRequiredSubject instance = new ForRequiredSubject();
	public static ForRequiredSubject getInstance() {
		return instance;
	}
	
	@Override
	public String getGrade(int score) {
		String grade = null;
		if (score == -1) {
			System.out.println("점수가 아직 입력되지 않았습니다.");
		} else if (score > 100 || score < 0) {
			System.out.println("점수는 0 ~ 100점 사이의 정수만 가능");
		} else if (100 >= score && score >= 95) {
			grade = "S";
		} else if (score >= 90) {
			grade = "A";
		} else if (score >= 80) {
			grade = "B";
		} else if (score >= 70) {
			grade = "C";
		} else if (score >= 60) {
			grade = "D";
		} else {
			grade = "F";
		}
		return grade;
	}
}
//enum GradeR {
//	S("S", IntStream.rangeClosed(95, 100).toArray()),
//	A("A", IntStream.rangeClosed(90, 94).toArray()),
//	B("B", IntStream.rangeClosed(80, 89).toArray()),
//	C("C", IntStream.rangeClosed(70, 79).toArray()),
//	D("D", IntStream.rangeClosed(60, 69).toArray()),
//	F("F", IntStream.rangeClosed(0, 59).toArray());
//
//	GradeR(String string, int[] array) {}
//	public static String conValue(int score) {
//		for (GradeR grade : GradeR.values()) {
//			List<GradeR> gradeRList = new ArrayList<GradeR>(Arrays.asList(grade));
//			if (gradeRList.contains(score)) {
//				return grade.name();
//			}
//		}
//		System.out.println("점수는 0 ~ 100점 사이의 정수만 가능");
//		return null;
//	}
//}