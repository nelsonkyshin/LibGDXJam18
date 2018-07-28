package com.happygo.nksy.jam18.entities.player.state;

import com.happygo.nksy.jam18.entities.Player;

public interface PlayerState {

    void enterState(Player entity);

    void actUpon(Player entity);

    void exitState(Player entity);

    boolean isComplete();
}
