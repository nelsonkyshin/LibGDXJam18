package com.happygo.nksy.jam18.entities.player.state;

import com.happygo.nksy.jam18.entities.Player;

public class Standing extends PlayerState {

    @Override
    public void exitState(Player entity) {

    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
