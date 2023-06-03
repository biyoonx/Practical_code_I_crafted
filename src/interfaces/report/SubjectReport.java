package interfaces.report;

import java.util.ArrayList;

import classes.Student;
import classes.Subject;

public final class SubjectReport extends Report {
	public SubjectReport(Subject subject, Student...students) {
		super(subject, students);
	}
	
	@Override
	ArrayList<Student> reStdList(Student...students) {
		for (Student student : students) {
			if (student.getCourseList().containsKey(reportable)) {
				studentList.add(student);
			}
		}
		return studentList;
	}
	
	@Override
	StringBuilder getHeader() {
		reportHeader
			.append(doubleLine)
			.append("\t")
			.append(reportable)
			.append(" 수강생 학점\n")
			.append(doubleLine)
			.append("  이름 ")
			.append(divider)
			.append("  학번  ")
			.append(divider)
			.append("    전공    ")
			.append(divider)
			.append("점수 \n")
			.append(line);
		return reportHeader;
	}
	@Override
	StringBuilder getMain() {
		studentList.stream()
			.forEach(std -> {
				reportMain
					.append(" ")
					.append(std.getStudentName())
					.append(divider)
					.append(std.getStudentNum())
					.append(divider)
					.append(std.getMajor().getMajorName())
					.append(divider)
					.append(std.getCourseList().get(reportable).getScore())
					.append("점:")
					.append(std.getCourseList().get(reportable).getGrade())
					.append("\n");
			});
		return reportMain;
	}
	@Override
	StringBuilder getFooter() {
		reportMain
			.append(doubleLine)
			.append("  총점")
			.append(divider)
			.append(" 평균 ")
			.append(divider)
			.append("최고점/최저점")
			.append(divider)
			.append(" 총인원\n")
			
			.append(line)
			.append(" ")
			.append(sum())
			.append("점")
			.append(divider)
			.append(avg())
			.append("점")
			.append(divider)
			.append("  ")
			.append(max())
			.append("점/")
			.append(min())
			.append("점  ")
			.append(divider)
			.append("  ")
			.append(count())
			.append("명\n")
			.append(doubleLine);
		return reportFooter;
	}
	@Override
	long sum() {
		return studentList.stream()
				.mapToLong(std -> std.getCourseList().get(reportable).getScore())
				.sum();
	}
	@Override
	String mode() {
		// 점수 시스템이 두개라 과목별 성적은 하나로 일괄적으로 묶어서 최빈값을 만들 수가 없음. 만들려면 다른 기준치가 있어야 함
//		String mode = studentList.stream()
//				.map(std -> std.getCourseList().get(reportable).getGrade())
//				.forEach(std -> {
//					String grades = Arrays.asList("A", "B", "C", "D", "E", "F");
//				});
		return null;
	}
	@Override
	double avg() {
		return sum() / count();
	}
	@Override
	int max() {
		return studentList.stream()
				.mapToInt(std -> std.getCourseList().get(reportable).getScore())
				.max()
				.getAsInt();
	}
	@Override
	int min() {
		return studentList.stream()
				.mapToInt(std -> std.getCourseList().get(reportable).getScore())
				.min()
				.getAsInt();
	}
}
