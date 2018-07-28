package com.happygo.nksy.jam18.entities.player.state;

import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.entities.Player;

public class Resting implements PlayerState {

    private static float COOLDOWN = 0.5f;
    private float startTime;

    @Override
    public void enterState(Player entity) {
        startTime = Main.gameTime;
    }

    @Override
    public void actUpon(Player entity) {

    }

    @Override
    public void exitState(Player entity) {

    }

    @Override
    public boolean isComplete() {
        return Main.gameTime - startTime > COOLDOWN;
    }
}
