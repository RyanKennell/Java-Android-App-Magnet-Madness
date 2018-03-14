package control;

import java.util.ArrayList;
import java.util.Random;

import masters.MasterBox;
import masters.MasterCoin;
import masters.MasterPowerUP;
import objects.Box;
import objects.Coin_or_PowerUP;
import objects.Magnet_PowerUP;
import objects.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;

public class Logic {

	private Random r = new Random();
	private float speedPerSec = 360;
	private int indexToRemove;
	
	/**
	 * Master method for the class Controls the logic of the game by calling
	 * minor methods within the class
	 * 
	 * @param p_sprite
	 *            : the magnet sprite
	 * @param b_sprite
	 *            : the closest block sprite
	 * @param player
	 *            : the Player Object
	 * @see render()
	 */

	public void update_all(Sprite p_sprite, Sprite b_sprite, Player player) {
	    //TODO here
	    
		if(coin_collision(p_sprite, b_sprite, player))
			create_new_coin(indexToRemove, MasterCoin.getList().get(indexToRemove).getX());
		powerup_collision(player);

		move_right((float) Main.deltaTime);
		move_coin_right((float) Main.deltaTime);
		move_powerup_right((float) Main.deltaTime);
		speedPerSec += 5 * (float) Main.deltaTime;
	}

	/**
	 * Moves boxes to the left unless the box exceeds the maximum distance it
	 * can move where it is scootched back over to the right of the screen for
	 * reuse
	 * 
	 * @see update_all()
	 */

	private void move_right(float alpha) {
		for (int i = 0; i < MasterBox.getBox_List().size(); i++) {
			MasterBox
					.getBox_List()
					.get(i)
					.setX((float) (MasterBox.getBox_List().get(i).getX() - (speedPerSec * alpha)));
			if (MasterBox.getBox_List().get(i).getX() < -250) {
				MasterBox.getBox_List().get(i)
						.setX(MasterBox.getBox_List().get(i).getX() + 2500);
				if (r.nextInt(2) == 1) {
					MasterBox.getBox_List().get(i).setY(160);
				} else {
					MasterBox.getBox_List().get(i).setY(225);
				}
			}
		}
	}
	
	
	/**
	 * 
	 * Tests for a collision with the magnet and the box
	 * 
	 * @param character
	 *            : magnet sprite object
	 * @param box
	 *            : box sprite object
	 * @param p
	 *            : Player object
	 * @return : true if their is a collision, false other wise
	 * 
	 * @see render()
	 */
	public boolean collision(Sprite character, Sprite box, Player p) {
		for (int i = 0; i < MasterBox.getBox_List().size(); i++) {
			float x = MasterBox.getBox_List().get(i).getX();
			float y = MasterBox.getBox_List().get(i).getY();
			if (p.getX() + 80 > x && p.getX() < x + 40) {
				if (!p.getInverted() && y == 225 || p.getInverted() && y == 160) {
					return true;
				}
			}
		}
		return false;
	}

	
	/**
	 * 
	 * Takes a coin which has been collected and creates a 'new' coin
	 * 
	 * @param index
	 *            : index of the coin being replaced
	 * @param x
	 *            : previous x value of the coin being replaced
	 * 
	 * @see coin_collision()
	 */

	private void create_new_coin(int index, float x) {
		Coin_or_PowerUP c = MasterCoin.getList().get(index);
		if (r.nextInt(50) == 1) {
			c.setName("doubleCoins");
			if (r.nextInt(2) == 1) {
				c.setY(160);
			} else {
				c.setY(225);
			}
			c.setX(250 * MasterCoin.MAX_COIN_COUNT + x);
			MasterCoin.replace(index, c);
		} else if (r.nextInt(100) == 1) {
				c.setName("tripleCoins");
				if (r.nextInt(2) == 1) {
					c.setY(160);
				} else {
					c.setY(225);
				}
				c.setX(250 * MasterCoin.MAX_COIN_COUNT + x);
				MasterCoin.replace(index, c);
		}else if (r.nextInt(20) == 1) {
			int val = r.nextInt(3);
			if (val == 0)
				c.setName("cherry");
			else if (val == 1)
				c.setName("strawberry");
			else if (val == 2)
				c.setName("pineapple");
			if (r.nextInt(2) == 1) {
				c.setY(160);
			} else {
				c.setY(225);
			}
			c.setX(250 * MasterCoin.MAX_COIN_COUNT + x);
			MasterCoin.replace(index, c);
		} else {
			if (r.nextInt(2) == 1) {
				c.setY(160);
			} else {
				c.setY(225);
			}
			c.setName("coin");
			c.setX(250 * MasterCoin.MAX_COIN_COUNT + x);
			MasterCoin.replace(index, c);
		}

	}

