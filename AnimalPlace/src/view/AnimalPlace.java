package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Animal;
import model.Cat;
import model.Dog;

public class AnimalPlace {
	private Scanner sc = new Scanner(System.in);
	private Map<Integer, Animal> animals = new HashMap<Integer, Animal>();
	{
		animals.put(1, new Dog("댕댕이", "말티즈", 10));
		animals.put(2, new Cat("야옹이", "샴", "어딨니", "크림&암갈색"));
		animals.put(3, new Dog("또리", "포메라니안", 15));
		animals.put(4, new Cat("고먐미", "페르시안", "넌 또 어딨니", "하양"));
		animals.put(5, new Dog("후추", "시베리안 허스키", 30));
		animals.put(6, new Cat("벌이", "렉돌", "집에 있었으면", "크림&초콜렛"));
	}
	
	private void showAnimal() {
		int randNum = (int)(Math.random() * 6 + 1);
		System.out.println();
		animals.get(randNum).speak();
		System.out.println();
		mainMenu();
	}
	
	public void mainMenu() {
		System.out.println("""
				===== 메뉴 =====
				1.동물 아무나
				2.모든 동물
				3.종료
				""");
		System.out.print("메뉴 번호 : ");
		int menuNo = Integer.parseInt(sc.nextLine());
		switch (menuNo) {
			case 1 -> showAnimal();
			case 2 -> {
				System.out.println();
				animals.values().stream().forEach(animal -> animal.speak());
				System.out.println();
				mainMenu();
			}
			case 3 -> {
				System.out.println("\n시스템을 종료합니다.");
				System.exit(0);
			}
			default -> {
				System.out.println("\n잘못 입력하셨습니다.\n");
				mainMenu();
			}
		}
	}
}
