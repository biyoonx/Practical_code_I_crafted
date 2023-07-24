package calcFigure.controller;

import calcFigure.model.Rectangle;
import lombok.Data;

@Data
public class RectangleController {
    private Rectangle r = new Rectangle();

    public String calcArea(int x, int y, int height, int width) {
        this.setRect(x, y, height, width);
        double area = r.getHeight() * r.getWidth();
        return this.r.toString() + ", area=" + area;
    }
    public String calcArea(Rectangle r) {
        return calcArea(r.getX(), r.getY(), r.getHeight(), r.getWidth());
    }
    public String calcPerimeter(int x, int y, int height, int width) {
        this.setRect(x, y, height, width);
        double circum = 2 * (r.getHeight() + r.getWidth());
        return this.r.toString() + ", circumference=" + circum;
    }
    public String calcPerimeter(Rectangle r) {
        return calcPerimeter(r.getX(), r.getY(), r.getHeight(), r.getWidth());
    }

    private void setRect(int x, int y, int height, int width) {
        r.setX(x);
        r.setY(y);
        r.setHeight(height);
        r.setWidth(width);
    }
}
