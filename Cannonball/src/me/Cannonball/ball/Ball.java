package me.Cannonball.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import me.Cannonball.Cannonball;
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
	 * 
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
	public static void moveBalls() {
		/*for(Ball b : balls) {
			b.x += b.xDir;
			b.y += b.yDir;
		}*/
		for(Ball ball : balls) {
			for(Box b : BoxController.boxes) {
				for(BoundingBox boundingBoxT : b.boundingBoxes) {
					try {
						if(boundingBoxT.getX() == ball.x) {
						}
						if((boundingBoxT.contains(ball.getBoundingBox()) || ball.getBoundingBox().contains(boundingBoxT))) {
							System.out.println("ye");
							b.setSize(b.getSize() - 1);
							if(boundingBoxT.getReflectionLocation() == ReflectionLocation.BOTTOM) {
								ball.setY(ball.getY() + 5);
								ball.setyDir(ball.getyDir() * -1);
							}
							else if(boundingBoxT.getReflectionLocation() == ReflectionLocation.TOP) {
								ball.setY(ball.getY() - 10);
								ball.setyDir(ball.getyDir() * -1);
							}
							else if(boundingBoxT.getReflectionLocation() == ReflectionLocation.RIGHT) {
								ball.setX(ball.getxDir() < 0 ? ball.getX() + 10 : ball.getX() - 10);
								ball.setxDir(ball.getxDir() * -1);
								System.out.println("Right");
							}
							else if(boundingBoxT.getReflectionLocation() == ReflectionLocation.LEFT) {
								ball.setX(ball.getX() + 5);
								ball.setxDir(ball.getxDir() * -1);
								System.out.println("Left");
							}
						}
					} catch (StartupException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	public double getxDir() {
		return xDir;
	}
	public void setxDir(double xDir) {
		this.xDir = xDir;
	}
	public double getyDir() {
		return yDir;
	}
	public void setyDir(double yDir) {
		this.yDir = yDir;
	}
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)x,(int)y,size * 2,size * 2);
		g.drawRect(boundingBox.getX(), boundingBox.getY(), boundingBox.getX2() - boundingBox.getX(), boundingBox.getY2() - boundingBox.getY());
	}
	public static void updateBoundingBox() {
		for(Box b : BoxController.boxes) {
			b.updateBoundingBox();
		}
	}
	public void updateBallBoundingBox() {
		boundingBox = new BoundingBox((int)(x - size),(int)(y - size),(int)(x + size),(int)(y + size));
	}
	private int getY() {
		return (int) y;
	}
	private void setY(int y) {
		this.y = y;
	}
	public void checkWallCollide() {
		BoundingBox upWall = new BoundingBox(0,-100,600,0,ReflectionLocation.TOP);
		BoundingBox rightWall = new BoundingBox(600,0,700,800,ReflectionLocation.RIGHT);
		BoundingBox leftWall = new BoundingBox(-100,0,0,800,ReflectionLocation.LEFT);
		BoundingBox[] boundingBoxTs = {upWall,rightWall,leftWall};
		for(Ball ball : balls) {
			for(BoundingBox boundingBoxT : boundingBoxTs) {
				try {
					if(boundingBoxT.getX() == ball.x) {
					}
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
	public int getX() {
		return (int) x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public boolean getIsInFrame() {
		if(y > 800) {
			return false;
		}
		return true;
	}
	public void moveBall() {
		x += xDir;
		y += yDir;
	}
	
}
