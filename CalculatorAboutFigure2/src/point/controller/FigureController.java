package point.controller;

import lombok.Getter;
import point.model.vo.Figure;
import point.model.vo.Point;

public abstract class FigureController {
	@Getter
	protected Figure figure;
	
	public abstract void calcArea();
	public void calcArea(Point p1, Point p2) {
		this.setFigure(p1, p2);
		this.calcArea();
	}
	
	public abstract void calcPeri();
	public void calcPeri(Point p1, Point p2) {
		this.setFigure(p1, p2);
		this.calcPeri();
	}
	
	public void calcAll() {
		this.calcArea();
		this.calcPeri();
	}
	public void calcAll(Figure figure) {
		this.setFigure(figure);
		this.calcAll();
	}
	public void calcAll(Point p1, Point p2) {
		this.calcArea(p1, p2);
		this.calcPeri(p1, p2);
	}
	
	public void setFigure(Figure figure) {
		this.figure = downCasting(figure);
	}
	public abstract void setFigure(Point p1, Point p2);
	public void setPoints(Point p1, Point p2) {
		figure.setPoint1(p1);
		figure.setPoint2(p2);
	}
	
	protected abstract Figure downCasting(Figure figure);
	
	@Override
	public String toString() {
		return downCasting(figure).toString();
	}
}
