package com.happygo.nksy.jam18.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.entities.factories.IcebergFactory;
import com.happygo.nksy.jam18.screen.camera.PanningController;

public class EntityController {

    public Array<Iceberg> icebergs;
    public static PlayerController playerController;
    public static Player player;
    public PanningController panningController;
    public CollisionController collisionController;

    public EntityController() {
        icebergs = new Array<Iceberg>();
        player = new Player();
        playerController = new PlayerController(player);
        panningController = new PanningController(this);
        collisionController = new CollisionController(this);
    }

    public void update() {
        for (Iceberg iceberg : icebergs) {
            if (iceberg.isRemoveable()) {
                icebergs.removeValue(iceberg, true);
                IcebergFactory.get().getPool().free(iceberg);
            }
            iceberg.update();
        }
        playerController.update(panningController.pauseWhilePanning());
        collisionController.update();

        while (icebergs.size < 20 && !GameController.isGameOver()) {
            icebergs.add(IcebergFactory.get().spawn(icebergs));
        }
    }

    public void render(SpriteBatch batch) {
        if (GameController.isGameOver()) {
            playerController.render(batch);
        }
        for (Iceberg iceberg : icebergs) {
            iceberg.renderEdge(batch);
        }
        for (Iceberg iceberg : icebergs) {
            iceberg.renderShadow(batch);
        }
        for (Iceberg iceberg : icebergs) {
            iceberg.render(batch);
        }
        if (!GameController.isGameOver()) {
            playerController.render(batch);
        }
    }

    public void reset() {
        panningController.reset();
        // clearing
        for (Iceberg iceberg: icebergs) {
            IcebergFactory.get().getPool().free(iceberg);
        }
        icebergs.clear();

        // initial iceberg
        playerController.reset();
        icebergs.add(IcebergFactory.get().getInitialIceberg());
    }
}
