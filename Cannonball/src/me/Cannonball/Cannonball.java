package me.Cannonball;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Cannonball {
	private JFrame jFrame;
	private CannonballPanel cannonballPanel;
	/**
	 * Width constant
	 */
	public static final int Height = 600;
	/**
	 * Height constant
	 */
	public static final int Width = 800;
	public static void main(String[] args) {
		Cannonball cannonball = new Cannonball();
		cannonball.run();
	}
	/*
	 * Create the JFrame and set it up with a extension of a JPanel
	 * I built this whole project around the Height and Width constants which were swapped the whole time xP
	 */
	private void run() {
		JOptionPane pane = new JOptionPane();
		pane.setIcon(null);
		pane.setMessage("Cannonball (Ballz 2.0 ?) \n Click to add a new row of boxes and to fire ballz \n Your score is the top level of boxes \n The boxes get below your screen you lose! \n Goodluck (score cap @ 155)");
		JDialog introDialog = pane.createDialog("Cannonball");
		introDialog.setVisible(true);
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
