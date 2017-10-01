package me.Cannonball.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import me.Cannonball.box.Box;
import me.Cannonball.box.BoxController;

public class Ball {
	private double x;
	private double y;
	private double xDir;
	private double yDir;
	private int size;
	private BoundingBox boundingBox;
	private static ArrayList<Ball> balls = new ArrayList<Ball>();
	/**
	 * Creates a new ball with an x,y,size,xDirection, and yDirection
	 * @param x
	 * @param y
	 * @param size
	 * @param xDir
	 * @param yDir
	 */
	public Ball(double x,double y,int size,double xDir,double yDir) {
		this.x = x;
		this.y = y;
		this.xDir = xDir;
		this.yDir = yDir;
		this.size = size;
		boundingBox = new BoundingBox((int)(x - size),(int)(y - size),(int)(x + size),(int)(y + size));
		balls.add(this);
	}
	/**
	 * Statically move all the balls and check if they collide with any boxes
	 * @exception Sometimes if this is called too early it throws a StackOverflow exception which is called a StartupException in this game
	 */
	public static void moveBalls() {
		for(Ball ball : balls) {
			for(Box b : BoxController.boxes) {
				for(BoundingBox boundingBoxT : b.boundingBoxes) {
					try {
						if((boundingBoxT.contains(ball.getBoundingBox()) || ball.getBoundingBox().contains(boundingBoxT))) {
							if(boundingBoxT.getReflectionLocation() == ReflectionLocation.BOTTOM) {
								ball.setY(ball.getY() + 5);
								ball.setyDir(ball.getyDir() * -1);
								b.setSize(b.getSize() - 1);
							}
							else if(boundingBoxT.getReflectionLocation() == ReflectionLocation.TOP) {
								ball.setY(ball.getY() - 10);
								ball.setyDir(ball.getyDir() * -1);
								b.setSize(b.getSize() - 1);
							}
							else if(boundingBoxT.getReflectionLocation() == ReflectionLocation.RIGHT) {
								ball.setX(ball.getxDir() < 0 ? ball.getX() + 10 : ball.getX() - 10);
								ball.setxDir(ball.getxDir() * -1);
								b.setSize(b.getSize() - 1);
							}
							else if(boundingBoxT.getReflectionLocation() == ReflectionLocation.LEFT) {
								//ball.setX(ball.getX() + 5);
								ball.setX(ball.getxDir() < 0 ? ball.getX() + 10 : ball.getX() - 10);
								ball.setxDir(ball.getxDir() * -1);
								b.setSize(b.getSize() - 1);
							}
						}
					} catch (StartupException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * Get the bounding box
	 * @return
	 */
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	/**
	 * Get the xDir
	 * @return
	 */
	public double getxDir() {
		return xDir;
	}
	/**
	 * Set the xDir
	 * @param xDir
	 */
	public void setxDir(double xDir) {
		this.xDir = xDir;
	}
	/**
	 * Get the yDir
	 * @return
	 */
	public double getyDir() {
		return yDir;
	}
	/**
	 * Set the yDir
	 * @param yDir
	 */
	public void setyDir(double yDir) {
		this.yDir = yDir;
	}
	/**
	 * Draw the ball
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)x,(int)y,size * 2,size * 2);
	}
	/**
	 * Update the boxes bounding boxes
	 */
	public static void updateBoundingBox() {
		for(Box b : BoxController.boxes) {
			b.updateBoundingBox();
		}
	}
	/**
	 * Update the balls bounding box
	 */
	public void updateBallBoundingBox() {
		boundingBox = new BoundingBox((int)(x - size),(int)(y - size),(int)(x + size),(int)(y + size));
	}
	/**
	 * Get the y
	 * @return
	 */
	private int getY() {
		return (int) y;
	}
	/**
	 * Set the y
	 * @param y
	 */
	private void setY(int y) {
		this.y = y;
	}
	/**
	 * Check if the ball collides with the walls
	 */
	public void checkWallCollide() {
		BoundingBox upWall = new BoundingBox(0,-100,600,0,ReflectionLocation.TOP);
		BoundingBox rightWall = new BoundingBox(600,0,700,800,ReflectionLocation.RIGHT);
		BoundingBox leftWall = new BoundingBox(-100,0,0,800,ReflectionLocation.LEFT);
		BoundingBox[] boundingBoxTs = {upWall,rightWall,leftWall};
		for(Ball ball : balls) {
			for(BoundingBox boundingBoxT : boundingBoxTs) {
				try {
					if((boundingBoxT.contains(ball.getBoundingBox()) || ball.getBoundingBox().contains(boundingBoxT))) {
						if(boundingBoxT.getReflectionLocation() == ReflectionLocation.BOTTOM) {
							ball.setY(ball.getY() + 5);
							ball.setyDir(ball.getyDir() * -1);
						}
						else if(boundingBoxT.getReflectionLocation() == ReflectionLocation.TOP) {
							ball.setyDir(ball.getyDir() < 0 ? ball.getyDir() * -1 : ball.getyDir() * 1);
						}
						else if(boundingBoxT.getReflectionLocation() == ReflectionLocation.RIGHT) {
							if(ball.getxDir() > 0) {
								ball.setxDir(ball.getxDir() * -1);
							}
						}
						else if(boundingBoxT.getReflectionLocation() == ReflectionLocation.LEFT) {
							if(ball.getxDir() < 0) {
								ball.setxDir(ball.getxDir() * -1);
							}
						}
					}
				} catch (StartupException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * Get the x
	 * @return
	 */
	public int getX() {
		return (int) x;
	}
	/**
	 * Set the x
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Get if the ball is in the frame of the game
	 * @return
	 */
	public boolean getIsInFrame() {
		if(y > 800) {
			return false;
		}
		return true;
	}
	/**
	 * Move the ball
	 */
	public void moveBall() {
		x += xDir;
		y += yDir;
	}
}
