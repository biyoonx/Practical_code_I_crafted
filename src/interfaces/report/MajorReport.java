package interfaces.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import classes.Major;
import classes.Student;

public final class MajorReport extends Report {

	public MajorReport(Major major, Student[] students) {
		super(major, students);
	}
	
	@Override
	ArrayList<Student> reStdList(Student... students) {
		for (Student student : students) {
			if (student.getMajor().equals(reportable)) {
				studentList.add(student);
			}
		}
		return studentList;
	}

	@Override
	StringBuilder getHeader() {
		reportHeader
			.append(doubleLine)
			.append("     ")
			.append(((Major)reportable).getMajorName() + "(" + ((Major)reportable).getMajorNum() + ")")
			.append(" 전공생 학점\n")
			.append(doubleLine)
			.append("  이름 ")
			.append(divider)
			.append("  학번  ")
			.append(divider)
			.append("    평균    ")
			.append(divider)
			.append("학점 \n")
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
				.append("   ")
				.append((double)Math.round(std.getAvgScore() * 10) / 10)
				.append("점   ")
				.append(divider)
				.append("  ")
				.append(std.getFinalGrade() + "\n");
		});
	return reportMain;
	}

	@Override
	StringBuilder getFooter() {
		reportMain
		.append(doubleLine)
		.append(" 최빈값")
		.append(divider)
		.append("평균")
		.append(divider)
		.append("최고점/최저점")
		.append(divider)
		.append(" 총인원 \n")
		
		.append(line)
		.append("   ")
		.append(mode())
		.append("   ")
		.append(divider)
		.append(Math.round(avg()))
		.append("점")
		.append(divider)
		.append("  ")
		.append(max())
		.append("점/")
		.append(min())
		.append("점  ")
		.append(divider)
		.append("   ")
		.append(count())
		.append("명\n")
		.append(doubleLine);
	return reportFooter;
	}

	@Override
	long sum() {
		return -1;
	}
	@Override
	String mode() {
		// 학생수가 많아지면 성적별 빈도수를 구하고 또 그래프를 그려볼 수도 있을 듯
		List<String> grades = Arrays.asList("A", "B", "C", "D", "F");
		HashMap<String, Integer> gradesCount = new HashMap<String, Integer>();
		for (int i = 0; i < grades.size(); i++) {
			gradesCount.put(grades.get(i), 0);
		}
		studentList.stream()
			.map(std -> std.getFinalGrade())
			.forEach(grd -> gradesCount.replace(grd, gradesCount.get(grd) + 1));
		String mode = Collections.max(gradesCount.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
		return mode;
	}
	@Override
	double avg() {
		return studentList.stream()
			.mapToDouble(std -> std.getAvgScore())
			.average()
			.getAsDouble();
	}
	@Override
	int max() {
		return studentList.stream()
			.mapToInt(std -> (int)Math.round(std.getAvgScore()))
			.max()
			.getAsInt();
	}
	@Override
	int min() {
		return studentList.stream()
				.mapToInt(std -> (int)Math.round(std.getAvgScore()))
				.min()
				.getAsInt();
	}
}
