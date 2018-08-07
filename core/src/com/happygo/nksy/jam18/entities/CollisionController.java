package com.happygo.nksy.jam18.entities;

import com.badlogic.gdx.Game;
import com.happygo.nksy.jam18.AudioManager;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.assets.Assets;

public class CollisionController {

    private EntityController entityController;

    public CollisionController(EntityController entityController) {
        this.entityController = entityController;
    }

    public void updateJustLanded() {
        int numberConsumed = 0;
        for (Iceberg iceberg : entityController.icebergs) {
            if (iceberg.getBounds().contains(entityController.player.getMid())) {
                if (iceberg.setConsumed()) {
                    GameController.platforms += iceberg.getnConsumed();
                    if (iceberg.getnConsumed() > 1) {
                        GameController.bonusQueue.add("Hopper +" + iceberg.getnConsumed());
                    }
                    numberConsumed++;
                }
            }
        }
        if (numberConsumed > 0) {
            AudioManager.playSfx(numberConsumed > 1 ? (numberConsumed == 2 ?Assets.SFX_SUPER_SUCCESS : Assets.SFX_ULTRA_SUCCESS) : Assets.SFX_SUCCESS);
        }
        if (numberConsumed > 1) {
            GameController.platforms += numberConsumed - 1;
            GameController.bonusQueue.add("Multi-Platform +" + (numberConsumed-1));
        }
    }

    public void update() {
        if (GameController.isJustLanded()) {
            updateJustLanded();
            GameController.setJustLanded(false);
        }
        if (entityController.playerController.isJumping() || GameController.isGameOver()) {
            return;
        }
        boolean onLand = false;
        for (Iceberg iceberg : entityController.icebergs) {
            if (iceberg.getBounds().contains(entityController.player.getMid())) {
                onLand = true;
            }
        }
        if (!onLand) {
            // game over
            GameController.setGameOver();
        }

    }
}
