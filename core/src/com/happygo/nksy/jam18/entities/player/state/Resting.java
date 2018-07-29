package com.happygo.nksy.jam18.entities.player.state;

import com.happygo.nksy.jam18.AudioManager;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.assets.Assets;
import com.happygo.nksy.jam18.entities.Player;

public class Resting extends PlayerState {

    private static float COOLDOWN = 0f;
    private float startTime;

    @Override
    public void enterState(Player entity) {
        startTime = Main.gameTime;
        super.enterState(entity);
    }

    @Override
    public void exitState(Player entity) {

    }

    @Override
    public boolean isComplete() {
        return Main.gameTime - startTime > COOLDOWN;
    }
}
