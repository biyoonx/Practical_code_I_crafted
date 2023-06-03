package classes;

import java.util.Calendar;
import java.util.HashMap;

import interfaces.implementation.General;

public class Student {
	private String studentName;
	private static int serialNum = 10000;
	private int studentNum;
	private Major major;
	int now = Calendar.getInstance().get(Calendar.YEAR);
	private int stdNum;
	private double avgScore;
	private String finalGrade;
	
	private HashMap<Subject, Course> courseList;
	public double getAvgScore() {
		avgScore = courseList.values().stream()
				.mapToInt(sbj -> sbj.getScore())
				.average()
				.getAsDouble();
		return avgScore;
	}
	public String getFinalGrade() {
		int avg = (int)Math.round(getAvgScore());
		finalGrade = General.getInstance().getGrade(avg);
		return finalGrade;
	}
	
	public Student(String studentName, Major major) {
		this.studentName = studentName;
		this.setStudentNum();
		this.major = major;
		this.courseList = new HashMap<Subject, Course>();
	}
	
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public HashMap<Subject, Course> getCourseList() {
		return courseList;
	}
	public void addCourse(Subject...subject) {
		for (Subject sub : subject) {
			if (courseList.containsKey(sub)) {
				System.out.println("이미 추가된 과목입니다.");
			} else {
				courseList.put(sub, new Course(sub));
				courseList.get(sub).setGradeSystem(this);
			}
		}
	}
	public void addScore(Subject subject, int score) {
		if (courseList.isEmpty()) {
			System.out.println("과목을 먼저 추가하세요.");
			return;
		}
		if (courseList.get(subject).getScore() == -1) {
			System.out.println(subject + "과목 추가");
		} else {
			System.out.println(subject + "과목 점수 변경");
		}
		courseList.get(subject).setScore(score);
		getAvgScore();
	}
	
	public String getStudentName() {
		return studentName;
	}
	private void setStudentNum() {
		this.studentNum = ((now - 1) * 10000) + setSerialNum();
		this.stdNum = now;
	}
	public int getStudentNum() {
		return studentNum;
	}
	private int setSerialNum() {
		if (stdNum == now || now == 2023) {
			++serialNum;
		} else {
			Student.serialNum = 10001;
		}
		return serialNum;
	}

	@Override
	public String toString() {
		return studentName + "(" + studentNum + "): 전공 - " + major + ", 수강과목 - " + courseList;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Student student) {
			if (this.studentNum == student.studentNum) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		return studentNum;
	}
}
