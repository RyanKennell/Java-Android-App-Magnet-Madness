package control;

import java.io.File;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

public class Score {

	public void firstTime() {
		
		FileHandle path = Gdx.files.local("my_file");
		boolean exists = path.exists();

		if (!exists) {
			try {
				createPreferences();
				File file = path.file();
				file.createNewFile();
				System.out.println("first time ay?");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	/*
	 * returns if the current level is locked or not
	 * 
	 * @return :true if unlocked || false if locked
	 * 
	 * @param : level_id
	 */

	public boolean isLevelUnlocked(int level_id) {
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		if (prefs.getInteger("isLevelLocked" + level_id) == 0) {
			return true;
		}
		return false;
	}
	
	/*
	 * Unlocks the level that is sent in the param
	 * 
	 *  @param : the level being unlocked
	 *  
	 */
	
	public void unlockLevel(int value, int level_id){
		System.out.println(level_id);
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		prefs.putInteger("isLevelLocked" + level_id, 0);
		prefs.flush();
		updateTotalCoins(value, false);
	}
	
	/*
	 * Creates empty preferences when the app is first booted
	 */
	
	private void createPreferences(){
		//TODO test for first launch
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		prefs.putInteger("coin", 25000);
		prefs.flush();
		prefs.putInteger("scoreLevel1", 0);
		prefs.flush();
		prefs.putInteger("scoreLevel2", 0);
		prefs.flush();
		prefs.putInteger("scoreLevel3", 0);
		prefs.flush();
		prefs.putInteger("scoreLevel4", 0);
		prefs.flush();
		prefs.putInteger("scoreLevel5", 0);
		prefs.flush();
		prefs.putInteger("scoreLevel6", 0);
		prefs.flush();
		prefs.putInteger("scoreLevel7", 0);
		prefs.flush();
		prefs.putInteger("isMagUNLocked1", 0);
		prefs.flush();
		prefs.putInteger("isMagUNLocked2", 1);
		prefs.flush();
		prefs.putInteger("isMagUNLocked3", 1);
		prefs.flush();
		prefs.putInteger("isMagUNLocked4", 1);
		prefs.flush();
		prefs.putInteger("isMagUNLocked5", 1);
		prefs.flush();
		prefs.putInteger("isLevelLocked1", 0);
		prefs.flush();
		prefs.putInteger("isLevelLocked2", 1);
		prefs.flush();
		prefs.putInteger("isLevelLocked3", 1);
		prefs.flush();
		prefs.putInteger("isLevelLocked4", 1);
		prefs.flush();
		prefs.putInteger("isLevelLocked5", 1);
		prefs.flush();
		prefs.putInteger("isLevelLocked6", 1);
		prefs.flush();
		prefs.putInteger("isLevelLocked7", 1);
		prefs.flush();
		prefs.putInteger("isSoundOn", 0);
		prefs.flush();
		prefs.putInteger("lastMagnet", 1);
		prefs.flush();
	}

	/**
	 * Returns the highest score
	 * 
	 * @return : the highest score
	 * @see updateBestScore()
	 */

	public int getHighscore(int level_id) {
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		return prefs.getInteger("scoreLevel" + level_id, 0);
	}
	
	/**
	 * Gets the preference for sound
	 * 
	 * @return : sound preference
	 * @see TODO
	 */
	
	public int getSound(){
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		return prefs.getInteger("isSoundOn");
	}
	
	/**
	 * Changes the sound preferencne
	 * 
	 * @see TODO
	 */
	
	public void setSound(int b){
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		prefs.putInteger("isSoundOn", b);
		prefs.flush();
	}

	/**
	 * 
	 * Retreives and returns the total coin count
	 * 
	 * @return : total coin count
	 * @see updateTotalCoins()
	 */

	public int getCoinCount() {
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		return prefs.getInteger("coin", 0);
	}

	/**
	 * 
	 * Gets the current state of the magnet requested for the store
	 * 
	 * @param index
	 *            : index of the magnet being retrieved
	 * 
	 * @return : true if the magnet is unlocked, false otherwise
	 * @see render_method()
	 */

	public boolean isMagnetUnlocked(int index) {
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		prefs.flush();
		int val = prefs.getInteger("isMagUNLocked" + index, 0);
		if (val == 0)
			return true;
		return false;
	}
	
	/**
	 * Updates the last magnet used
	 * 
	 * @param magnet_id
	 *            : The index of the magnet being used
	 */

	public void updateLastMagnet(int magnet_id) {
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		prefs.putInteger("lastMagnet", magnet_id);
		prefs.flush();
	}
	
	public int getLastMagnet(){
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		return prefs.getInteger("lastMagnet");
	}

	/**
	 * Updates the unlock state of a magnet
	 * 
	 * @param index
	 *            : The index of the magnet being changed
	 * @param value
	 *            : a int of the new value
	 * 
	 * @see render()
	 */

	public void updateMagnet(int index, int value) {
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		prefs.putInteger("isMagUNLocked" + index, value);
		prefs.flush();
	}

	/**
	 * Takes in the current score and tests if it is higher then the best, if
	 * so, the best is updated to the current
	 * 
	 * @param score
	 *            : the current score that is being tested to the best
	 * 
	 * @see render()
	 */

	public void updateBestScore(int score, int level_id) {
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		if (score > getHighscore(level_id)) {
			prefs.putInteger("scoreLevel" + level_id, score);
		}
		prefs.flush();
	}

	/**
	 * Updates the total amount of coins
	 * 
	 * @param value
	 *            : number of coins being added or deduced
	 * @param isIncrement
	 *            : a boolean representing if the amount is being added or
	 *            subtracted
	 * 
	 * @see render()
	 */

	public void updateTotalCoins(int value, boolean isIncrement) {
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		if (isIncrement) {
			prefs.putInteger("coin", getCoinCount() + value);
			prefs.flush();
		} else {
			prefs.putInteger("coin", getCoinCount() - value);
			prefs.flush();
		}
	}
	
	public void flush(){
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		prefs.flush();
	}
}
