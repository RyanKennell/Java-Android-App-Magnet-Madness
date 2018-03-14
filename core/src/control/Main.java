package control;

import java.util.Random;

import masters.MasterBox;
import masters.MasterCoin;
import masters.MasterPowerUP;
import objects.Coin_or_PowerUP;
import objects.Magnet_PowerUP;
import objects.Player;
import animations.InvertedPlus100Animation;
import animations.Plus100Animation;
import animations.PolarizingAnimation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * @author kenne
 *
 */
/**
 * @author kenne
 *
 */
public class Main extends ApplicationAdapter {
	
	private SpriteBatch batch;
	private BitmapFont font;

	private StringBuffer scoreBuffer = new StringBuffer("Score :: ");
	private StringBuffer magnetBuffer = new StringBuffer("x ");
	private StringBuffer coinBuffer = new StringBuffer("x ");

	private Sprite Player_Sprite;
	private Sprite p_sprite;
	private Sprite p_sprite2;
	private Sprite p_sprite3;
	private Sprite p_sprite4;
	private Sprite p_sprite5;

	private Sprite the_background;
	private Sprite backgroundThumbnail;
	private Sprite background_field_thumbnail;
	private Sprite background_desert_thumbnail;
	private Sprite background_mountains_thumbnail;
	private Sprite background_cave_thumbnail;
	private Sprite background_pyramid_thumbnail;
	private Sprite background_space_thumbnail;

	private Sprite background;
	private Sprite background_field;
	private Sprite background_desert;
	private Sprite background_mountains;
	private Sprite background_cave;
	private Sprite background_pyramid;
	private Sprite background_space;

	private Sprite tree;
	private Sprite ground;
	private Sprite levelmenu;
	private Sprite checkmark;
	private Sprite magnet_powerup;
	private Sprite b_sprite;
	private Sprite rock;
	private Sprite shop_background;
	private Sprite splash;
	private Sprite cherry;
	private Sprite strawberry;
	private Sprite pineapple;
	private Sprite menu;
	private Sprite sound_on;
	private Sprite sound_off;
	private Sprite gameover;
	private Sprite magnet;
	private Sprite coin;
	private Sprite temp_shop_mag;
	private Sprite buy_button;
	private Sprite use_button;
	private Sprite play_button;
	private Sprite locked;
	private Sprite confirmation;
	private Sprite thumbnail;
	private Sprite cactus;
	private Sprite obstacle;
	private Sprite bush;
	private Sprite spike;
	private Sprite doubleCoins;
	private Sprite tripleCoins;
	private Sprite menu_htp;
	private Sprite menu_htp2;
	private Sprite temp_object;

	private Music musicBackgroundMenu;
	private Music musicBackground2;
	private Music musicBackground3;
	private Music musicBackground4;
	private Music musicShopBackground;

	private Sound gameoverAudio;
	private Sound clickAudio;

	public static Sound magnetCollectedAudio;

	private static Sound fruitPowerUp;
	private static Sound coinPowerUpAudio;
	private static Sound coinCollectedAudio;

	public static OrthographicCamera camera;
	public static Viewport viewport;

	public static double tap_x = 0;
	public static double tap_y = 0;

	public static boolean is_tap = false;

	// John Cena meme
	private boolean areYouSureAboutThat = false;

	private final Random rand = new Random();
	private final Logic logic = new Logic();
	private final Input input = new Input();

	private Player player;
	private final Score score_keeper = new Score();
	private PowerUpEffects PEF;
	private PolarizingAnimation animationPolarizing;
	private Plus100Animation plus100animation;
	private InvertedPlus100Animation invertedplus100animation;

	private boolean isPage1 = true;

	private static float volume = 1f;

	private final int SHOP_MIN = 1;
	private final int SHOP_MAX = 5;

	private final int LEVEL_MIN = 1;
	private final int LEVEL_MAX = 7;

	public static final int HEIGHT = 640;
	public static final int WIDTH = 480;
	private int track;
	private int menu_state = 0; // 0 splash 1 menu 2 game 3 gameover 4 shop
	private int shop_id = 1;
	private int level_id = 1;
	private int current_mag = 1;
	private int isFirstFrame = 0;
	private int tipNum = 0;
	static float score = 0;
	private float menu_cool = 0;
	private float game_start_cooldown = 60;
	private boolean levelPurchaseConfirm = false;

	public static int coin_count = 0;
	public static float polarizing_counter = 0;
	public static float counter100 = 0;
	public static float invertedCounter100 = 0;
	public static float doubleCoinCounter = 0;
	public static float tripleCoinCounter = 0;
    public static float step = 1.0f / 60.0f;

	public static double deltaTime = 1f / 60f;

	/**
	 * Instantiates all objects used within the game
	 */

	@Override
	public void create() {

		musicBackgroundMenu = Gdx.audio.newMusic(Gdx.files.internal("track1.wav"));
		musicBackground2 = Gdx.audio.newMusic(Gdx.files.internal("track2.wav"));
		musicBackground3 = Gdx.audio.newMusic(Gdx.files.internal("track3.wav"));
		musicBackground4 = Gdx.audio.newMusic(Gdx.files.internal("track4.wav"));
		musicShopBackground = Gdx.audio.newMusic(Gdx.files
				.internal("Item Shop.mp3"));

		fruitPowerUp = Gdx.audio.newSound(Gdx.files.internal("fruitPowerup.wav"));
		coinPowerUpAudio = Gdx.audio.newSound(Gdx.files.internal("coinPower.wav"));
		gameoverAudio = Gdx.audio.newSound(Gdx.files.internal("gameover.wav"));
		clickAudio = Gdx.audio.newSound(Gdx.files.internal("menu_click.wav"));
		coinCollectedAudio = Gdx.audio
				.newSound(Gdx.files.internal("coin8.wav"));
		magnetCollectedAudio = Gdx.audio.newSound(Gdx.files
				.internal("repolarized.wav"));

		PEF = new PowerUpEffects();
		animationPolarizing = new PolarizingAnimation();
		plus100animation = new Plus100Animation();
		invertedplus100animation = new InvertedPlus100Animation();

		temp_shop_mag = null;

		Gdx.input.setInputProcessor(input);
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("font.fnt"),
				Gdx.files.internal("font.png"), false);
		font.setColor(0, 0, 0, 1);

