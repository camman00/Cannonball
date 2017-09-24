package me.Cannonball.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BoundingBox {
	private int x;
	private int y;
	private int x2;
	private int y2;
	private ReflectionLocation reflectionLocation = null;
	/**
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 */
	public BoundingBox(int x,int y,int x2,int y2) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @param reflectionLocation
	 */
	public BoundingBox(int x,int y,int x2,int y2,ReflectionLocation reflectionLocation) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.reflectionLocation = reflectionLocation;
	}
	private boolean containsSingle(BoundingBox otherBoundingBox,BoundingBox a) throws StartupException {
		try {
			return ((otherBoundingBox.getX() >= a.getX() && otherBoundingBox.getX() <= a.getX2() && otherBoundingBox.getY() >= a.getY() && otherBoundingBox.getY() <= a.getY2()) || (otherBoundingBox.getX() <= a.getX() && a.getX() >= otherBoundingBox.getX() && a.getX() <= otherBoundingBox.getX2() && a.getY() >= otherBoundingBox.getY() && a.getY() <= otherBoundingBox.getY2()));
		}
		catch(StackOverflowError e) {
			throw new StartupException();
		}
	}
	public boolean contains(BoundingBox otherBoundingBox) throws StartupException {
		if(containsSingle(otherBoundingBox,this) || containsSingle(this,otherBoundingBox)) {
			return true;
		}
		return false;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	public ReflectionLocation getReflectionLocation() {
		return reflectionLocation;
	}
	public void setReflectionLocation(ReflectionLocation reflectionLocation) {
		this.reflectionLocation = reflectionLocation;
	}
	public void testDraw(Graphics g) {
		g.setColor(new Color(new Random().nextInt(255),255,48));
		g.drawRect(x, y, x2 - x, y2 - y);
	}
}