	/**
	 * Takes a powerup which has been collected and creates a 'new' powerup by
	 * replacing its index
	 * 
	 * @param index
	 *            : index of the coin being replaced
	 * @param x
	 *            : previous x value of the coin being replaced
	 * 
	 * @see powerup_collision()
	 */

	private void create_new_powerup(int index, float x) {
		Magnet_PowerUP MPU = MasterPowerUP.getList().get(index);
		MPU.setX(250 * (MasterCoin.MAX_COIN_COUNT - 2) + x);
		int index1 = 0;
		float closest_x = Integer.MAX_VALUE;
		for (int i = 0; i < MasterCoin.getList().size(); i++) {
			if (Math.abs(MPU.getX() - MasterCoin.getList().get(i).getX()) < closest_x) {
				index1 = i;
				closest_x = Math.abs(MPU.getX()
						- MasterCoin.getList().get(i).getX());
			}
		}
		if (MasterCoin.getList().get(index1).getY() == 160) {
			MPU.setY(225);
		} else {
			MPU.setY(160);
		}
		MasterPowerUP.replace(index, MPU);
	}

	/**
	 * Moves each coin to the left and then checks if the coin has exceeded its
	 * maximum x value, if it has, the coin is moved the the far right
	 * 
	 * @see update_all()
	 */

	private void move_coin_right(float alpha) {
		for (int i = 0; i < MasterCoin.getList().size(); i++) {
			if (MasterCoin.getList().get(i).getX() < -250) {
				MasterCoin.getList().get(i)
						.setX(MasterCoin.getList().get(i).getX() + 2500);
				if (r.nextInt(2) == 1) {
					MasterCoin.getList().get(i).setY(160);
				} else {
					MasterCoin.getList().get(i).setY(225);
				}
			}
			MasterCoin
					.getList()
					.get(i)
					.setX((float) (MasterCoin.getList().get(i).getX() - (speedPerSec * alpha)));
		}
	}

	/**
	 * Moves each powerup to the left and then checks if the coin has exceeded
	 * its maximum x value, if it has, the powerup is moved the the far right
	 * 
	 * @see update_all()
	 */

	private void move_powerup_right(float alpha) {
		for (int i = 0; i < MasterPowerUP.getList().size(); i++) {
			if (MasterPowerUP.getList().get(i).getX() < -250) {
				MasterPowerUP.getList().get(i)
						.setX(MasterPowerUP.getList().get(i).getX() + 2500);
				float closest_x = Integer.MAX_VALUE;
				int index1 = 0;
				for (int b = 0; b < MasterCoin.getList().size(); b++) {
					if (Math.abs(MasterPowerUP.getList().get(i).getX()
							- MasterCoin.getList().get(b).getX()) < closest_x) {
						index1 = b;
						closest_x = Math.abs(MasterPowerUP.getList().get(i)
								.getX()
								- MasterCoin.getList().get(b).getX());
					}
				}
				if (MasterCoin.getList().get(index1).getY() == 160) {
					MasterPowerUP.getList().get(i).setY(225);
				} else {
					MasterPowerUP.getList().get(i).setY(160);
				}
			}
			((Magnet_PowerUP) (MasterPowerUP.getList().get(i)))
					.setX((float) (((Magnet_PowerUP) (MasterPowerUP.getList()
							.get(i))).getX() - (speedPerSec * alpha)));
		}
	}

	/**
	 * Tests if the magnet is colliding with any coins if their is a collision,
	 * return true
	 * 
	 * @param character
	 *            : Magnet Sprite
	 * @param coin
	 *            : Coin Sprite
	 * @param p
	 *            : Player Object
	 * @return : if a collision is true, return true, returns false otherwise
	 * 
	 * @see update_all()
	 */

