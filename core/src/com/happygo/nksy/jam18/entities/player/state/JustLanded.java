package com.happygo.nksy.jam18.entities.player.state;

import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.entities.Player;

public class JustLanded extends PlayerState {

    @Override
    public void enterState(Player entity) {
        super.enterState(entity);
        GameController.setJustLanded(true);
    }

    @Override
    public void exitState(Player entity) {

    }

    @Override
    public boolean isComplete() {
        return true;
    }
}
