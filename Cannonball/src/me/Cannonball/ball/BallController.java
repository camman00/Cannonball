package me.Cannonball.ball;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.TimerTask;

public class BallController extends TimerTask {
	/*
	 * Arraylist<Ball> for the current balls and ones to be added
	 */
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private ArrayList<Ball> scheduledBalls = new ArrayList<Ball>();
	/**
	 * Add balls(from the queue) based on a time interval
	 */
	public void run() {
		if(shouldRunDelay()) {
			balls.add(scheduledBalls.get(0));
			scheduledBalls.remove(0);
		}
	}
	/**
	 * Add a specific amount of balls to the queue
	 * @param amount
	 * @param xDir
	 * @param yDir
	 */
	public void addBalls(int amount,double xDir,double yDir) {
		for (int i = 0; i < amount; i++) {
			scheduledBalls.add(new Ball(300,799,5,xDir,yDir));
		}
	}
	/**
	 * Evaluates if you can add new balls
	 * @return
	 */
	public boolean addNewBalls() {
		for(Ball b : balls) {
			if(b.getIsInFrame()) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Draw all the balls
	 * @param g
	 */
	public void drawAllBalls(Graphics g) {
		for (int i = 0; i < balls.size(); i++) {
			balls.get(i).draw(g);
		}
	}
	/**
	 * Update all the balls bounding boxes
	 */
	public void updateBoundingBox() {
		for(Ball b : balls) {
			b.updateBallBoundingBox();
		}
	}
	/**
	 * Check if the balls collide with a wall
	 */
	public void checkAllWallCollide() {
		for (int i = 0; i < balls.size(); i++) {
			balls.get(i).checkWallCollide();
		}
	}
	/**
	 * Check if the balls should run a delay
	 * @return
	 */
	public boolean shouldRunDelay() {
		if(scheduledBalls.isEmpty()) {
			return false;
		}
		return true;
	}
	/**
	 * Move all the balls
	 */
	public void moveAllBalls() {
		for(Ball b : balls) {
			b.moveBall();
		}
	}
	/**
	 * Check if all the balls are in the frame
	 * @return
	 */
	public boolean under800() {
		for(Ball b : balls) {
			if(b.getIsInFrame()) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Remove unnecessary balls from the game
	 * @param current
	 * @return
	 */
	public int clean(int current) {
		for (int i = 0; i < balls.size(); i++) {
			if(balls.get(i).getIsInFrame() == false) {
				balls.remove(i);
			}
		}
		if(balls.size() == 0) {
			return current += 1;
		}
		return 0;
	}
}
