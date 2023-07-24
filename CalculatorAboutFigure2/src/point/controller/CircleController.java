package point.controller;

import point.model.vo.Circle;
import point.model.vo.Figure;
import point.model.vo.Point;

public class CircleController extends FigureController {
	public CircleController() {
		this.figure = new Circle();
	}
	
	@Override
	public void calcArea() {
		double area = Math.PI * Math.pow(downCasting(figure).getRadius(), 2);
		figure.setArea(area);
	}
	public void calcArea(double radius) {
		this.setFigure(radius);
		this.calcArea();
	}
	
	@Override
	public void calcPeri() {
		double peri = 2 * Math.PI * downCasting(figure).getRadius();
		figure.setPeri(peri);
	}
	public void calcPeri(double radius) {
		this.setFigure(radius);
		this.calcPeri();
	}
	
	public void calcAll(double radius) {
		this.calcArea(radius);
		this.calcPeri(radius);
	}
	
	@Override
	public void setFigure(Point p1, Point p2) {
		setPoints(p1, p2);
		downCasting(figure).setRadius(p1, p2);
	}
	public void setFigure(double radius) {
		downCasting(figure).setRadius(radius);
	}

	@Override
	protected Circle downCasting(Figure figure) {
		if (figure instanceof Circle circle) {
			return circle;
		}
		return null;
	}
}
