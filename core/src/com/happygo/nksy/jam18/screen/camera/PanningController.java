package com.happygo.nksy.jam18.screen.camera;

import com.badlogic.gdx.math.Interpolation;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.entities.EntityController;
import com.happygo.nksy.jam18.entities.Iceberg;
import com.happygo.nksy.jam18.screen.camera.JamCamera;

public class PanningController {

    private static final float PAN_DURATION = 0.4f;
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
            float interpolated = amount * Interpolation.circle.apply(1-(durationRemaining/PAN_DURATION));
            JamCamera.get().setY(interpolated);
            JamCamera.get().setYOffset(interpolated);

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
