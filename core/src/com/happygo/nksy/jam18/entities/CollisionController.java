package com.happygo.nksy.jam18.entities;

import com.happygo.nksy.jam18.AudioManager;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.assets.Assets;

public class CollisionController {

    private EntityController entityController;

    public CollisionController(EntityController entityController) {
        this.entityController = entityController;
    }

    public void update() {
        if (entityController.playerController.isJumping() || GameController.isGameOver()) {
            return;
        }
        boolean onLand = false;
        int numberConsumed = 0;
        for (Iceberg iceberg : entityController.icebergs) {
            if (iceberg.getBounds().contains(entityController.player.getMid())) {
                if (!iceberg.isConsumed()) {
                    iceberg.setConsumed();
                    GameController.platforms++;
                    numberConsumed++;
                }
                onLand = true;
            }
        }
        if (!onLand) {
            // game over
            GameController.setGameOver();
        }
        if (numberConsumed > 0) {
            AudioManager.playSfx(numberConsumed > 1 ? (numberConsumed == 2 ?Assets.SFX_SUPER_SUCCESS : Assets.SFX_ULTRA_SUCCESS) : Assets.SFX_SUCCESS);
        }
    }
}
