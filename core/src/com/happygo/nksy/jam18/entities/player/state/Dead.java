package com.happygo.nksy.jam18.entities.player.state;

import com.badlogic.gdx.graphics.Color;
import com.happygo.nksy.jam18.AudioManager;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.assets.Assets;
import com.happygo.nksy.jam18.entities.Player;

public class Dead extends PlayerState {

    private static final float DURATION = 1f;
    private float startTime;

    public Dead() {
        super();
    }

    @Override
    public void enterState(Player entity) {
        super.enterState(entity);
        startTime = Main.gameTime;
        color.set(Main.leadColor);
        AudioManager.playSfx(Assets.SFX_SPLASH);
    }

    @Override
    public void actUpon(Player entity) {
        super.actUpon(entity);
        color.lerp(Main.darkerColor, (Main.gameTime-startTime)/DURATION);
    }

    @Override
    public void exitState(Player entity) {

    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
