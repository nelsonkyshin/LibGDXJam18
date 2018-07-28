package com.happygo.nksy.jam18.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.screen.JamCamera;

/**
 * Created by Nelson on 28-Jul-18.
 */

public class PanningController {

    private static final float PAN_DURATION = 1.5f;
    private EntityController entityController;
    private float durationRemaining;
    private boolean panning;

    public PanningController(EntityController entityController) {
        this.entityController = entityController;
        panning = false;
    }

    public boolean pauseWhilePanning() {
        if (entityController.playerController.readyToPan()) {
            durationRemaining -= Main.dT;

            if (!panning) {
                panning = true;
                durationRemaining = PAN_DURATION;
            }
            float amount = entityController.playerController.getAmountOff();
            JamCamera.get().setY(amount * Interpolation.circle.apply(1-(durationRemaining/PAN_DURATION)));

            if (durationRemaining <= 0) {
                // adjust everything
                for (Iceberg iceberg : entityController.icebergs) {
                    iceberg.adjustDownward(amount);
                }
                entityController.player.adjustDownward(amount);
                JamCamera.get().setY(0);
                panning = false;
            }
            return true;
        }
        return false;
    }

    public void reset() {
        panning = false;
        JamCamera.get().setY(0);
    }
}
