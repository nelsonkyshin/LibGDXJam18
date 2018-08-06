package com.happygo.nksy.jam18.entities.factories;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.entities.Iceberg;
import com.happygo.nksy.jam18.entities.PlayerController;
import com.happygo.nksy.jam18.screen.camera.JamCamera;

public class IcebergFactory {

    private Pool<Iceberg> pool;
    private static IcebergFactory instance;

    private IcebergFactory() {
        this.pool = new Pool<Iceberg>() {
            @Override
            protected Iceberg newObject() {
                return new Iceberg();
            }
        };
    }

    public static IcebergFactory get() {
        if (instance == null) {
            instance = new IcebergFactory();
        }
        return instance;
    }

    public Iceberg spawn(Array<Iceberg> icebergs) {
        Iceberg iceberg = pool.obtain();
        iceberg.reset();
        float maxY = 0;
        for (Iceberg berg: icebergs) {
            maxY = Math.max(maxY, berg.getPosition().y + berg.getHeight()/2);
        }
        iceberg.getPosition().set(MathUtils.random(0.2f, 0.8f) * JamCamera.get().viewportWidth, maxY + iceberg.getHeight()/2 + MathUtils.random(-400, 200) / GameController.getDifficultyMultiplier());
        return iceberg;
    }

    public Pool<Iceberg> getPool() {
        return pool;
    }

    public Iceberg getInitialIceberg() {
        Iceberg iceberg = getPool().obtain();
        iceberg.reset();
        iceberg.setOriginalWidth(1000);
        iceberg.getPosition().set(JamCamera.get().viewportWidth/2, PlayerController.PLAYER_INITIAL_Y);
        iceberg.setConsumed();
        iceberg.setConsumedTime(-100);
        return iceberg;
    }
}
