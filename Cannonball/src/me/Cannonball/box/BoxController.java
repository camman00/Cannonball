package me.Cannonball.box;

import java.util.ArrayList;
import java.util.Random;

import me.Cannonball.Cannonball;

public class BoxController {
	private final int y = 0;
	private int size = 1;
	public static ArrayList<Box> boxes = new ArrayList<Box>();
	public void addNewRow() {
		if(size != 1)
			Box.moveRows();
		int rand = new Random().nextInt(6) + 1;
		int toRemove = new Random().nextInt(3);
		int[] skip = selectedToRemove(toRemove);
		ArrayList<Integer> toSkip = new ArrayList<Integer>();
		for (int i = 0; i < skip.length; i++) {
			toSkip.add(skip[i]);
		}
		for (int i = 0; i < rand; i++) {
			if(!(toSkip.contains(i))) {
				Box b = new Box(xConvert(i),y,size);
				boxes.add(b);
			}
		}
		size++;
	}
	private int xConvert(int place) {
		if(place == 0) {
			return 0;
		}
		else {
			return (Cannonball.Height / 6) * place;
		}
	}
	private int[] selectedToRemove(int removeCount) {
		int[] toRemove = new int[removeCount];
		for (int i = 0; i < toRemove.length; i++) {
			toRemove[i] = new Random().nextInt(6) + 1;
		}
		return toRemove;
	}
}
