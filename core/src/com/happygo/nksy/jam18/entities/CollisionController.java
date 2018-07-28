package com.happygo.nksy.jam18.entities;

import com.happygo.nksy.jam18.GameController;

public class CollisionController {

    private EntityController entityController;

    public CollisionController(EntityController entityController) {
        this.entityController = entityController;
    }

    public void update() {
        if (entityController.playerController.isJumping()) {
            return;
        }
        boolean onLand = false;
        for (Iceberg iceberg : entityController.icebergs) {
            if (iceberg.getBounds().contains(entityController.player.getMid())) {
                if (!iceberg.isConsumed()) {
                    iceberg.setConsumed();
                    GameController.platforms++;
                }
                onLand = true;
            }
        }
        if (!onLand) {
            // game over
            GameController.setGameOver();
        }
    }
}
