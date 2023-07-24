package calcFigure.view;

import calcFigure.controller.CircleController;
import calcFigure.controller.RectangleController;
import calcFigure.model.Circle;
import calcFigure.model.Point;
import calcFigure.model.Rectangle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PointView {
    private Scanner sc = new Scanner(System.in);
    private CircleController cc = new CircleController();
    private RectangleController rc = new RectangleController();

    private void showMenuOutline(String msg) {
        System.out.println("===== 메뉴 =====");
        System.out.println(msg);
        System.out.print("메뉴 번호 : ");
    }
    private String figureInfoMenu(String type) {
        return "1." + type + " 둘레\n2." + type + " 넓이\n3.메인으로";
    }
    private <T extends Point> void controlMenu(String func, Point figure) {
        try {
            Class<?> pv = Class.forName("calcFigure.view.PointView");
            Constructor<?> cons = pv.getConstructor();
            Object instance = cons.newInstance();
            Method method = null;
            if (figure instanceof Circle c) {
                method = pv.getMethod(func, new Class[] {c.getClass()});
                method.invoke(instance, c);
            } else if (figure instanceof Rectangle r) {
                method = pv.getMethod(func, new Class[] {r.getClass()});
                method.invoke(instance, r);
            } else {
                method = pv.getMethod(func);
                method.invoke(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void controlMenu(String func) {
        controlMenu(func, null);
    }
    private Map<String, String> makeMenuMap(String...menu) {
        Map<String, String> map = new HashMap<String, String>();
        int num = 1;
        for (String m : menu) {
            map.put(String.valueOf(num++), m);
        }
        return map;
    }
    public void mainMenu() {
        String menuContent = """
                1.원
                2.사각형
                3.끝내기
                """;
        showMenuOutline(menuContent);

        String menuNo = sc.nextLine();
        Map<String, String> map = makeMenuMap("circleMenu", "rectangleMenu", "exit");

        if (!map.containsKey(menuNo)) {
            System.out.println("잘못 입력하셨습니다.");
            mainMenu();
        }

        controlMenu(map.get(menuNo));
    }
    public void circleMenu() {
        String menuContent = figureInfoMenu("원");
        showMenuOutline(menuContent);
        String menuNo = sc.nextLine();

        if (menuNo.equals("3")) {
            mainMenu();
        } else {
            inputCircleCalcOutline("반지름", menuNo);
        }
    }
    public void rectangleMenu() {
        String menuContent = figureInfoMenu("사각형");
        showMenuOutline(menuContent);
        String menuNo = sc.nextLine();

        if (menuNo.equals("3")) {
            mainMenu();
        } else {
            inputRectCalcOutline("높이", "너비", menuNo);
        }
    }
    private void inputCircleCalcOutline(String msg, String menuNo) {
        Map<String, String> map = makeMenuMap("calcCircum", "calcCircleArea");
        if (!map.containsKey(menuNo)) {
            System.out.println("잘못 입력하셨습니다.");
            mainMenu();
        }
//        System.out.println(map.get(menuNo) + "()");

        System.out.print("x 좌표 : ");
        int x = Integer.parseInt(sc.nextLine());

        System.out.print("y 좌표 : ");
        int y = Integer.parseInt(sc.nextLine());

        System.out.print(msg + " : ");
        int result1 = Integer.parseInt(sc.nextLine());

        this.cc.setC(new Circle(x, y, result1));
        controlMenu(map.get(menuNo), this.cc.getC());
    }
    private void inputRectCalcOutline(String msg1, String msg2, String menuNo) {
        Map<String, String> map = makeMenuMap("calcPerimeter", "calcRectArea");
        if (!map.containsKey(menuNo)) {
            System.out.println("잘못 입력하셨습니다.");
            mainMenu();
        }
        System.out.println(map.get(menuNo) + "()");

        System.out.print("x 좌표 : ");
        int x = Integer.parseInt(sc.nextLine());

        System.out.print("y 좌표 : ");
        int y = Integer.parseInt(sc.nextLine());

        System.out.print(msg1 + " : ");
        int result1 = Integer.parseInt(sc.nextLine());

        System.out.print(msg2 + " : ");
        int result2 = Integer.parseInt(sc.nextLine());

        this.rc.setR(new Rectangle(x, y, result1, result2));
        controlMenu(map.get(menuNo), this.rc.getR());
    }
    public void calcCircum(Circle c) {
        this.cc.setC(c);
        System.out.println(cc.calcCircum(cc.getC()));
        mainMenu();
    }
    public void calcCircleArea(Circle c) {
        this.cc.setC(c);
        System.out.println(cc.calcArea(cc.getC()));
        mainMenu();
    }
    public void calcPerimeter(Rectangle r) {
        this.rc.setR(r);
        System.out.println(rc.calcPerimeter(rc.getR()));
        mainMenu();
    }
    public void calcRectArea(Rectangle r) {
        this.rc.setR(r);
        System.out.println(rc.calcArea(rc.getR()));
        mainMenu();
    }

    public void exit() {
        System.out.println("시스템이 종료됩니다.");
        System.exit(0);
    }
}
