package objects;

/**
 * Contains a template of the magnet, or player, along with mutator methods
 */

public class Player {

	public static boolean is_flip = false;

	private float x;
	private float y;
	private boolean isInverted;

	public Player(float x, float y, boolean isInverted) {
		this.x = x;
		this.y = y;
		this.isInverted = isInverted;
	}

	public void setInverted(boolean b) {
		isInverted = b;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public void addToY(double d){
		y += d;
	}

	public boolean getInverted() {
		return isInverted;
	}

	public void setY(int i) {
		y = i;
	}

	public void setX(int i) {
		x = i;
	}
}
