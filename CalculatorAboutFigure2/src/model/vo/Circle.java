package model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Circle extends Figure {
	private double radius;
	
	public Circle(Point p1, Point p2) {
		super(p1, p2);
		
		this.setRadius(p1, p2);
	}
	public Circle(double radius) {
		this.setRadius(radius);
	}
	
	@Override
	protected void checkPoint(Point p1, Point p2) {
		boolean cond1 = (p1.getX() == p2.getX());
		boolean cond2 = (p1.getY() == p2.getY());
		if (cond1 && cond2) {
			throw new IllegalArgumentException("두 점의 좌표가 동일합니다.");
		}
	}
	public void setRadius(Point p1, Point p2) {
		checkPoint(p1, p2);
		setPoint1(p1);
		setPoint2(p2);
		
		this.radius = Math.sqrt(Math.pow(point1.getX()-point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
	}
	public void setRadius(double radius) {
		checkLength(radius);
		
		this.radius = radius;
	}
	
	@Override
	public String toString() {
		return "Circle(" + super.toString() + ", radius=" + radius + ")";
	}
}
