package runtest;

import java.util.ArrayList;
import java.util.Arrays;

import classes.Major;
import classes.Student;
import classes.Subject;
import interfaces.report.MajorReport;
import interfaces.report.Report;
import interfaces.report.SubjectReport;

public class RunTest {

	// 2023년이 아니라 2024년으로 뜨는가 → serialNum이랑 더해지면서 1이 추가된 것!
	// 데이터를 메서드에서 입력해줄 수도 있음(나는 그냥 DB는 보통 DBMS 연결해줄 것 같은데 하나하나 입력해야 하길래 메인에 넣어버림. 프로그램 연결할 때는 연결하는 메서드를 만들고 메인에서 연결하는 작업을 해주기만 한다고 함) 
	
	public static void main(String[] args) {
		// 강의 생성
		Subject korean = new Subject("국어");
		Subject math = new Subject("수학");
		
		// 강의 추가
		Subject dance = new Subject("방송댄스");
		dance.setIsPassFail(true);
		
		// 전공 생성
		Major koreanLL = new Major("국어국문학과", korean);
		Major computerS = new Major("컴퓨터공학과", math);
		
		// 학생(+전공) 생성
		Student Ahn = new Student("안성원", koreanLL);
		Student Oh = new Student("오태훈", computerS);
		Student Lee = new Student("이동호", koreanLL);
		Student Jo = new Student("조성욱", koreanLL);
		Student Choi = new Student("최태평", computerS);
		
		// 수강과목 담기 및 점수 입력
		Ahn.addCourse(korean, math);
		Ahn.addScore(korean, 56);
		Ahn.addScore(math, 95);
		Oh.addCourse(korean, math);
		Oh.addScore(korean, 98);
		Oh.addScore(math, 95);
		Lee.addCourse(korean, math);
		Lee.addScore(korean, 88);
		Lee.addScore(math, 100);
		Jo.addCourse(korean, math);
		Jo.addScore(korean, 95);
		Jo.addScore(math, 89);
		Choi.addCourse(korean, math);
		Choi.addScore(korean, 56);
		Choi.addScore(math, 83);
		
		Ahn.addCourse(dance);
		Ahn.addScore(dance, 95);
		Oh.addCourse(dance);
		Oh.addScore(dance, 85);
		Lee.addCourse(dance);
		Lee.addScore(dance, 55);
		
		// 학생 목록
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.addAll(Arrays.asList(Ahn, Oh, Lee, Jo, Choi));
		Student[] studentArr = studentList.toArray(new Student[studentList.size()]);
		
		System.out.println();
		
		// 클래스 및 메서드 테스트용
		System.out.println("< 확인용 >");
		System.out.println(korean);
		System.out.println(math);
		System.out.println(dance);
		System.out.println(koreanLL);
		System.out.println(computerS);
		System.out.println(studentList);
		
		System.out.println();

		// 리포트 생성(학생은 개인 따로따로 넣거나 배열로 넣어야 함)
		System.out.println("< 리포트 >");
		// 강의 리포트
		Report koreanReport = new SubjectReport(korean, Ahn, Oh, Lee, Jo, Choi);
		koreanReport.showReport();
		Report mathReport = new SubjectReport(math, Ahn, Oh, Lee, Jo, Choi);
		mathReport.showReport();
		Report danceReport = new SubjectReport(dance, studentArr);
		danceReport.showReport();
		// 전공 리포트
		Report koreanLLReport = new MajorReport(koreanLL, studentArr);
		koreanLLReport.showReport();
		Report computerSReport = new MajorReport(computerS, studentArr);
		computerSReport.showReport();
	}

}
