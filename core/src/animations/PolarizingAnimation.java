package animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PolarizingAnimation {

	private Sprite frame1 = new Sprite(new Texture(
			Gdx.files.internal("polarizing_frame_1.png")));
	private Sprite frame2 = new Sprite(new Texture(
			Gdx.files.internal("polarizing_frame_2.png")));
	private Sprite frame3 = new Sprite(new Texture(
			Gdx.files.internal("polarizing_frame_3.png")));

	public Sprite animation(float polarizing_counter) {
		if (polarizing_counter > 45)
			return frame1;
		if (polarizing_counter > 40)
			return frame2;
		if (polarizing_counter > 0)
			return frame3;
		return null;
	}

}
