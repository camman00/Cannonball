package me.Cannonball.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BoundingBox {
	/*
	 * The x,y,x2, and y2 points of the bounding box
	 */
	private int x;
	private int y;
	private int x2;
	private int y2;
	/*
	 * Default state for the ReflectionLocation
	 */
	private ReflectionLocation reflectionLocation = null;
	/**
	 * Define a new bounding box\n
	 * <p><b>x < x2 and y < y2</b></p>
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
	 * Define a new bounding box
	 * <p><b>x < x2 and y < y2</b></p>
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
	/**
	 * Check if the BoundingBox contains the other
	 * @param otherBoundingBox
	 * @param a
	 * @return
	 * @throws StartupException
	 */
	private boolean containsSingle(BoundingBox otherBoundingBox,BoundingBox a) throws StartupException {
		try {
			return ((otherBoundingBox.getX() >= a.getX() && otherBoundingBox.getX() <= a.getX2() && otherBoundingBox.getY() >= a.getY() && otherBoundingBox.getY() <= a.getY2()) || (otherBoundingBox.getX() <= a.getX() && a.getX() >= otherBoundingBox.getX() && a.getX() <= otherBoundingBox.getX2() && a.getY() >= otherBoundingBox.getY() && a.getY() <= otherBoundingBox.getY2()));
		}
		catch(StackOverflowError e) {
			throw new StartupException();
		}
	}
	/**
	 * Checks if the bounding boxes contains each other. Runs twice to find a ratio
	 * in which the smaller box is in the bigger box
	 * @param otherBoundingBox
	 * @return
	 * @throws StartupException
	 */
	public boolean contains(BoundingBox otherBoundingBox) throws StartupException {
		if(containsSingle(otherBoundingBox,this) || containsSingle(this,otherBoundingBox)) {
			return true;
		}
		return false;
	}
	/**
	 * Get the x
	 * @return
	 */
	public int getX() {
		return x;
	}
	/**
	 * Set the x
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Get the y
	 * @return
	 */
	public int getY() {
		return y;
	}
	/**
	 * Set the y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Get the x2
	 * @return
	 */
	public int getX2() {
		return x2;
	}
	/**
	 * Set the x2
	 * @param x2
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}
	/**
	 * Get the y2
	 * @return
	 */
	public int getY2() {
		return y2;
	}
	/**
	 * Set the y2
	 * @param y2
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}
	/**
	 * Get the reflectionLocation
	 * @return
	 */
	public ReflectionLocation getReflectionLocation() {
		return reflectionLocation;
	}
	/**
	 * Set the reflectionLocation
	 * @param reflectionLocation
	 */
	public void setReflectionLocation(ReflectionLocation reflectionLocation) {
		this.reflectionLocation = reflectionLocation;
	}
	/**
	 * Test draw the bounding box
	 * @deprecated
	 * @param g
	 */
	public void testDraw(Graphics g) {
		g.setColor(new Color(new Random().nextInt(255),255,48));
		g.drawRect(x, y, x2 - x, y2 - y);
	}
}
