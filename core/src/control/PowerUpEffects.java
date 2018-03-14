package control;

import com.badlogic.gdx.Gdx;

public class PowerUpEffects {

	public static float FramesLeftOfMagnet = 10 * 60;
	public static float FramesRemovedPerSec = 60;
	public static boolean isActive_magnet = true;
	
	/**
	 * Updates all effects of the power ups that are active and tests if they
	 * should remain active
	 * 
	 * @see render()
	 */

	public void update_all() {
		if (isActive_magnet) {
			FramesLeftOfMagnet -= FramesRemovedPerSec * Gdx.graphics.getDeltaTime();
		}

		if (FramesLeftOfMagnet <= 0) {
			isActive_magnet = false;
		}

	}

	public static void setIsActiveMagnet(boolean b) {
		isActive_magnet = b;
		FramesLeftOfMagnet = 10 * 60;
	}
}
