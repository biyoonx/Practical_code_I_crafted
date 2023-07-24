package calcFigure.controller;

import calcFigure.model.Circle;
import lombok.Data;

@Data
public class CircleController {
    private Circle c = new Circle();

    public String calcArea(int x, int y, int radius) {
        this.setCircle(x, y, radius);
        double area = (Math.PI * Math.pow(c.getRadius(), 2));
        return this.c.toString() + ", area=" + area;
    }
    public String calcArea(Circle c) {
        return calcArea(c.getX(), c.getY(), c.getRadius());
    }
    public String calcCircum(int x, int y, int radius) {
        this.setCircle(x, y, radius);
        int circum = (int)(Math.PI * c.getRadius() * 2);
        return this.c.toString() + ", circumference=" + circum;
    }
    public String calcCircum(Circle c) {
        return calcCircum(c.getX(), c.getY(), c.getRadius());
    }

    private void setCircle(int x, int y, int radius) {
        c.setX(x);
        c.setY(y);
        c.setRadius(radius);
    }
    // 값들을 초기화하는 생성자를 불러서 대입하는 것이 좋은 방법일까 아니면 멤버변수를 초기화하는 메서드를 만들어서 사용하는 것이 좋은 방법일까?
}
