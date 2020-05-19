package com.happygo.nksy.jam18;

import com.badlogic.gdx.audio.Music;
import com.happygo.nksy.jam18.assets.Assets;

public class AudioManager {

    private static float sfx_volume = 0.1f;
    private static float music_volume = 0.4f;
    private static Music current;

    public static void playSfx(String key) {
        Assets.sfx(key).play(sfx_volume);
    }

    public static void playMusic(String key) {
        if (current == null) {
            current = Assets.music(key);
            current.setLooping(true);
            current.setVolume(music_volume);
            current.play();
        }
    }

}
