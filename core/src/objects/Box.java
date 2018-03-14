package objects;

/**
 * The box class represents the data that one box would contain
 * 
 * @param x
 *            : The x value of the box
 * @param y
 *            : the y value of the box
 */

public class Box {

	private float x;
	private float y;

	public Box(float x, float y) {
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

}
