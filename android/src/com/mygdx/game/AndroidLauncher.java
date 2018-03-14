package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import control.Main;


/**
 * @author kenne
 *
 *Runs upon android app launch
 */
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useCompass = false;
		config.useAccelerometer = false;
		config.useGyroscope = false;
		config.useImmersiveMode = false;
		config.getTouchEventsForLiveWallpaper = false;
		config.maxSimultaneousSounds = 2;
		initialize(new Main(), config);
	}
}
