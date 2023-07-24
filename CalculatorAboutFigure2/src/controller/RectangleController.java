package controller;

import model.vo.Figure;
import model.vo.Point;
import model.vo.Rectangle;

public class RectangleController extends FigureController {
	public RectangleController() {
		this.figure = new Rectangle();
	}
	
	@Override
	public void calcArea() {
		double area = downCasting(figure).getWidth() * downCasting(figure).getHeight();
		figure.setArea(area);
	}
	public void calcArea(double width, double height) {
		this.setFigure(width, height);
		this.calcArea();
	}
	
	@Override
	public void calcPeri() {
		double perimeter = 2 * (downCasting(figure).getWidth() + downCasting(figure).getHeight());
		figure.setPeri(perimeter);
	}
	public void calcPeri(double width, double height) {
		this.setFigure(width, height);
		this.calcPeri();
	}
	
	public void calcAll(double width, double height) {
		this.calcArea(width, height);
		this.calcPeri(width, height);
	}
	
	@Override
	public void setFigure(Point p1, Point p2) {
		setPoints(p1, p2);
		downCasting(figure).setWidth(p1, p2);
		downCasting(figure).setHeight(p1, p2);
	}
	public void setFigure(double width, double height) {
		downCasting(figure).setWidth(width);
		downCasting(figure).setHeight(height);
	}
	
	@Override
	protected Rectangle downCasting(Figure figure) {
		if (figure instanceof Rectangle rect) {
			return rect;
		}
		return null;
	}
}
