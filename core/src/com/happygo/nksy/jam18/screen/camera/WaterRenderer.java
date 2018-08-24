package com.happygo.nksy.jam18.screen.camera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.happygo.nksy.jam18.Main;

public class WaterRenderer {

    private static final float SQUARE_WIDTH = 20;
    private static float SIN;

    float squareWidth;

    public WaterRenderer() {
        squareWidth = 0;
    }

    public static void updateSin() {
        SIN = (MathUtils.sin(Main.gameTime)/4 + 0.7f);
    }

    public void update() {
        squareWidth = SIN * SQUARE_WIDTH;
    }

    public void render(SpriteBatch batch) {
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.setColor(Main.lighterColor);
        Rectangle camBounds = JamCamera.get().getCamBounds();

        for (float y=0; y <= camBounds.y + camBounds.height; y += SQUARE_WIDTH) {
            float scale = MathUtils.sin((y - camBounds.y)/camBounds.height*MathUtils.PI*8 + Main.gameTime*4)/4 + 0.7f;
            for (float x = camBounds.x; x < camBounds.x + camBounds.width; x += SQUARE_WIDTH) {
                Main.shapeRenderer.rect(x, y, squareWidth/2, squareWidth/2, squareWidth, squareWidth, scale, scale, SIN * 360);
            }
        }
        Main.shapeRenderer.end();
    }
}
