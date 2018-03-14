package masters;

import java.util.ArrayList;

import objects.Magnet_PowerUP;
import objects.PowerUP;

/**
 * Contains a static list of all the powerUPs in the game cuurrently and methods
 * to mutate
 */

public class MasterPowerUP {

	static ArrayList<Magnet_PowerUP> powerUP_list = new ArrayList<Magnet_PowerUP>();
	public final static int MAX_POWERUP_COUNT = 1;

	public static ArrayList<Magnet_PowerUP> getList() {
		return powerUP_list;
	}

	public static void addMagnet(Magnet_PowerUP MPU) {
		if (powerUP_list.size() < MAX_POWERUP_COUNT) {
			powerUP_list.add(MPU);
		}
	}

	public static void clear() {
		powerUP_list = new ArrayList<Magnet_PowerUP>();
	}

	public static void replace(int index, Magnet_PowerUP MPU) {
		powerUP_list.set(index, MPU);
	}

	public static void remove(int i) {
		powerUP_list.remove(i);
	}

	public static void add(Magnet_PowerUP MPU) {
		powerUP_list.add(MPU);
	}

}
