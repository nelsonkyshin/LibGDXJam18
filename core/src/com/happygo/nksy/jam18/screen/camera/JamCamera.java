package com.happygo.nksy.jam18.screen.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.happygo.nksy.jam18.entities.Iceberg;

public class JamCamera extends OrthographicCamera {

    private static JamCamera instance;
    private Rectangle camBounds;
    private float yOffset;

    private JamCamera() {
        super(780, 1200);
        camBounds = new Rectangle();
        setY(0);
    }

    public static JamCamera get() {
        if (instance == null) {
            instance = new JamCamera();
        }
        return instance;
    }

    public boolean isVisible(Iceberg iceberg) {
        return Intersector.overlaps(iceberg.getBounds(), getCamBounds());
    }

    public void setY(float y) {
        position.set(viewportWidth/2, y + viewportHeight/2, 0);
        update();
    }

    public Rectangle getCamBounds() {
        return camBounds.set(position.x - viewportWidth/2, position.y - viewportHeight/2, viewportWidth, viewportHeight);
    }

    public void setYOffset(float YOffset) {
        yOffset = YOffset;
    }

    public float getYOffset() {
        return yOffset;
    }
}
