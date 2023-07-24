package point.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rectangle extends Figure {
	private double width;
	private double height;
	
	public Rectangle(Point p1, Point p2) {
		super(p1, p2);
		
		this.setWidth(p1, p2);
		this.setHeight(p1, p2);
	}
	public Rectangle(double width, double height) {
		this.setWidth(width);
		this.setHeight(height);
	}
	
	@Override
	protected void checkPoint(Point p1, Point p2) {
		boolean cond1 = (p1.getX() == p2.getX());
		boolean cond2 = (p1.getY() == p2.getY());
		if (cond1 || cond2) {
			throw new IllegalArgumentException("두 점의 x, y 좌표 모두 달라야합니다.");
		}
	}
	
	public void setWidth(double width) {
		checkLength(width);
		
		this.width = width;
	}
	public void setWidth(Point p1, Point p2) {
		checkPoint(p1, p2);
		
		this.width = Math.abs(p1.getX() - p2.getX());
	}
	public void setHeight(double height) {
		checkLength(height);
		
		this.height = height;
	}
	public void setHeight(Point p1, Point p2) {
		checkPoint(p1, p2);
		
		this.height = Math.abs(p1.getY() - p2.getY());
	}
	
	@Override
	public String toString() {
		return "Rectangle(" + super.toString() + ", width=" + width + ", height=" + height + ")";
	}
}
