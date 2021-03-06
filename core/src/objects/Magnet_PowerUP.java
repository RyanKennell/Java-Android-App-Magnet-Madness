package objects;

public class Magnet_PowerUP extends PowerUP {

	private float x;
	private float y;

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public String getName() {
		return "magnet";
	}

}