	public boolean coin_collision(Sprite character, Sprite coin, Player p) {
		for (int i = 0; i < MasterCoin.getList().size(); i++) {
			float x = MasterCoin.getList().get(i).getX();
			float y = MasterCoin.getList().get(i).getY();
			if (p.getX() + 70 > x && p.getX() < x + 40) {
				if (!p.getInverted() && y == 225 || p.getInverted() && y == 160) {
					if (MasterCoin.getList().get(i).getName().equals("coin")) {
						Main.playCoinAudio();
						if (Main.doubleCoinCounter == 0 && Main.tripleCoinCounter == 0) {
							Main.coin_count++;
						} else if(Main.tripleCoinCounter > 0){
							Main.coin_count += 3;
						}else{
							Main.coin_count += 2;
						}
					} else if (MasterCoin.getList().get(i).getName()
							.equals("cherry")
							|| MasterCoin.getList().get(i).getName()
									.equals("strawberry")
							|| MasterCoin.getList().get(i).getName()
									.equals("pineapple")) {
						Main.playFruitPowerUp();
						Main.score += 100;
						Main.counter100 = 50;
					} else if (MasterCoin.getList().get(i).getName()
							.equals("tripleCoins")) {
						Main.playCoinPowerUp();
						Main.tripleCoinCounter = 10;
					}else if (MasterCoin.getList().get(i).getName()
							.equals("doubleCoins")) {
						Main.playCoinPowerUp();
						Main.doubleCoinCounter = 10;
					}
					indexToRemove = i;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Tests if the magnet is colliding with any powerups if their is a
	 * collision, return true
	 * 
	 * @param p
	 *            : Player Object
	 * @return : if a collision is true, return true, returns false otherwise
	 * 
	 * @see update_all()
	 */

	public boolean powerup_collision(Player p) {
		for (int i = 0; i < MasterPowerUP.getList().size(); i++) {
			float x = MasterPowerUP.getList().get(i).getX();
			float y = MasterPowerUP.getList().get(i).getY();
			if (p.getX() + 70 > x && p.getX() < x + 40) {
				if (!p.getInverted() && y == 225 || p.getInverted() && y == 160) {
					PowerUpEffects.setIsActiveMagnet(true);
					create_new_powerup(i, x);
					Main.playCollectAudio();
					Main.polarizing_counter = 50;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Initially create coins, boxes, and powerups
	 * 
	 * @see create()
	 */
	public void initialize_objects() {
		create_boxes();
		create_coins();
		create_powerUPs();
	}

	/**
	 * creates the initial box locations
	 * 
	 * @see initialize_game()
	 */

	private void create_boxes() {
		Box box;
		float increment = 250;
		float y = 100;
		for (int i = 0; i < MasterBox.MAX_BOX_COUNT; i++) {
			if (r.nextInt(2) == 1) {
				y = 160;
			} else {
				y = 225;
			}
			box = new Box(increment, y);
			MasterBox.addBox(box);
			increment += 250;
		}
	}

	/**
	 * creates the initial coins
	 * 
	 * @see initialize_game()
	 * 
	 */

	private void create_coins() {
		Coin_or_PowerUP coin = null;
		float increment = 400 - Main.HEIGHT / 16;
		float y = 100;
		for (int i = 0; i < MasterCoin.MAX_COIN_COUNT; i++) {
			if (r.nextInt(2) == 1) {
				y = 160;
			} else {
				y = 225;
			}
			coin = new Coin_or_PowerUP(increment, y);
			coin.setName("coin");
			MasterCoin.addCoin(coin);
			increment += 250;
		}
	}

	/**
	 * creates the starting few powerups
	 * 
	 * @see initialize_game()
	 * 
	 */

	private void create_powerUPs() {
		int increment = 400 - Main.HEIGHT / 16;
		int y = 100;
		for (int i = 0; i < MasterPowerUP.MAX_POWERUP_COUNT; i++) {
			Magnet_PowerUP MPU = new Magnet_PowerUP();
			if (MasterCoin.getList().get(0).getY() == 160) {
				y = 225;
			} else {
				y = 160;
			}
			MPU.setX(increment);
			MPU.setY(y);
			MasterPowerUP.addMagnet(MPU);
			increment += 250;
		}
	}

	public void resetSpeedPerSec() {
		speedPerSec = 360;
	}

}
