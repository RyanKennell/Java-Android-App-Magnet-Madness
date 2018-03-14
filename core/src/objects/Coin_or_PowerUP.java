package objects;

/**
 * The coin class represents the data that one coin would contain
 * 
 * @param x
 *            : The x value of the coin
 * @param y
 *            : the y value of the coin
 */

public class Coin_or_PowerUP extends PowerUP {

	private float x;
	private float y;
	private String name;
	
	public Coin_or_PowerUP(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setName(String name){
		this.name = name;
	}
	@Override
	public String getName() {
		return name;
	}
}
