package classes;

import interfaces.GradeSystem;
import interfaces.implementation.ForRequiredSubject;
import interfaces.implementation.General;
import interfaces.implementation.PassFail;

public class Course {
	Subject subject;
	int score;
	String grade;
	GradeSystem gradeSystem;
	
	public Course(Subject subject) {
		this.subject = subject;
		this.score = -1;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setGradeSystem(Student student) {
		if (subject.isPassFail) {
			gradeSystem = PassFail.getInstance();
			return;
		}
		if (subject == student.getMajor().requiredSub) {
			gradeSystem = ForRequiredSubject.getInstance();
		} else {
			gradeSystem = General.getInstance();
		}
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
		this.setGrade();
	}
	public String getGrade() {
		return grade;
	}
	private void setGrade() {
		this.grade = gradeSystem.getGrade(getScore());
	}
	
	@Override
	public String toString() {
//		return getSubject().toString() + getScore() + ": " + getGrade();
		return getScore() + ": " + getGrade();
	}
}
