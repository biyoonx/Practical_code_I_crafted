package point.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import point.controller.CircleController;
import point.controller.FigureController;
import point.controller.RectangleController;
import point.model.vo.Circle;
import point.model.vo.Figure;
import point.model.vo.Point;
import point.model.vo.Rectangle;

public class FigureMenu {
	private Scanner sc = new Scanner(System.in);
	private class FigContent {
		String FigName;
		ArrayList<String> FigElem = new ArrayList<String>();
	}
	private FigContent circle;
	private FigContent rect;
	private Map<Integer, FigContent> figs;
	private Map<String, Double> values;
	{
		circle = new FigContent();
		circle.FigName = "원";
		circle.FigElem.add("반지름");
		
		rect = new FigContent();
		rect.FigName = "사각형";
		rect.FigElem.addAll(Arrays.asList("너비", "높이"));
		
		figs = new HashMap<Integer, FigContent>();
		figs.put(1, circle);
		figs.put(2, rect);
		
		values = new HashMap<String, Double>();
	}
	
	private int inputCastInt() {
		return Integer.parseInt(sc.nextLine());
	}
	private double inputCaseDouble() {
		return Double.parseDouble(sc.nextLine());
	}
	
	private String makeMainMenuContent(String type, String cont) {
		StringBuilder content = new StringBuilder();
		content.append("===== ").append(type).append("메뉴 =====\n")
			   .append(cont).append("\n")
			   .append("메뉴 번호 : ");
		return content.toString();
	}
	private String makeFigureMenuContent(String type) {
		StringBuilder content = new StringBuilder();
		content.append("1.").append(type).append(" 둘레").append("\n")
			   .append("2.").append(type).append(" 넓이").append("\n")
			   .append("3.").append(type).append(" 전체").append("\n")
			   .append("4.메인으로").append("\n");
		return content.toString();
	}
	
	public void mainMenu() {
		String content = """
				1.원
				2.사각형
				3.종료
				""";
		String mainContent = makeMainMenuContent("", content);
		System.out.print(mainContent);
		int menuNo = inputCastInt();
		switch (menuNo) {
			case 1, 2 -> figureMenu(menuNo); 
			case 3 -> {
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
			default -> throw new IllegalArgumentException("잘못된 메뉴 번호입니다.");
		}
	}
	private void figureMenu(int figureType) {
		FigContent fig = figs.get(figureType);
		String content = makeFigureMenuContent(fig.FigName);
		String mainContent = makeMainMenuContent(fig.FigName + " ", content);
		System.out.print(mainContent);
		int calcType = inputCastInt();
		switch (calcType) {
			case 1 -> figureInfoMenu(figureType, "둘레");
			case 2 -> figureInfoMenu(figureType, "넓이");
			case 3 -> figureInfoMenu(figureType, "전체");
			case 4 -> {
				values.clear();
				mainMenu();
			}
			default -> throw new IllegalArgumentException("잘못된 메뉴 번호입니다.");
		}
	}
	private void figureInfoMenu(int figureType, String calcType) {
		String content = """
				1.점 좌표 입력
				2.도형 길이 입력
				""";
		String mainContent = makeMainMenuContent("", content);
		System.out.print(mainContent);
		int inputType = inputCastInt();
		switch (inputType) {
			case 1 -> {
				makePoint(1);
				makePoint(2);
				callCalc(figureType, calcType, "point");
			}
			case 2 -> {
				figs.get(figureType).FigElem.forEach(elem -> values.put(elem, inputNumber(elem)));
				callCalc(figureType, calcType, "length");
			}
		}
	}
	private double inputNumber(String val) {
		System.out.print(val + " : ");
		return inputCaseDouble();
	}
	private void makePoint(int no) {
		values.put("Point" + no + "X", inputNumber("Point" + no + " x 좌표"));
		values.put("Point" + no + "Y", inputNumber("Point" + no + " y 좌표"));
	}
	
	private void callCalc(int figureType, String calcType, String inputType) {
		FigureController controller = switch (inputType) {
			case "point" -> calcByPoints(figureType);
			case "length" -> calcByLength(figureType);
			default -> throw new IllegalArgumentException("잘못된 메뉴 번호입니다.");
		};
		String result = switch (calcType) {
			case "둘레" -> String.valueOf(controller.getFigure().getPeri());
			case "넓이" -> String.valueOf(controller.getFigure().getArea());
			case "전체" -> controller.getFigure().toString();
			default -> throw new IllegalArgumentException("잘못된 메뉴 번호입니다.");
		};
		System.out.println(figs.get(figureType).FigName + " " + calcType + " : " + result);
		values.clear();
		mainMenu();
	}
	private FigureController calcByPoints(int figureType) {
		Point point1 = new Point(values.get("Point1X"), values.get("Point1Y"));
		Point point2 = new Point(values.get("Point2X"), values.get("Point2Y"));
		FigureController controller = makeController(figureType);
		controller.calcAll(point1, point2);
		return controller;
	}
	private FigureController calcByLength(int figureType) {
		Figure figure = makeFigure(figureType);
		FigureController controller = makeController(figureType);
		controller.calcAll(figure);
		return controller;
	}
	private FigureController makeController(int figureType) {
		FigureController controller = switch (figureType) {
			case 1 -> new CircleController();
			case 2 -> new RectangleController();
			default -> throw new IllegalArgumentException("잘못된 메뉴 번호입니다.");
		};
		return controller;
	}
	private Figure makeFigure(int figureType) {
		Figure figure = switch (figureType) {
			case 1 -> {
				double radius = values.get(figs.get(figureType).FigElem.get(0));
				yield new Circle(radius);
			}
			case 2 -> {
				double width = values.get(figs.get(figureType).FigElem.get(0));
				double height = values.get(figs.get(figureType).FigElem.get(1));
				yield new Rectangle(width, height);
			}
			default -> throw new IllegalArgumentException("잘못된 메뉴 번호입니다.");
		};
		return figure;
	}
}
