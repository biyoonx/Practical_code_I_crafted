package interfaces.report;

//import java.util.Arrays;
import java.util.ArrayList;

import classes.Student;
import interfaces.Reportable;

public sealed abstract class Report permits SubjectReport, MajorReport {
	public static final String line = "-------------------------------------------\n";
	public static final String doubleLine = "===========================================\n";
	public static final String divider = " | ";
	
	protected Reportable reportable;
	protected ArrayList<Student> studentList;
	protected String finalReport;
	protected StringBuilder reportHeader;
	protected StringBuilder reportMain;
	protected StringBuilder reportFooter;
	
	abstract StringBuilder getHeader();
	abstract StringBuilder getMain();
	abstract StringBuilder getFooter();
	
	abstract long sum();
	abstract String mode();
	abstract int max();
	abstract int min();
	abstract double avg();
	protected int count() {
		return studentList.size();
	}
	abstract ArrayList<Student> reStdList(Student...students);
	
	protected String mkFinalReport() {
		getHeader();
		getMain();
		getFooter();
		
		finalReport = reportHeader
			.append(reportMain)
			.append(reportFooter)
			.toString();
		return finalReport;
	}
	public Report(Reportable reportable, Student...students) {
		this.reportable = reportable;
		this.studentList = new ArrayList<Student>();
		this.reportHeader = new StringBuilder();
		this.reportMain = new StringBuilder();
		this.reportFooter = new StringBuilder();
		
		// 매개값으로 학생을 수강생들만 넣었을 때
//		studentList.addAll(Arrays.asList(students));
		
		// 매개값으로 전체학생을 넣었을 때 해당 과목수강생/전공생로만 재구성하기
		this.reStdList(students);
		
		this.mkFinalReport();
	}
	public void showReport() {
		System.out.println(finalReport);
	}
}
