package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controller.PhoneController;

public class PhoneView {
	private PhoneController pc = new PhoneController();
	private Scanner sc = new Scanner(System.in);
	private Map<Integer, String> phoneMenu = new HashMap<Integer, String>();
	{
		phoneMenu.put(1, "GalaxyNote9");
		phoneMenu.put(2, "V40");
	}
	
	public void mainMenu() {
		System.out.println("""
				===== 메뉴 =====
				1.갤럭시 노트9 정보
				2.V40 정보
				3.모든 핸드폰 정보 출력
				4.종료""");
		System.out.print("메뉴 번호 : ");
		int menuNo = Integer.parseInt(sc.nextLine());
		switch (menuNo) {
			case 1, 2 -> System.out.println("\n" + pc.method(phoneMenu.get(menuNo)) + "\n");
			case 3 -> {
				phoneMenu.values().stream().forEach(phone -> System.out.println("\n" + pc.method(phone)));
				System.out.println();
			}
			case 4 -> exit();
			default -> {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		mainMenu();
	}
	private void exit() {
		System.out.println("시스템을 종료합니다.");
		System.exit(0);
	}
}
