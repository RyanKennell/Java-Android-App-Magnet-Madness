package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import control.Main;


/**
 * @author kenne
 *
 * Runs upon desktop launch
 */
public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL",
				"true");
		cfg.width = 720;
		cfg.height = 1280;
		cfg.vSyncEnabled = false;
		new LwjglApplication(new Main(), cfg);
	}
}
