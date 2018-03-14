package control;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class Input implements InputProcessor {

	/**
	 * The following methods are required implementary methods that do nothing
	 * special as of now
	 */

	@Override
	public boolean keyDown(int keycode) {
	   return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * Overrided method which takes the location of a touch and converts the
	 * coordinates to the viewport screen size
	 * 
	 */

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		//System.out.println("Mouse Event Click at " + screenX + "," + screenY);
		Vector3 worldCoordinates = Main.camera.unproject(new Vector3(screenX,
				screenY, 0));
		//System.out.println("Mouse Event Projected at " + worldCoordinates.x
		//		+ " " + worldCoordinates.y);
		Main.tap_x = worldCoordinates.x;
		Main.tap_y = worldCoordinates.y;
		if(PowerUpEffects.FramesLeftOfMagnet > 1)
			Main.is_tap = true;
		return false;
	}

	/**
	 * The following are implemented methods that do nothing special
	 */

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Main.is_tap = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
