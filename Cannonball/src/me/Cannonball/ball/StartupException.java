package me.Cannonball.ball;

@SuppressWarnings("serial")
public class StartupException extends Exception {
	public StartupException() {
		StackTraceElement[] stackElement = new StackTraceElement[1];
		stackElement[0] = new StackTraceElement("Starting up","!","#",0);
		setStackTrace(stackElement);
	}
}
