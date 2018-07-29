package com.happygo.nksy.jam18.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.happygo.nksy.jam18.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Icebergs";
		config.width = 540;
		config.height = 860;
        config.resizable = false;
		config.vSyncEnabled = false;
		new LwjglApplication(new Main(), config);
	}
}
