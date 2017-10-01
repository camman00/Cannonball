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
import javax.swing.JPanel;
import javax.swing.Timer;
import me.Cannonball.ball.Ball;
import me.Cannonball.ball.BallController;
import me.Cannonball.box.Box;
import me.Cannonball.box.BoxController;

@SuppressWarnings("serial")
public class CannonballPanel extends JPanel implements ActionListener,MouseMotionListener,MouseListener {
	/*
	 * Initial ball count starting at 1
	 */
	private int ballCount = 1;
	/*
	 * mx and my for the current mouse position
	 */
	private int mx;
	private int my;
	/*
	 * Variable to see if the TimerTask is scheduled
	 */
	private boolean isScheduled;
	/*
	 * Variable to keep time
	 */
	private int time = 0;
	/*
	 * BoxController and BallController to control the boxes and balls
	 */
	private BoxController boxController;
	private BallController ballController;
	/*
	 * Two timers to control the repainting and the balls delay
	 */
	private Timer timer;
	private java.util.Timer ballTimer;
	/*
	 *Starts the game and sets the background to grey 
	 */
	public CannonballPanel() {
		ballTimer = new java.util.Timer();
		timer = new Timer(2,this);
		timer.start();
		this.setBackground(Color.DARK_GRAY);
		boxController = new BoxController();
		boxController.addNewRow();
		ballController = new BallController();
	}
	/**
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * Repaint and increment the variable
	 */
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
	/**
	 * Massive method to paint everything
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		/*
		 * Run the task
		 */
		if(isScheduled == false) {
			ballTimer.schedule(ballController, 0, 500);
			isScheduled = true;
		}
		Box.drawAll(g);
		drawLine(g);
		/*
		 * Draw the ball and update its bounding box
		 */
		ballController.drawAllBalls(g);
		ballController.updateBoundingBox();
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
	}
	//Not used
	@Override
	public void mouseDragged(MouseEvent e) {}
	/*
	 * Set mouse cords
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY() - 22;
		mx = x;
		my = y;		
	}
	/**
	 * Draw the dashed line to the cursor
	 * @param g
	 */
	private void drawLine(Graphics g) {
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setStroke(dashed);
		g2d.drawLine(Cannonball.Height / 2, Cannonball.Width, mx, my);
	}
	/*
	 * Create the balls and add a new row
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(ballController.addNewBalls()) {
			int xChange = 300 - e.getX();
			int yChange = 799 - e.getY() + 22;
			double length = Math.sqrt(Math.pow(xChange, 2) + Math.pow(yChange, 2));
			ballController.addBalls(ballCount,(xChange / length * -1) * (1 + (ballCount / 10)),(yChange / length * -1) * (1 + (ballCount / 10)));
			ballCount++;
			boxController.addNewRow();
		}
	}
	//Unused
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
