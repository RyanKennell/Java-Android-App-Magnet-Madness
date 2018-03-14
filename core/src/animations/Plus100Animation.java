package animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Plus100Animation {

	private Sprite frame1 = new Sprite(new Texture(
			Gdx.files.internal("a_frame1.png")));
	private Sprite frame2 = new Sprite(new Texture(
			Gdx.files.internal("a_frame2.png")));
	private Sprite frame3 = new Sprite(new Texture(
			Gdx.files.internal("a_frame3.png")));

	public Sprite animation(float counter100) {
		if (counter100 > 45)
			return frame1;
		if (counter100 > 40)
			return frame2;
		if (counter100 > 0)
			return frame3;
		return null;
	}
}