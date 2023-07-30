package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import controller.LibraryController;
import lombok.Builder.Default;
import model.vo.AniBook;
import model.vo.Book;
import model.vo.CookBook;
import model.vo.Member;

public class LibraryMenu {
	private LibraryController lc = new LibraryController();
	private Scanner sc = new Scanner(System.in);
	private Member currUser = null;
	
	public void insertBook(String title, String author, String publisher) {
		Book book = new Book(title, author, publisher);
		lc.insertBook(book);
	}
	public void insertBook(String title, String author, String publisher, int accessAge) {
		Book book = new AniBook(title, author, publisher, accessAge);
		lc.insertBook(book);
	}
	public void insertBook(String title, String author, String publisher, boolean coupon) {
		Book book = new CookBook(title, author, publisher, coupon);
		lc.insertBook(book);
	}
	
	private int inputCastInt() {
		return Integer.parseInt(sc.nextLine());
	}
	private String showNInput(String cont) {
		System.out.print(cont + " : ");
		return sc.nextLine();
	}
	private String makeMenuOutline(String...cont) {
		StringBuilder content = new StringBuilder();
		content.append("===== 메뉴 =====").append("\n");
		int menuNo = 0;
		for (String menu : cont) {
			content.append(++menuNo + ".").append(menu).append("\n");
		}
		content.append("메뉴 번호 : ");
		return content.toString();
	}
	public void selectSignInUpMenu() {
		String content = makeMenuOutline("로그인", "회원가입");
		System.out.print(content);
		int menuNo = inputCastInt();
		switch (menuNo) {
			case 1 -> signInMenu();
			case 2 -> signUpMenu();
			default -> {
				System.out.println("잘못 입력하셨습니다.");
				selectSignInUpMenu();
			}
		}
	}
	private void signInMenu() {
		try {
			int memNo = Integer.parseInt(showNInput("회원번호"));
			if (Optional.ofNullable(lc.memInfo(memNo)).isEmpty()) {
				System.out.println("없는 회원번호입니다.");
				selectSignInUpMenu();
			} else {
				currUser = lc.memInfo(memNo);
				System.out.println("로그인되었습니다.");
				mainMenu();
			}
		} catch(Exception e) {
			System.out.println("잘못 입력하셨습니다.");
			selectSignInUpMenu();
		}
	}
	private void signUpMenu() {
		try {
			String name = showNInput("이름");
			int age = Integer.parseInt(showNInput("나이"));
			char gender = showNInput("성별(F/M)").charAt(0);
			Member mem = new Member(name, age, gender);
			
			lc.insertMember(mem);
			currUser = mem;
			System.out.println("회원가입이 성공했습니다. 로그인됩니다.");
			System.out.println("회원번호 : " + mem.getMemNo());
			mainMenu();
		} catch(Exception e) {
			System.out.println("잘못 입력하셨습니다.");
			selectSignInUpMenu();
		}
	}
	private void mainMenu() {
		String content = makeMenuOutline(
			"마이페이지",
			"도서 전체 조회",
			"도서 검색",
			"도서 대여하기",
			"도서 반납하기",
			"로그아웃",
			"프로그램 종료하기");
		System.out.print(content);
		int menuNo = inputCastInt();
		
		switch (menuNo) {
			case 1 -> showMyPage();
			case 2 -> selectAll();
			case 3 -> searchBook();
			case 4 -> rentBook();
			case 5 -> returnBooks();
			case 6 -> logOut();
			case 7 -> exit();
			default -> {
				System.out.println("잘못 입력하셨습니다.");
				mainMenu();
			}
		}
	}
	private void showMyPage() {
		System.out.println(currUser.toString());
		backOrExitMenu();
	}
	private void selectAll() {
		System.out.println("현재 소장중인 도서는 " + lc.selectAll().size() + "권 입니다.");
		for (Book book : lc.selectAll().values()) {
			System.out.println(book.toString());
		}
		backOrExitMenu();
	}
	private void searchBook() {
		String keyword = showNInput("검색 키워드");
		Map<Integer, Book> result = lc.searchBook(keyword);
		if (Optional.ofNullable(result).isEmpty()) {
			System.out.println("검색 결과가 없습니다.");
		} else {
			result.values().stream()
				.forEach(book -> System.out.println(book.toString()));
		}
		backOrExitMenu();
	}
	private void rentBook() {
		int numOfBooks = Integer.parseInt(showNInput("빌릴 책 권수"));
		
		if (!(lc.isAbleToBorrow(currUser, numOfBooks))) {
			System.out.println("1인당 총 5권까지만 대여 가능합니다.");
		} else {
			List<Integer> bookNoes = new ArrayList<Integer>();
			
			for (int i = 0; i < numOfBooks; i++) {
				int bookNo = Integer.parseInt(showNInput((i + 1) + "번째 책번호"));
				bookNoes.add(bookNo);
			}
			
			int[] bookNoesArr = bookNoes.stream().mapToInt(i -> i).toArray();
			boolean result = lc.borrowBooks(currUser, bookNoesArr);
			if (result) {
				if (lc.issueCoupon(bookNoesArr) > 0) {
					System.out.println("요리학원 쿠폰이 " + lc.issueCoupon(bookNoesArr) + "개 발급되었습니다.");
				}
				System.out.println("대여를 완료하였습니다.");
			} else {
				if (lc.checkAccessAge(currUser, bookNoesArr)) {
					System.out.println("책이 없거나 대출중입니다.");
				} else {
					System.out.println("나이제한에 해당하는 도서가 있습니다.");
				}
			}
		}
		backOrExitMenu();
	}
	private void returnBooks() {
		if (currUser.getBorrowedBList().size() < 1) {
			System.out.println("반납할 책이 없습니다.");
		} else {
			int numOfBooks = Integer.parseInt(showNInput("반납할 책 권수"));
			List<Integer> bookNoes = new ArrayList<Integer>();
			
			for (int i = 0; i < numOfBooks; i++) {
				int bookNo = Integer.parseInt(showNInput((i + 1) + "번째 책번호"));
				bookNoes.add(bookNo);
			}
			int[] bookNoesArr = bookNoes.stream().mapToInt(i -> i).toArray();
			boolean result = lc.returnBooks(currUser, bookNoesArr);
			if (result) {
				System.out.println("반납을 완료하였습니다.");
			} else {
				System.out.println("반납에 실패하였습니다. 반납하려는 책 정보를 확인해주세요.");
			}
		}
		backOrExitMenu();
	}
	
	private void backOrExitMenu() {
		String content = makeMenuOutline("뒤로가기", "종료하기");
		System.out.print(content);
		int menuNo = inputCastInt();
		
		switch (menuNo) {
			case 1 -> mainMenu();
			case 2 -> exit();
			default -> {
				System.out.println("잘못 입력하셨습니다.");
				mainMenu();
			}
		}
	}
	private void logOut() {
		currUser = null;
		System.out.println("로그아웃 되었습니다.");
		selectSignInUpMenu();
	}
	private void exit() {
		System.out.println("시스템을 종료합니다.");
		System.exit(0);
	}
}
