package model.vo;

import java.util.Optional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Figure {
	protected Point point1;
	protected Point point2;
	protected double area;
	protected double peri;
	
	public Figure(Point p1, Point p2) {
		this.setPoint1(p1);
		this.setPoint2(p2);
	}
	protected abstract void checkPoint(Point p1, Point p2);
	public void setPoint1(Point p1) {
		if (Optional.ofNullable(point2).isPresent()) {
			checkPoint(p1, point2);
		}
		this.point1 = p1;
	}
	public void setPoint2(Point p2) {
		if (Optional.ofNullable(point1).isPresent()) {
			checkPoint(point1, p2);
		}
		this.point2 = p2;
	}
	protected void checkLength(double length) {
		if (length < 0) {
			throw new IllegalArgumentException("길이는 0보다 커야 합니다.");
		}
	}
	
	@Override
	public String toString() {
		if (Optional.ofNullable(point1).isPresent() && Optional.ofNullable(point2).isPresent()) {
			return point1 + ", " + point2 + ", area=" + area + ", peri=" + peri;
		}
		return "area=" + area + ", peri=" + peri;
	}
}
