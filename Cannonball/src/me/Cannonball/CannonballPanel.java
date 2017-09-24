package me.Cannonball;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.Timer;

import me.Cannonball.ball.Ball;
import me.Cannonball.ball.BallController;
import me.Cannonball.ball.BoundingBox;
import me.Cannonball.ball.StartupException;
import me.Cannonball.box.Box;
import me.Cannonball.box.BoxController;

@SuppressWarnings("serial")
public class CannonballPanel extends JPanel implements ActionListener,MouseMotionListener,MouseListener {
	private int ballCount = 1;
	private int mx;
	private int my;
	private int length;
	private boolean isScheduled;
	private int time = 0;
	private BoxController boxController;
	private BallController ballController;
	private Ball b;
	private Timer timer;
	private java.util.Timer ballTimer;
	public CannonballPanel() {
		ballTimer = new java.util.Timer();
		timer = new Timer(2,this);
		timer.start();
		this.setBackground(Color.DARK_GRAY);
		boxController = new BoxController();
		boxController.addNewRow();
		ballController = new BallController();
		b = new Ball(0,500,5,-1.7,-.5);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(time > 100000000) {
			time = 0;
		}
		if(e.getSource() == timer) {
			repaint();
			time++;
		}
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		if(isScheduled == false) {
			ballTimer.schedule(ballController, 0, 500);
			isScheduled = true;
		}
		
		/*if(ballController.shouldRunDelay() == true && isScheduled == false && ballController.under800() == true) {
			System.out.println("si senor");
			ballTimer = new java.util.Timer();
			ballTimer.schedule(ballController, 0, 500);
			isScheduled = true;
		}
		else if(ballController.shouldRunDelay() == false && isScheduled == true) {
			ballController.cancel();
			System.out.println("AAAd");
			isScheduled = false;
			ballTimer.cancel();
			ballTimer.purge();
			ballCount++;
		}*/
		/*else if(isScheduled == true) {
			if(ballController.addNewBalls()) {
				isScheduled = false;
				ballTimer.cancel();
				ballTimer.purge();
				ballController.cancel();
			}
		}
		else {
			ballTimer.cancel();
			ballTimer.purge();
			ballController.cancel();
		}
		//g.drawRect(50, 50, 10, 10);
		/*
		 * Draw all boxes and draw mouse line
		 */
		Box.drawAll(g);
		drawLine(g);
		/*
		 * Draw the ball and update its bounding box
		 */
		ballController.drawAllBalls(g);
		ballController.updateBoundingBox();
		//b.draw(g);
		//b.updateBallBoundingBox();
		/*
		 * Move the balls and update there bounding box
		 */
		Ball.updateBoundingBox();
		Ball.moveBalls();
		/*
		 * Check if the ball hits a wall
		 */
		ballController.checkAllWallCollide();
		ballController.moveAllBalls();
		//b.checkWallCollide();
		//Box.testDraw(Box.bbr, g);
		//Box.testDraw(Box.bbr, g);
	}
	/*private void test(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Cannonball.Height / 6, Cannonball.Width / 8);
		g.setColor(Color.BLUE);
		g.fillRect((Cannonball.Width / 8) * 1, 0, Cannonball.Height / 6, Cannonball.Width / 8);
	}*/
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY() - 22;
		mx = x;
		my = y;
		double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		int normalizeLength = 1;
		x /= length;
		y /= length;
		this.length = (int) length;
		
	}
	private void drawLine(Graphics g) {
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setStroke(dashed);
		g2d.drawLine(Cannonball.Height / 2, Cannonball.Width, mx, my);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(ballController.addNewBalls()) {
			int xChange = 300 - e.getX();
			int yChange = 799 - e.getY() + 22;
			double length = Math.sqrt(Math.pow(xChange, 2) + Math.pow(yChange, 2));
			ballController.addBalls(ballCount,(xChange / length * -1),(yChange / length * -1));
			ballCount++;
			boxController.addNewRow();
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
