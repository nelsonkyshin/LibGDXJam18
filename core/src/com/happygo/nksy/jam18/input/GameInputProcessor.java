package com.happygo.nksy.jam18.input;

import com.badlogic.gdx.math.Vector3;
import com.happygo.nksy.jam18.entities.PlayerController;
import com.happygo.nksy.jam18.screen.GameScreen;
import com.happygo.nksy.jam18.screen.JamCamera;

public class GameInputProcessor implements IInputProcessor {

    private PlayerController playerController;
    private Vector3 temp;

    public GameInputProcessor(PlayerController playerController) {
        this.playerController = playerController;
        temp = new Vector3();
    }

    @Override
    public boolean stimulus(float screenX, float screenY) {
        JamCamera.get().unproject(temp.set(screenX, screenY, 0));
        playerController.jumpTo(temp.x, temp.y);
        return true;
    }
}
