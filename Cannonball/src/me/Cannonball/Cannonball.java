package me.Cannonball;

import javax.swing.JFrame;


public class Cannonball {
	private JFrame jFrame;
	private CannonballPanel cannonballPanel;
	public static final int Height = 600;
	public static final int Width = 800;
	public static void main(String[] args) {
		Cannonball cannonball = new Cannonball();
		cannonball.run();
	}
	private void run() {
		cannonballPanel = new CannonballPanel();
		jFrame = new JFrame();
		jFrame.setSize(Height,Width);
		jFrame.add(cannonballPanel);
		jFrame.setDefaultCloseOperation(3);
		jFrame.setResizable(false);
		jFrame.addMouseMotionListener(cannonballPanel);
		jFrame.addMouseListener(cannonballPanel);
		jFrame.setVisible(true);
	}
}
