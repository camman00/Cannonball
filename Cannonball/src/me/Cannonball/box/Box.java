package me.Cannonball.box;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import me.Cannonball.Cannonball;
import me.Cannonball.ball.BoundingBox;
import me.Cannonball.ball.ReflectionLocation;

public class Box {
	/*
	 * All of the bounding boxes of the box. An array of that
	 */
	private BoundingBox bbr;
	private BoundingBox bbl;
	private BoundingBox bbt;
	private BoundingBox bbb;
	public BoundingBox[] boundingBoxes = new BoundingBox[4];
	/*
	 * The x,y, and size of the box
	 */
	private int x;
	private int y;
	private int size;
	/**
	 * rtb(Row To Box) defines the rows and the box object for the rows
	 * Rows can contain 6 boxs and there are 8 rows total
	 */
	private static HashMap<Box,Integer> rtb = new HashMap<Box,Integer>();
	/**
	 * Construct a box with an x,y, and size
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
	 * Get the size
	 * @return
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Set the size
	 * @param size
	 */
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
			if(newRow <= 1) {
				JOptionPane gameOverPane = new JOptionPane();
				gameOverPane.setIcon(null);
				gameOverPane.setMessage("");
				JDialog gameOverDialog = gameOverPane.createDialog("Game over!");
				gameOverDialog.setVisible(true);
				System.exit(1);
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
	/**
	 * Get the box color
	 * TODO: Update this to make it work for more than 255
	 * @return
	 */
	private Color getColor() {
		int green = 58;
		int red = 255;
		int blue = 255;
		red = red - (int)(size * 4);
		if(red > 254) {
			blue = red - 255;
			red = 255;
		}
		return new Color(blue,red,green);
	}
	/**
	 * Get the border color
	 * TODO: Update this to make it work for more than 255
	 * @return
	 */
	private Color getBorderColor() {
		int green = 68;
		int red = 224;
		int blue = 224;
		red = red - (int)(size * 4);
		return new Color(blue,red,green);
	}
	/**
	 * Draw the box and return an array of all the boxes used for removal
	 * @param g
	 * @return
	 */
	private Box[] draw(Graphics g) {
		Box[] bb = new Box[rtb.size()];
		for (int i = 0; i < rtb.size(); i++) {
			Box b = new ArrayList<Box>(rtb.keySet()).get(i);
			if(b.size <= 0) {
				bb[i] = b;
			}
		}
		g.setColor(getColor());
		g.fillRect(x, y,Cannonball.Height / 6,Cannonball.Width / 8);
		g.setColor(getBorderColor());
		g.drawRect(x, y,Cannonball.Height / 6,Cannonball.Width / 8);
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.MONOSPACED, 50, 50));
		g.drawString(String.valueOf(size),xOffset(),y + ((Cannonball.Height / 8) - 10));
		return bb;
	}
	/**
	 * Draw all the boxes and remove the boxes that have a size < 0
	 * @param g
	 */
	public static void drawAll(Graphics g) {
		Box[][] BoxToRemove = new Box[rtb.size()][rtb.size()];
		int i = 0;
		int j = 0;
		for(Box b : rtb.keySet()) {
			j = 0;
			for(Box bb : b.draw(g)) {
				if(bb != null) {
					BoxToRemove[i][j] = bb;
				}
				j++;
			}
			i++;
		}
		for(Box[] boxArray : BoxToRemove) {
			for(Box b : boxArray) {
				if(b != null) {
					if(b.getSize() <= 0) {
						rtb.remove(b);
						BoxController.boxes.remove(b);
						System.out.println(b.size);
					}
				}
			}
		}
	}
	/**
	 * Get the xOffset for the numbers in the box
	 * @return
	 */
	private int xOffset() {
		if(size > 0) {
			int center = ((Cannonball.Width / 8) / 2);
			int digits = (int) (Math.log10(size) + 1);
			if(digits == 2) {
				center += 15;
			}
			return x + (center / digits) - 15;
		}
		return 0;
	}
	/**
	 * Test draw a bounding box
	 * @deprecated
	 * @param b
	 * @param g
	 */
	public static void testDraw(BoundingBox b,Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(b.getX(), b.getY(), b.getX2() - b.getX(), b.getY2() - b.getY());
	}
	/**
	 * Update the box BoundingBox
	 */
	public void updateBoundingBox() { 
		bbr = new BoundingBox(x + (Cannonball.Height / 6),y - 3,x + (Cannonball.Height / 6) + 10,y + (Cannonball.Width / 8) - 3,ReflectionLocation.RIGHT);
		bbl = new BoundingBox(x - 10,y - 3,x,y + (Cannonball.Width / 8) - 3,ReflectionLocation.LEFT);
		bbt = new BoundingBox(x,y - 10,x + (Cannonball.Width / 8),y + 5,ReflectionLocation.TOP);
		bbb = new BoundingBox(x,y + (Cannonball.Width / 8),x + (Cannonball.Height / 6) + 1,y + (Cannonball.Width / 8) + 1,ReflectionLocation.BOTTOM);
		boundingBoxes[0] = bbr;boundingBoxes[1] = bbl;boundingBoxes[2] = bbt;boundingBoxes[3] = bbb;
	}
	
}
