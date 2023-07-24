package calcFigure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private int x;
    private int y;

    @Override
    public String toString() {
        Map<String, Integer> values = new HashMap<String, Integer>();
        values.put("x", this.getX());
        values.put("y", this.getY());
        return "Point" + values.toString();
    }
}