		player = new Player(20, 225, false);

		p_sprite = new Sprite(new Texture(Gdx.files.internal("person.png")));
		p_sprite.setPosition(player.getX(), player.getY());
		p_sprite.setSize(HEIGHT / 8, WIDTH / 8);

		p_sprite2 = new Sprite(new Texture(Gdx.files.internal("person2.png")));
		p_sprite2.setPosition(player.getX(), player.getY());
		p_sprite2.setSize(HEIGHT / 6.3f, WIDTH / 7);

		p_sprite3 = new Sprite(new Texture(Gdx.files.internal("person3.png")));
		p_sprite3.setPosition(player.getX(), player.getY());
		p_sprite3.setSize(HEIGHT / 6.75f, WIDTH / 8);

		p_sprite4 = new Sprite(new Texture(Gdx.files.internal("person4.png")));
		p_sprite4.setPosition(player.getX(), player.getY());
		p_sprite4.setSize(HEIGHT / 7, WIDTH / 8);

		p_sprite5 = new Sprite(new Texture(Gdx.files.internal("person5.png")));
		p_sprite5.setPosition(player.getX(), player.getY());
		p_sprite5.setSize(HEIGHT / 8, WIDTH / 8);

		backgroundThumbnail = new Sprite(new Texture(
				Gdx.files.internal("background_thumbnail.png")));
		backgroundThumbnail.setSize(430, 85);

		background_field_thumbnail = new Sprite(new Texture(
				Gdx.files.internal("background_field_thumbnail.png")));
		background_field_thumbnail.setSize(430, 85);

		background_desert_thumbnail = new Sprite(new Texture(
				Gdx.files.internal("background_desert_thumbnail.png")));
		background_desert_thumbnail.setSize(430, 85);

		background_mountains_thumbnail = new Sprite(new Texture(
				Gdx.files.internal("background_mountains_thumbnail.png")));
		background_mountains_thumbnail.setSize(430, 85);

		background_cave_thumbnail = new Sprite(new Texture(
				Gdx.files.internal("background_cave_thumbnail.png")));
		background_cave_thumbnail.setSize(430, 85);

		background_pyramid_thumbnail = new Sprite(new Texture(
				Gdx.files.internal("background_pyramid_thumbnail.png")));
		background_pyramid_thumbnail.setSize(430, 85);

		background_space_thumbnail = new Sprite(new Texture(
				Gdx.files.internal("background_space_thumbnail.png")));
		background_space_thumbnail.setSize(430, 85);

		background_field = new Sprite(new Texture(
				Gdx.files.internal("background_field.png")));
		background_field.setSize(HEIGHT, WIDTH);

		background_desert = new Sprite(new Texture(
				Gdx.files.internal("background_desert.png")));
		background_desert.setSize(HEIGHT, WIDTH);

		background_mountains = new Sprite(new Texture(
				Gdx.files.internal("background_mountains.png")));
		background_mountains.setSize(HEIGHT, WIDTH);

		background_cave = new Sprite(new Texture(
				Gdx.files.internal("background_cave.png")));
		background_cave.setSize(HEIGHT, WIDTH);

		background_pyramid = new Sprite(new Texture(
				Gdx.files.internal("background_pyramid.png")));
		background_pyramid.setSize(HEIGHT, WIDTH);

		background_space = new Sprite(new Texture(
				Gdx.files.internal("background_space.png")));
		background_space.setSize(HEIGHT, WIDTH);

		magnet = new Sprite(new Texture(Gdx.files.internal("magnet.png")));
		magnet.setSize(HEIGHT, WIDTH / 19);
		magnet.setPosition(0, 200);

		locked = new Sprite(new Texture(Gdx.files.internal("locked.png")));
		locked.setSize(HEIGHT / 4, WIDTH / 4);

		levelmenu = new Sprite(new Texture(Gdx.files.internal("levelmenu.png")));
		levelmenu.setSize(HEIGHT, WIDTH);

		menu_htp = new Sprite(new Texture(
				Gdx.files.internal("menu_howtoplay.png")));
		menu_htp.setSize(HEIGHT, WIDTH);

		menu_htp2 = new Sprite(new Texture(
				Gdx.files.internal("menu_howtoplay_pg2.png")));
		menu_htp2.setSize(HEIGHT, WIDTH);

		doubleCoins = new Sprite(new Texture(
				Gdx.files.internal("doubleCoins.png")));
		doubleCoins.setSize(HEIGHT / 16, WIDTH / 16);

		tripleCoins = new Sprite(new Texture(
				Gdx.files.internal("Triplecoin.png")));
		tripleCoins.setSize(HEIGHT / 16, WIDTH / 16);

		checkmark = new Sprite(new Texture(Gdx.files.internal("checkmark.png")));
		checkmark.setSize(HEIGHT / 8, WIDTH / 8);

		coin = new Sprite(new Texture(Gdx.files.internal("coin.png")));
		coin.setSize(HEIGHT / 16, WIDTH / 16);

