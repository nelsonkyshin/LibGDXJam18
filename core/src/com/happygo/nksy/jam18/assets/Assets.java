package com.happygo.nksy.jam18.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    private static final AssetManager manager = new AssetManager();

    public static final String PREFERENCES = "jam18";
    public static final String SKIN = "skin/terra-mother-ui.json";
    public static final String LIBGDX = "images/libgdx.png";
    public static final String HGS = "images/hgs_logo.png";
    public static final String SFX_JUMP = "sfx/jump.ogg";
    public static final String SFX_SPLASH = "sfx/splash.ogg";
    public static final String SFX_SUCCESS = "sfx/success.ogg";
    public static final String SFX_SUPER_SUCCESS = "sfx/super_success.ogg";
    public static final String SFX_ULTRA_SUCCESS = "sfx/ultra_success.ogg";
    public static final String MUSIC_ICE = "music/ice.ogg";

    public static void loadAll() {
        manager.load(SKIN, Skin.class);

        manager.load(LIBGDX, Texture.class);
        manager.load(HGS, Texture.class);

        manager.load(SFX_JUMP, Sound.class);
        manager.load(SFX_SPLASH, Sound.class);
        manager.load(SFX_SUCCESS, Sound.class);
        manager.load(SFX_SUPER_SUCCESS, Sound.class);
        manager.load(SFX_ULTRA_SUCCESS, Sound.class);

        manager.load(MUSIC_ICE, Music.class);
    }

    public static Sound sfx(String key) {
        return manager.get(key, Sound.class);
    }

    public static Skin skin() {
        return manager.get(SKIN, Skin.class);
    }

    public static Texture texture(String string) {
        return manager.get(string, Texture.class);
    }

    public static Preferences prefs() {
        return Gdx.app.getPreferences(PREFERENCES);
    }

    public static boolean isReady() {
        return manager.update();
    }

    public static Music music(String key) {
        return manager.get(key, Music.class);
    }
}
