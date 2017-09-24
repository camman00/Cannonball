package me.Cannonball.ball;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.TimerTask;

public class BallController extends TimerTask {
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private ArrayList<Ball> scheduledBalls = new ArrayList<Ball>();
	public void run() {
		if(shouldRunDelay()) {
			balls.add(scheduledBalls.get(0));
			scheduledBalls.remove(0);
		}
	}
	public void addBalls(int amount,double xDir,double yDir) {
		for (int i = 0; i < amount; i++) {
			scheduledBalls.add(new Ball(300,799,5,xDir,yDir));
		}
	}
	public boolean addNewBalls() {
		for(Ball b : balls) {
			if(b.getIsInFrame()) {
				return false;
			}
		}
		return true;
	}
	public void drawAllBalls(Graphics g) {
		for(Ball b : balls) {
			b.draw(g);
		}
	}
	public void updateBoundingBox() {
		for(Ball b : balls) {
			b.updateBallBoundingBox();
		}
	}
	public void checkAllWallCollide() {
		for(Ball b : balls) {
			b.checkWallCollide();
		}
	}
	public boolean shouldRunDelay() {
		if(scheduledBalls.isEmpty()) {
			return false;
		}
		return true;
	}
	public void moveAllBalls() {
		for(Ball b : balls) {
			b.moveBall();
		}
	}
	public boolean under800() {
		for(Ball b : balls) {
			if(b.getIsInFrame()) {
				return false;
			}
		}
		return true;
	}
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
