package masters;

import java.util.ArrayList;

import objects.Box;

/**
 * Contains a static list of all the obstacles in the game cuurrently and
 * methods to mutate
 */

public class MasterBox {

	static ArrayList<Box> box_list = new ArrayList<Box>();
	public final static int MAX_BOX_COUNT = 10;

	public static ArrayList<Box> getBox_List() {
		return box_list;
	}

	public static void addBox(Box box) {
		if (box_list.size() < MAX_BOX_COUNT) {
			box_list.add(box);
		}
	}

	public static void clear() {
		box_list = new ArrayList<Box>();
	}

}
