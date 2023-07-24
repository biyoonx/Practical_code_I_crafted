package run;

import view.LibraryMenu;

public class Run {

	public static void main(String[] args) {
		LibraryMenu library = new LibraryMenu();
		
		// 도서관의 책 추가는 어디에 배치하는게 좋지? 이건 도서관 이용자가 아니라 관리자쪽에 속하는 거 같은데 보통은 어떻게 할까?
		library.insertBook("진짜 기본 요리책 레시피, 레시피팩토리", "수퍼레시피", "레시피팩토리", true);
		library.insertBook("살육의 천사", "Sanada Naoki", "D&C미디어", 19);
		library.insertBook("귀멸의 칼날", "Koyoharu Gotouge", "학산문화사/DCW", 15);
		library.insertBook("진격의 거인", "Hajime Isayama", "학산문화사", 15);
		library.insertBook("서른의 식사법", "박민정", "시루", false);
		library.insertBook("[만개의 레시피]700만이 뽑은 초간단 인생 요리 120", "만개의 레시피", "만개의 레시피", true);
		library.insertBook("세이노의 가르침", "세이노", "데이원");
		library.insertBook("문과 남자의 과학 공부", "유시민", "돌베개");
		library.insertBook("도둑맞은 집중력", "요한 하리", "어크로스");
		library.insertBook("하늘과 바람과 별과 인간", "김상욱", "바다출판사");
		
		// 도서관 프로그램 시작하기.
		library.selectSignInUpMenu();
	}

}
