package me.Cannonball.box;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import me.Cannonball.Cannonball;
import me.Cannonball.ball.BoundingBox;
import me.Cannonball.ball.ReflectionLocation;

public class Box {
	private BoundingBox bbr;
	private BoundingBox bbl;
	private BoundingBox bbt;
	private BoundingBox bbb;
	public BoundingBox[] boundingBoxes = new BoundingBox[4];
	private int x;
	private int y;
	private int size;
	/**
	 * rtb(Row To Box) defines the rows and the box object for the rows
	 * Rows can contain 6 boxs and there are 8 rows total
	 */
	private static HashMap<Box,Integer> rtb = new HashMap<Box,Integer>();
	/**
	 * Define a box
	 * @param x
	 * @param y
	 * @param size
	 */
	public Box(int x,int y,int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		rtb.put(this, 8);
		bbr = new BoundingBox(x + (Cannonball.Height / 6),y - 3,x + (Cannonball.Height / 6) + 10,y + (Cannonball.Width / 8) - 3,ReflectionLocation.RIGHT);
		bbl = new BoundingBox(x - 10,y - 3,x + 10,y + (Cannonball.Width / 8) - 3,ReflectionLocation.LEFT);
		bbt = new BoundingBox(x,y - 10,x + (Cannonball.Width / 8),y + 5,ReflectionLocation.TOP);
		bbb = new BoundingBox(x,y + (Cannonball.Width / 8),x + (Cannonball.Height / 6) + 1,y + (Cannonball.Width / 8) + 1,ReflectionLocation.BOTTOM);
		boundingBoxes[0] = bbr;boundingBoxes[1] = bbl;boundingBoxes[2] = bbt;boundingBoxes[3] = bbb;
		
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * Move the boxes down
	 */
	public static void moveRows() {
		for (int i = 0; i < rtb.size(); i++) {
			Box b = (Box) rtb.keySet().toArray()[i];
			int newRow = rtb.get(b) - 1;
			b.setY(b.getY() + (Cannonball.Width / 8));
			if(newRow <= 0) {
				System.out.println("END GAME!");
			}
			else {
				rtb.remove(b);
				rtb.put(b, newRow);
			}
		}
	}
	/**
	 * Subtract the size by 1
	 */
	public void sub() {
		size--;
	}
	/**
	 * Subtract the size by x
	 * @param num
	 */
	public void sub(int num) {
		size -= num;
	}
	private Color getColor() {
		int green = 58;
		int red = 255;
		int blue = 255;
		red = red - (int)(size / 2);
		return new Color(blue,red,green);
	}
	private Color getBorderColor() {
		int green = 68;
		int red = 224;
		int blue = 224;
		red = red - (int)(size / 2);
		return new Color(blue,red,green);
	}
	private boolean draw(Graphics g) {
		if(size == 0) {
			try {
				rtb.remove(this);
				BoxController.boxes.remove(this);
			}
			catch(Exception e) {
				rtb.remove(this);
				BoxController.boxes.remove(this);
			}
			return true;
		}
		g.setColor(getColor());
		g.fillRect(x, y,Cannonball.Height / 6,Cannonball.Width / 8);
		g.setColor(getBorderColor());
		g.drawRect(x, y,Cannonball.Height / 6,Cannonball.Width / 8);
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.MONOSPACED, 50, 50));
		g.drawString(String.valueOf(size),xOffset(),y + ((Cannonball.Height / 8) - 10));
		return false;
	}
	public static void drawAll(Graphics g) {
		/*for(Box b : rtb.keySet()) {
			b.draw(g);
		}*/
		for(Box b : rtb.keySet()) {
			
		}
	}
	private int xOffset() {
		int center = ((Cannonball.Width / 8) / 2);
		int digits = (int) (Math.log10(size) + 1);
		if(digits == 2) {
			center += 15;
		}
		return x + (center / digits) - 15;
	}
	public static void testDraw(BoundingBox b,Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(b.getX(), b.getY(), b.getX2() - b.getX(), b.getY2() - b.getY());
	}
	public void updateBoundingBox() { 
		/*bbr = new BoundingBox(x + (Cannonball.Height / 6),y,x + (Cannonball.Height / 6),y + (Cannonball.Width / 8),ReflectionLocation.RIGHT);
		bbl = new BoundingBox(x,y,x + 1,y + (Cannonball.Width / 8),ReflectionLocation.LEFT);
		bbt = new BoundingBox(x,y,x + (Cannonball.Height / 6),y + 1,ReflectionLocation.TOP);
		bbb = new BoundingBox(x,y + (Cannonball.Width / 8),x + (Cannonball.Height / 6) + 1,y + (Cannonball.Width / 8) + 1,ReflectionLocation.BOTTOM);
		boundingBoxes[0] = bbr;boundingBoxes[1] = bbl;boundingBoxes[2] = bbt;boundingBoxes[3] = bbb;
		bbr = new BoundingBox(x + (Cannonball.Height / 6) - 20,y,x + (Cannonball.Height / 6),y + (Cannonball.Width / 8),ReflectionLocation.RIGHT);
		bbl = new BoundingBox(x - 10,y,x + 1,y + (Cannonball.Width / 8),ReflectionLocation.LEFT);
		bbt = new BoundingBox(x,y - 10,x + (Cannonball.Width / 8),y + 5,ReflectionLocation.TOP);
		bbb = new BoundingBox(x,y + (Cannonball.Width / 8),x + (Cannonball.Height / 6) + 1,y + (Cannonball.Width / 8) + 1,ReflectionLocation.BOTTOM);*/
		bbr = new BoundingBox(x + (Cannonball.Height / 6),y - 3,x + (Cannonball.Height / 6) + 10,y + (Cannonball.Width / 8) - 3,ReflectionLocation.RIGHT);
		bbl = new BoundingBox(x - 10,y - 3,x + 10,y + (Cannonball.Width / 8) - 3,ReflectionLocation.LEFT);
		bbt = new BoundingBox(x,y - 10,x + (Cannonball.Width / 8),y + 5,ReflectionLocation.TOP);
		bbb = new BoundingBox(x,y + (Cannonball.Width / 8),x + (Cannonball.Height / 6) + 1,y + (Cannonball.Width / 8) + 1,ReflectionLocation.BOTTOM);
		boundingBoxes[0] = bbr;boundingBoxes[1] = bbl;boundingBoxes[2] = bbt;boundingBoxes[3] = bbb;
	}
	
}
