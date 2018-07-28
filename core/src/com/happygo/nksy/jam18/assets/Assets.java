package com.happygo.nksy.jam18.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    private static final AssetManager manager = new AssetManager();
    public static final String SKIN = "skin/terra-mother-ui.json";

    public static void loadAll() {
        manager.load(SKIN, Skin.class);
    }

    public static Skin skin() {
        return manager.get(SKIN, Skin.class);
    }

    public static boolean isReady() {
        return manager.update();
    }
}