		cherry = new Sprite(new Texture(Gdx.files.internal("cherry.png")));
		cherry.setSize(HEIGHT / 16, WIDTH / 16);

		strawberry = new Sprite(new Texture(
				Gdx.files.internal("strawberry.png")));
		strawberry.setSize(HEIGHT / 16, WIDTH / 16);

		pineapple = new Sprite(new Texture(Gdx.files.internal("pineapple.png")));
		pineapple.setSize(HEIGHT / 16, WIDTH / 16);

		magnet_powerup = new Sprite(new Texture(
				Gdx.files.internal("magnet_powerup.png")));
		magnet_powerup.setSize(HEIGHT / 16, WIDTH / 16);

		background = new Sprite(new Texture(
				Gdx.files.internal("background.png")));
		background.setSize(HEIGHT, WIDTH);

		shop_background = new Sprite(
				new Texture(Gdx.files.internal("shop.png")));
		shop_background.setSize(HEIGHT, WIDTH);

		gameover = new Sprite(new Texture(Gdx.files.internal("gameover.png")));
		gameover.setSize(HEIGHT, WIDTH);

		splash = new Sprite(new Texture(
				Gdx.files.internal("kennellcoding_logo.png")));
		splash.setSize(HEIGHT, WIDTH);

		menu = new Sprite(new Texture(Gdx.files.internal("menu_screen.png")));
		menu.setSize(HEIGHT, WIDTH);

		b_sprite = new Sprite(new Texture(Gdx.files.internal("box.png")));
		b_sprite.setSize(HEIGHT / 12, WIDTH / 12);

		sound_on = new Sprite(new Texture(Gdx.files.internal("sound_on.png")));
		sound_on.setSize(HEIGHT / 12, WIDTH / 12);
		sound_on.setPosition(560, 440);

		sound_off = new Sprite(new Texture(Gdx.files.internal("sound_off.png")));
		sound_off.setSize(HEIGHT / 12, WIDTH / 12);
		sound_off.setPosition(560, 440);

		cactus = new Sprite(new Texture(Gdx.files.internal("cactus.png")));
		cactus.setSize(HEIGHT / 12, WIDTH / 8);

		tree = new Sprite(new Texture(Gdx.files.internal("tree_obstacle.png")));
		tree.setSize(HEIGHT / 12, WIDTH / 8);

		bush = new Sprite(new Texture(Gdx.files.internal("bush.png")));
		bush.setSize(HEIGHT / 12, WIDTH / 12);

		rock = new Sprite(new Texture(Gdx.files.internal("rock.png")));
		rock.setSize(HEIGHT / 12, WIDTH / 12);

		spike = new Sprite(new Texture(Gdx.files.internal("spike.png")));
		spike.setSize(HEIGHT / 12, WIDTH / 12);

		buy_button = new Sprite(new Texture(
				Gdx.files.internal("buy_button.png")));
		buy_button.setSize(HEIGHT / 3, WIDTH / 8);

		use_button = new Sprite(new Texture(
				Gdx.files.internal("use_button.png")));
		use_button.setSize(HEIGHT / 3, WIDTH / 8);

		play_button = new Sprite(new Texture(
				Gdx.files.internal("play_button.png")));
		play_button.setSize(HEIGHT / 3, WIDTH / 8);

		confirmation = new Sprite(
				new Texture(Gdx.files.internal("confirm.png")));
		confirmation.setSize(HEIGHT, WIDTH);

		current_mag = score_keeper.getLastMagnet();
		System.out.println(score_keeper.getLastMagnet());
		Player_Sprite = new Sprite(getShopSprite(current_mag + 1));
		reset_all();
		score_keeper.setSound(1);
		camera = new OrthographicCamera();
		viewport = new StretchViewport(HEIGHT, WIDTH, camera);
		viewport.apply();

