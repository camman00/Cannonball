package me.Cannonball.ball;

@SuppressWarnings("serial")
public class StartupException extends Exception {
	/**
	 * Thrown when there is a StackOverflow error because the game is out of sync
	 */
	public StartupException() {
		StackTraceElement[] stackElement = new StackTraceElement[1];
		stackElement[0] = new StackTraceElement("Starting up","!","#",0);
		setStackTrace(stackElement);
	}
}
