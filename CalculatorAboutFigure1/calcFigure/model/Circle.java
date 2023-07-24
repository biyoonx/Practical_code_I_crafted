package calcFigure.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class Circle extends Point {
    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public String toString() {
        Map<String, Integer> values = new HashMap<String, Integer>();
        values.put("x", this.getX());
        values.put("y", this.getY());
        values.put("radius", this.getRadius());
        return "Circle" + values.toString();
    }
}