		camera.position.set(camera.viewportWidth / 2,
				camera.viewportHeight / 2, 0);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}

	/**
	 * Render method, main game loop the menu_state represents the current
	 * 'state' of the game with the following 'states' 0 : splash screen 1 :
	 * starting menu screen 2 : game 3 : game over screen 4 : game shop menu
	 *
	 *
	 */

	@Override
	public void render() {
		//System.out.println("FPS :: " + Gdx.graphics.getFramesPerSecond());
		deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1/45f);
	    game_state();
	    render_method();

	}

	/**
	 * Manages the state the game is in
	 * 
	 * state 0 : splash screen
	 * state 1 : main menu
	 * state 2 : game
	 * state 3 : game over menu
	 * state 4 : shop menu
	 * state 5 : level menu
	 * state 6 : how to play menu
	 */
	private void game_state() {
		if (isFirstFrame == 0) {
			isFirstFrame++;
			score_keeper.firstTime();
		} else if (menu_state == 0) {
			// splash
			if (menu_cool <= 90)
				menu_cool += (30 * Gdx.graphics.getDeltaTime());
			else
				menu_state = 1;
		} else if (menu_state == 1) {
			// main menu
			if (is_tap) {
				//TODO edit here
				if (tap_x > 190 && tap_x < 440 && tap_y > 140 && tap_y < 200) {
					is_tap = false;
					menu_state = 5;
					clickAudio.play(volume);
				} else if (tap_x > 380 && tap_x < 590 && tap_y > 40
						&& tap_y < 100) {
					clickAudio.play(volume);
					is_tap = false;
					menu_state = 4;
					endTrack(false);
					musicShopBackground.setVolume(volume);
					musicShopBackground.play();
					musicShopBackground.setLooping(true);
				} else if (tap_x > 35 && tap_x < 240 && tap_y > 45
						&& tap_y < 100) {
					is_tap = false;
					menu_state = 6;
					clickAudio.play(volume);
				} else if (tap_x > 540 && tap_x < 640 && tap_y > 440
						&& tap_y < 480) {
					is_tap = false;
					if (score_keeper.getSound() == 1) {
						volume = 0;
						endTrack(false);
					} else {
						volume = 1f;
						music();
					}
					score_keeper.setSound(1 - score_keeper.getSound());
					volume = score_keeper.getSound();
					music();
				}

			}
		} else if (menu_state == 2) {

			if (game_start_cooldown > 0) {
				game_start_cooldown -= 60 * Gdx.graphics.getDeltaTime();
			} else {
				// game
				if (PowerUpEffects.FramesLeftOfMagnet <= 1) {
					if (player.getInverted()) {
						player.addToY(-7);
					} else {
						player.addToY(7);
					}
				}

				if (logic.collision(p_sprite, b_sprite, player)
						|| player.getY() > 550 || player.getY() < 0) {
					levelPurchaseConfirm = false;
					font.setColor(0, 0, 0, 1);
					endTrack(true);
					score_keeper.updateBestScore((int) (score), level_id);
					score_keeper.updateTotalCoins(coin_count, true);
					tipNum = (int) (Math.random() * 10) + 1;
					menu_state = 3;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else if (PowerUpEffects.FramesLeftOfMagnet > .9){
					score += 100 * Gdx.graphics.getDeltaTime();
					update_sprites();
					logic.update_all(p_sprite, b_sprite, player);
					PEF.update_all();
				}

			}
		} else if (menu_state == 3) {
			// game over
			if (is_tap) {
				if (tap_x > 80 && tap_x < 280 && tap_y > 110 && tap_y < 160
						|| Gdx.input.isKeyPressed(Keys.BACK)) {
					clickAudio.play(volume);
					is_tap = false;
					menu_state = 1;
					music();
					reset_all();
				}
			}
		} else if (menu_state == 4) {
			// menu
			if (!areYouSureAboutThat) {
				if (tap_x < 100 && tap_y > 420) {
					clickAudio.play(volume);
					is_tap = false;
					menu_state = 1;
					musicShopBackground.stop();
					music();
				}
				if (tap_x > 0 && tap_x < 90 && tap_y > 220 && tap_y < 330) {
					clickAudio.play(volume);
					tap_x = 0;
					tap_y = 0;
					if (shop_id > SHOP_MIN) {
						shop_id--;
					}
				}

				if (tap_x > 545 && tap_x < 640 && tap_y > 220 && tap_y < 325) {
					clickAudio.play(volume);
					tap_x = 0;
					tap_y = 0;
					if (shop_id < SHOP_MAX) {
						shop_id++;
					}
				}

				if (tap_x > 210 && tap_x < 420 && tap_y > 125 && tap_y < 185) {
					clickAudio.play(volume);
					if (score_keeper.isMagnetUnlocked(shop_id)) {
						clickAudio.play(volume);
						tap_x = 0;
						tap_y = 0;
						Player_Sprite = getShopSprite(shop_id);
						current_mag = shop_id;
					} else {
						areYouSureAboutThat = true;
					}
				}
			} else {
				// confirmation screen
				if (tap_x > 60 && tap_x < 225 && tap_y > 40 && tap_y < 100) {
					// Good to go buy it
					if (score_keeper.getCoinCount() >= getValueOfMagnet(shop_id)) {
						score_keeper.updateMagnet(shop_id, 0);
						score_keeper.updateTotalCoins(
								getValueOfMagnet(shop_id), false);
						areYouSureAboutThat = false;
					}
				} else if (tap_x > 425 && tap_x < 585 && tap_y > 40
						&& tap_y < 100) {
					// Cancel
					areYouSureAboutThat = false;
				}
			}
		} else if (menu_state == 5) {
			System.out.println(tap_x + " " + tap_y);
			// level select
			if (levelPurchaseConfirm) {
				if (tap_x > 50 && tap_x < 215 && tap_y > 25 && tap_y < 100) {
					updateUnlockedLevels(level_id);
					levelPurchaseConfirm = false;
				}
				if (tap_x > 425 && tap_x < 585 && tap_y > 25 && tap_y < 100) {
					levelPurchaseConfirm = false;
				}
			} else {
				if (tap_x > 100 && tap_x < 190 && tap_y > 160 && tap_y < 250) {
					if (level_id > LEVEL_MIN) {
						clickAudio.play(volume);
						level_id--;
						tap_x = 0;
						tap_y = 0;
					}
				}
				if (tap_x > 420 && tap_x < 540 && tap_y > 160 && tap_y < 250) {
					if (level_id < LEVEL_MAX) {
						clickAudio.play(volume);
						level_id++;
						tap_x = 0;
						tap_y = 0;
					}
				}
				if (tap_x > 0 && tap_x < 75 && tap_y > 455 && tap_y < 480) {
					clickAudio.play(volume);
					menu_state = 1;
				}

				if (tap_x < 420 && tap_x > 200 && tap_y > 75 && tap_y < 130) {
					if (score_keeper.isLevelUnlocked(level_id)) {
						clickAudio.play(volume);
						the_background = getBackground(level_id);
						obstacle = getObstacle(level_id);
						menu_state = 2;
						musicBackgroundMenu.stop();
						music();
					} else {
						levelPurchaseConfirm = true;
					}
				}
			}
		} else if (menu_state == 6) {
			// How to play menu
			if (isPage1) {
				if (tap_x > 0 && tap_x < 75 && tap_y < 480 && tap_y > 450) {
					if (isPage1) {
						menu_state = 1;
					}

					is_tap = false;
					clickAudio.play(volume);
				} else if (tap_x < 640 && tap_x > 640 - 75 && tap_y < 480
						&& tap_y > 450) {
					isPage1 = false;
					is_tap = false;
					clickAudio.play(volume);
				}
			} else {
				if (tap_x > 0 && tap_x < 75 && tap_y < 480 && tap_y > 450) {
					isPage1 = true;
					is_tap = false;
					clickAudio.play(volume);
					tap_x = 0;
					tap_y = 0;
				}
			}

		}
	}

	/**
	 * Upon hitting the play again option, this method resets all resources
	 *
	 * @see render()
	 */

	private void reset_all() {
		musicBackgroundMenu.setVolume(volume);
		musicBackgroundMenu.play();
		musicBackgroundMenu.setLooping(true);
		track = 4;
		doubleCoinCounter = 0;
		tripleCoinCounter = 0;
		logic.resetSpeedPerSec();
		MasterBox.clear();
		MasterPowerUP.clear();
		MasterCoin.clear();
		is_tap = false;
		if (player.getInverted())
			player.setInverted(false);
		player.setX(20);
		player.setY(225);
		if (Player_Sprite.isFlipY()) {
			Player_Sprite.flip(false, true);
			Player_Sprite.setPosition(player.getX(), 225);
		}
		resetPowerUp();
		menu_cool = 0;
		counter100 = 0;
		polarizing_counter = 0;
		score = 0;
		coin_count = 0;
		game_start_cooldown = 60;
		logic.initialize_objects();
	}

	/**
	 * Updates if levels are unlocked
	 *
	 * @see render();
	 */

	private void updateUnlockedLevels(int level_id) {
		if (!score_keeper.isLevelUnlocked(level_id)) {
			if (score_keeper.getCoinCount() >= getScoreRequired(level_id)) {
				score_keeper.unlockLevel(getScoreRequired(level_id), level_id);
			}
		}
	}

	/**
	 * resets all powerup data for new game
	 *
	 * @see reset_all()
	 */

	private void resetPowerUp() {
		PowerUpEffects.FramesLeftOfMagnet = 10 * 60;
		PowerUpEffects.isActive_magnet = true;
	}

	/**
	 *
	 * After receiving a id which represents a sprite, return the Sprite that is
	 * represented by that id value
	 *
	 * @param id
	 *            : the Sprite id of the currently displayed magnet
	 * @return : The Sprite Object of the current id
	 *
	 * @see render_method()
	 */

	private Sprite getShopSprite(int id) {
		switch (id) {
		case 1:
			return p_sprite;
		case 2:
			return p_sprite2;
		case 3:
			return p_sprite3;
		case 4:
			return p_sprite4;
		case 5:
			return p_sprite5;
		default:
			return p_sprite;
		}
	}

	/**
	 * Changes the orientation of the sprite depending on if the Sprite needs to
	 * be flipped vertically and offset
	 *
	 * @see render()
	 */

	private void update_sprites() {
		Player_Sprite.setPosition(player.getX(), player.getY());
		if (Gdx.input.justTouched()) {
			if (is_tap) {
				Player_Sprite.flip(false, true);
			} else {
				Player_Sprite.flip(false, false);
			}
			if (Player_Sprite.isFlipY()) {
				player.setInverted(true);
			} else {
				player.setInverted(false);
			}
		}
	}

	/**
	 * Picks a random track to play for background music
	 *
	 * @see render()
	 */

	private void music() {
		track = rand.nextInt(3);
		volume = score_keeper.getSound();

		if (menu_state == 1) {
			musicBackgroundMenu.setVolume(volume);
			musicBackgroundMenu.play();
			musicBackgroundMenu.setLooping(true);
			return;
		}
		if (track == 0) {
			musicBackground2.setVolume(volume);
			musicBackground2.play();
			musicBackground2.setLooping(true);
		} else if (track == 1) {
			musicBackground3.setVolume(volume);
			musicBackground3.play();
			musicBackground3.setLooping(true);
		} else if (track == 2) {
			musicBackground4.setVolume(volume);
			musicBackground4.play();
			musicBackground4.setLooping(true);
		}
	}

	/**
	 * Ends the currently playing audio track and plays the gameover tune
	 *
	 * @param isGameover
	 *            : tells if the gameover noise should play
	 *
	 * @see render()
	 */

	private void endTrack(boolean isGameover) {
		if (track == 0)
			musicBackground2.stop();
		else if (track == 1)
			musicBackground3.stop();
		else if (track == 2)
			musicBackground4.stop();
		else if (track == 4)
			musicBackgroundMenu.stop();

		if (isGameover) {
			gameoverAudio.play(volume);
		}
	}

	/**
	 * gets the value of magnets locked in the shop
	 *
	 * @param index
	 *            : index of the magnet
	 *
	 * @return the price of the magnet
	 * @see render_method()
	 */

	private int getValueOfMagnet(int index) {

		if (index == 2)
			return 250;
		else if (index == 3)
			return 1000;
		else if (index == 4)
			return 2500;
		else if (index == 5)
			return 5000;
		else
			return 0;
	}

	/**
	 * gets the asset of the correct thumbnail
	 *
	 * @param level_id
	 *            : the level of the thumbnail
	 *
	 * @return the thumbnail of the level
	 * @see render_method()
	 */

	private Sprite getLevel(int level_id) {
		switch (level_id) {

		case 1:
			return backgroundThumbnail;
		case 2:
			return background_field_thumbnail;
		case 3:
			return background_desert_thumbnail;
		case 4:
			return background_mountains_thumbnail;
		case 5:
			return background_pyramid_thumbnail;
		case 6:
			return background_cave_thumbnail;
		case 7:
			return background_space_thumbnail;
		default:
			return backgroundThumbnail;
		}
	}

	/**
	 * gets the asset of the correct floor
	 *
	 * @param level_id
	 *            : the level of the floor
	 *
	 * @return the ground texture for the level
	 * @see render_method()
	 */

	private Sprite getFloor(int level_id) {
		switch (level_id) {

		case 1:
			return magnet;
		case 2:
			return magnet;
		case 3:
			return magnet;
		case 4:
			return magnet;
		case 5:
			return magnet;
		case 6:
			return magnet;
		case 7:
			return magnet;
		default:
			return null;
		}
	}

	/*
	 * Gets the y offset needed to space taller obstacles
	 *
	 * @param level_id : The current level needed
	 *
	 * @return the y offset for the obstacle
	 *
	 * @see render_method()
	 */

	private int getYOffset(int level_id) {
		switch (level_id) {
		case 1:
			return 0;
		case 2:
			return 0;
		case 3:
			return 20;
		case 4:
			return 20;
		case 5:
			return 0;
		case 6:
			return 0;
		case 7:
			return 0;
		default:
			return 0;
		}
	}

	/**
	 * returns the background of the wanted level
	 *
	 * @param level_id
	 *            : the level of the world
	 *
	 * @return the sprite of the level
	 * @see render_method()
	 */

	private Sprite getBackground(int level_id) {
		switch (level_id) {

		case 1:
			return background;
		case 2:
			return background_field;
		case 3:
			return background_desert;
		case 4:
			return background_mountains;
		case 5:
			return background_pyramid;
		case 6:
			return background_cave;
		case 7:
			return background_space;
		default:
			return null;
		}
	}

	/**
	 * gets the name of the level
	 *
	 * @param level_id
	 *            : the level of the world
	 *
	 * @return name of the level
	 * @see render_method()
	 */

	private String getLevelName(int level_id) {
		switch (level_id) {

		case 1:
			return "  The Hills";
		case 2:
			return "  The Field";
		case 3:
			return " The Desert";
		case 4:
			return "The Mountain";
		case 5:
			return "The Pyramid";
		case 6:
			return "  The Cave";
		case 7:
			return "The Frontier";
		default:
			return "Error fetching name!";
		}
	}

	/**
	 * gets the name of the level
	 *
	 * @param level_id
	 *            : the level of the world
	 *
	 * @see render_method()
	 */

	private Sprite getObstacle(int level_id) {
		switch (level_id) {

		case 1:
			return b_sprite;
		case 2:
			return bush;
		case 3:
			return cactus;
		case 4:
			return tree;
		case 5:
			return spike;
		case 6:
			return b_sprite;
		case 7:
			return rock;
		default:
			return b_sprite;
		}
	}

	private boolean is_text_color_inverted(int level_id) {
		switch (level_id) {
		case 1:
			return false;
		case 2:
			return false;
		case 3:
			return true;
		case 4:
			return false;
		case 5:
			return false;
		case 6:
			return true;
		case 7:
			return true;
		default:
			return false;
		}
	}

	/*
	 * Returns the required score for the previous level in order to unlock the
	 * next
	 *
	 * @param level_id : the id of the current level being displayed
	 *
	 * @return : the score required of the previous level to unlock level_id
	 *
	 * @see updateUnlockedLevels()()
	 */

	private int getScoreRequired(int level_id) {
		
		switch (level_id) {

		case 1:
			return 0;
		case 2:
			return 1000;
		case 3:
			return 2000;
		case 4:
			return 3000;
		case 5:
			return 4000;
		case 6:
			return 5000;
		case 7:
			return 6000;
		default:
			return 0;
		}
	}

	private String getTip(int x) {
		
		switch (x) {

		case 1:
			return "Collect fruits to boost your score!";
		case 2:
			return "Collect coins to unlock magnets in the shop!";
		case 3:
			return "Watch your polarizing counter carefully!";
		case 4:
			return "Double tap the screen to quickly grab enclosed items!";
		case 5:
			return "Use triple coin powerups to maximize profit!";
		case 6:
			return "Focus on the closest obstacle in front of you!";
		case 7:
			return "Be careful! The obstacles gradually speed up!";
		case 8:
			return "Avoid falling off the magnet by grabbing blue magnets!";
		case 9:
			return "Play on different levels to experience new environments!";
		case 10:
			return "Use double coin powerups to boost profit!";
		default:
			return "";
		}
	}

	/**
	 * Determins if the +100 animation should be inverted
	 *
	 * @param the
	 *            level_id
	 * @return the correct animation
	 */

	private Sprite getAnimation(int level_id) {
		switch (level_id) {

		case 1:
			return plus100animation.animation(counter100);
		case 2:
			return plus100animation.animation(counter100);
		case 3:
			return invertedplus100animation.animation(counter100);
		case 4:
			return plus100animation.animation(counter100);
		case 5:
			return plus100animation.animation(counter100);
		case 6:
			return invertedplus100animation.animation(counter100);
		case 7:
			return invertedplus100animation.animation(counter100);
		default:
			return plus100animation.animation(counter100);
		}
	}

	/**
	 * Updates the screen and proceeds to render all assets accordingly to need
	 * This method renders ALL assets for the gamer
	 *
	 * @see render()
	 */

	private void render_method() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		batch.begin();
		if (menu_state == 0) {
			// splash
			splash.draw(batch);
		} else if (menu_state == 1) {
			// menu screen
			menu.draw(batch);
			
			temp_shop_mag = new Sprite(getShopSprite(current_mag));
			temp_shop_mag.setPosition(230, 220);
			temp_shop_mag.setSize(HEIGHT / 4, WIDTH / 4);
			temp_shop_mag.draw(batch);
			if (score_keeper.getSound() == 1)
				sound_on.draw(batch);
			else
				sound_off.draw(batch);
		} else if (menu_state == 2) {
			// game

			if (is_text_color_inverted(level_id))
				font.setColor(255, 255, 255, 1);
			else
				font.setColor(0, 0, 0, 1);
			the_background.draw(batch);

			if (game_start_cooldown > 0) {
				font.draw(batch, "Ready", 300, 350);
			}
			if (game_start_cooldown < 10 && game_start_cooldown > 0) {
				font.draw(batch, "Go!", 315, 300);
			}

			if (Player_Sprite.isFlipY()) {
				Player_Sprite.setPosition(player.getX(), player.getY()
						- Player_Sprite.getHeight() - WIDTH / 19);
			}

			ground = getFloor(level_id);
			ground.draw(batch);
			for (int i = 0; i < MasterBox.getBox_List().size(); i++) {
				if (!(MasterBox.getBox_List().get(i).getX() < 650 && MasterBox
						.getBox_List().get(i).getX() > -50)) {
					continue;
				}
				if (MasterBox.getBox_List().get(i).getY() == 160) {
					obstacle.setFlip(false, true);
					obstacle.setPosition(MasterBox.getBox_List().get(i).getX(),
							MasterBox.getBox_List().get(i).getY()
									- getYOffset(level_id));
				} else {
					obstacle.setFlip(false, false);
					obstacle.setPosition(MasterBox.getBox_List().get(i).getX(),
							MasterBox.getBox_List().get(i).getY());
				}
				obstacle.draw(batch);
			}

			for (int i = 0; i < MasterCoin.getList().size(); i++) {
				Coin_or_PowerUP c = MasterCoin.getList().get(i);
				if (c.getX() > 650 || c.getX() < -50)
					continue;
				if (c.getName().equals("coin")) {
					coin.setPosition(c.getX(), c.getY());
					coin.draw(batch);
				} else if (c.getName().equals("cherry")) {
					cherry.setPosition(c.getX(), c.getY());
					cherry.draw(batch);
				} else if (c.getName().equals("strawberry")) {
					strawberry.setPosition(c.getX(), c.getY());
					strawberry.draw(batch);
				} else if (c.getName().equals("pineapple")) {
					pineapple.setPosition(c.getX(), c.getY());
					pineapple.draw(batch);
				} else if (c.getName().equals("doubleCoins")) {
					doubleCoins.setPosition(c.getX(), c.getY());
					doubleCoins.draw(batch);
				} else if (c.getName().equals("tripleCoins")) {
					tripleCoins.setPosition(c.getX(), c.getY());
					tripleCoins.draw(batch);
				}

			}

			for (int i = 0; i < MasterPowerUP.getList().size(); i++) {
				Magnet_PowerUP MPU;
				MPU = (Magnet_PowerUP) MasterPowerUP.getList().get(i);
				if (MPU.getX() < -50 || MPU.getX() > 700)
					continue;
				magnet_powerup.setPosition(MPU.getX(), MPU.getY());
				magnet_powerup.draw(batch);
			}

			if (PowerUpEffects.isActive_magnet) {
				magnet_powerup.setPosition(300, 425);
				magnet_powerup.draw(batch);
				if ((int) Math.ceil(PowerUpEffects.FramesLeftOfMagnet / 60.0) < 4) {
					font.setColor(1, 0, 0, 1);
				}
				magnetBuffer.replace(2, 5, Integer.toString((int) Math.ceil(PowerUpEffects.FramesLeftOfMagnet / 60.0)));
				font.draw(batch, magnetBuffer, 350, 455);
				if (is_text_color_inverted(level_id))
					font.setColor(1, 1, 1, 1);
				else
					font.setColor(0, 0, 0, 1);
			} else {
				magnet_powerup.setPosition(300, 425);
				magnet_powerup.draw(batch);
				font.setColor(1, 0, 0, 1);
				font.draw(batch, "X", 305, 450);
				font.setColor(0, 0, 0, 1);
			}

			if (polarizing_counter > 0) {
				temp_object = animationPolarizing.animation(polarizing_counter);
				temp_object.setPosition(260, 300);
				temp_object.draw(batch);
				polarizing_counter -= 60 * Gdx.graphics.getDeltaTime();
			}

			if (counter100 > 0 || invertedCounter100 > 0) {
				temp_object = getAnimation(level_id);
				temp_object.setPosition(50, 280);
				temp_object.draw(batch);
				counter100 -= 60 * Gdx.graphics.getDeltaTime();
			}
			if (tripleCoinCounter > 0) {
				tripleCoinCounter -= 1 * Gdx.graphics.getDeltaTime();
				tripleCoins.setPosition(500, 425);
				tripleCoins.draw(batch);
			} else if (doubleCoinCounter > 0) {
				doubleCoinCounter -= 1 * Gdx.graphics.getDeltaTime();
				doubleCoins.setPosition(500, 425);
				doubleCoins.draw(batch);
			} else {
				coin.setPosition(500, 425);
				coin.draw(batch);
			}

			scoreBuffer.replace(9, 20, Integer.toString((int) score));
			font.draw(batch, scoreBuffer, 50, 453);

			coinBuffer.replace(2, 10, Integer.toString(coin_count));
			font.draw(batch, coinBuffer, 550, 455);
			Player_Sprite.draw(batch);

		} else if (menu_state == 3) {
			// game over
			gameover.draw(batch);
			font.getData().setScale(.5f);
			font.draw(batch, "Tip:  " + getTip(tipNum), 50, 405);
			font.getData().setScale(1f);
			font.draw(batch, "" + (int) (score), 300, 310);
			font.draw(batch, "" + coin_count, 150 - String.valueOf(coin_count)
					.length() * 15, 363);
			font.draw(batch, "" + (int) score_keeper.getCoinCount(), 400, 363);
			font.draw(batch, "" + (int) score_keeper.getHighscore(level_id),
					300, 245);
		} else if (menu_state == 4) {
			// coin shop
			shop_background.draw(batch);
			temp_shop_mag = new Sprite(getShopSprite(shop_id));
			temp_shop_mag.setPosition(230, 220);
			temp_shop_mag.setSize(HEIGHT / 4, WIDTH / 4);
			temp_shop_mag.draw(batch);

			coin.setPosition(400, 445);
			coin.draw(batch);
			font.draw(batch, "x " + score_keeper.getCoinCount(), 450, 475);

			if (!score_keeper.isMagnetUnlocked(shop_id)) {
				locked.setPosition(230, 220);
				locked.draw(batch);
				buy_button.setPosition(210, 125);
				buy_button.draw(batch);
				coin.setPosition(230, 187);
				coin.draw(batch);
				font.draw(batch, "x " + getValueOfMagnet(shop_id), 305, 215);
			} else {
				use_button.setPosition(210, 125);
				use_button.draw(batch);
				if (shop_id == current_mag) {
					score_keeper.updateLastMagnet(current_mag);
					checkmark.setPosition(150, 200);
					checkmark.draw(batch);
				}
			}
			if (areYouSureAboutThat) {
				confirmation.setPosition(0, 0);
				confirmation.draw(batch);
				coin.setPosition(180, 213);
				coin.draw(batch);
				font.draw(batch, "" + getValueOfMagnet(shop_id), 250, 240);
			}
		} else if (menu_state == 5) {
			// level select
			if (levelPurchaseConfirm) {
				confirmation.setPosition(0, 0);
				confirmation.draw(batch);
				coin.setPosition(180, 213);
				coin.draw(batch);
				font.draw(batch, "" + getScoreRequired(level_id), 250, 240);
			} else {

				levelmenu.draw(batch);
				thumbnail = getLevel(level_id);
				thumbnail.setPosition(100, 294);
				thumbnail.draw(batch);
				if (!score_keeper.isLevelUnlocked(level_id)) {
					locked.setPosition(225, 290);
					locked.draw(batch);
					buy_button.setPosition(200, 75);
					buy_button.draw(batch);
					coin.setPosition(350, 40);
					coin.draw(batch);
					font.draw(batch, "Unlock for :     "
							+ getScoreRequired(level_id), 150, 70);
				} else {
					play_button.setPosition(200, 75);
					play_button.draw(batch);
					font.draw(
							batch,
							"Best score :  "
									+ score_keeper.getHighscore(level_id), 175,
							70);
				}
				font.draw(batch, getLevelName(level_id), 200, 220);
			}
		} else if (menu_state == 6) {
			if (isPage1)
				menu_htp.draw(batch);
			else
				menu_htp2.draw(batch);
		}
		batch.end();
	}

	/**
	 * Cleans up assets after the game is over Called automaticly at endgame
	 *
	 */

	@Override
	public void dispose() {
		reset_all();
		Player_Sprite.getTexture().dispose();
		p_sprite.getTexture().dispose();
		p_sprite2.getTexture().dispose();
		p_sprite3.getTexture().dispose();

		backgroundThumbnail.getTexture().dispose();
		background_field_thumbnail.getTexture().dispose();
		background_desert_thumbnail.getTexture().dispose();
		background_mountains_thumbnail.getTexture().dispose();
		background_pyramid_thumbnail.getTexture().dispose();
		background_cave_thumbnail.getTexture().dispose();

		background.getTexture().dispose();
		background_field.getTexture().dispose();
		background_desert.getTexture().dispose();
		background_mountains.getTexture().dispose();
		background_pyramid.getTexture().dispose();
		background_cave.getTexture().dispose();

		bush.getTexture().dispose();
		levelmenu.getTexture().dispose();
		checkmark.getTexture().dispose();
		magnet_powerup.getTexture().dispose();
		b_sprite.getTexture().dispose();
		shop_background.getTexture().dispose();
		splash.getTexture().dispose();
		cherry.getTexture().dispose();
		strawberry.getTexture().dispose();
		pineapple.getTexture().dispose();
		menu.getTexture().dispose();
		gameover.getTexture().dispose();
		magnet.getTexture().dispose();
		coin.getTexture().dispose();
		buy_button.getTexture().dispose();
		use_button.getTexture().dispose();
		locked.getTexture().dispose();
		confirmation.getTexture().dispose();
		cactus.getTexture().dispose();

		musicBackgroundMenu.dispose();
		musicBackground2.dispose();
		musicBackground3.dispose();
		musicBackground4.dispose();
		musicShopBackground.dispose();

		gameoverAudio.dispose();
		clickAudio.dispose();
		coinCollectedAudio.dispose();
		score_keeper.flush();
	}

	/**
	 * Resizes the screen according to the phone screen size
	 */

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2,
				camera.viewportHeight / 2, 0);
	}

	public static void playCoinAudio() {
		coinCollectedAudio.play(volume);
	}

	public static void playCoinPowerUp(){
		coinPowerUpAudio.play(volume);
	}

	public static void playFruitPowerUp(){
		fruitPowerUp.play(volume);
	}

	public static void playCollectAudio() {
		magnetCollectedAudio.play(volume);
	}
}
