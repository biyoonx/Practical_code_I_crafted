package calcFigure.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Rectangle extends Point {
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        Map<String, Integer> values = new HashMap<String, Integer>();
        values.put("x", this.getX());
        values.put("y", this.getY());
        values.put("width", this.getWidth());
        values.put("height", this.getHeight());
        return "Rectangle" + values.toString(); // 출력 순서가 별로네 좋은 방법은 없을까?
    }
}
