package masters;

import java.util.ArrayList;

import objects.Coin_or_PowerUP;
import objects.PowerUP;

/**
 * Contains a static list of all the coins in the game and methods to mutate
 */

public class MasterCoin {

	private static ArrayList<Coin_or_PowerUP> coin_list = new ArrayList<Coin_or_PowerUP>();
	public static final int MAX_COIN_COUNT = 10;

	public static ArrayList<Coin_or_PowerUP> getList() {
		return coin_list;
	}

	public static void addCoin(Coin_or_PowerUP c) {
		coin_list.add(c);
	}

	public static void replace(int index, Coin_or_PowerUP c) {
		coin_list.set(index, c);
	}

	public static void clear() {
		coin_list = new ArrayList<Coin_or_PowerUP>();
	}
}
