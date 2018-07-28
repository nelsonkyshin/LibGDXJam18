package com.happygo.nksy.jam18.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.screen.JamCamera;

public class Iceberg extends AbstractEntity implements Pool.Poolable {

    private float originalWidth;
    private float timeToDisappear;
    private float durationRemaining;
    private boolean consumed;

    public Iceberg() {
        super(Vector2.Zero);
    }

    @Override
    public void update() {
        if (durationRemaining <= 0 || position.y + getWidth()/2 < 0) {
            removeable = true;
            return;
        }
        if (!JamCamera.get().isVisible(this)) {
            return;
        }
        durationRemaining -= Main.dT;
        setWidth(originalWidth * Interpolation.linear.apply(durationRemaining/timeToDisappear));
    }

    @Override
    public void render(SpriteBatch batch) {
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.setColor(isConsumed() ? Color.YELLOW : Color.WHITE);
        Main.shapeRenderer.circle(position.x, position.y, getWidth()/2);
        Main.shapeRenderer.end();
    }

    @Override
    public void reset() {
        timeToDisappear = MathUtils.random(15, 40)/ GameController.getDifficultyMultiplier();
        durationRemaining = timeToDisappear;
        setOriginalWidth(MathUtils.random(300, 500));
        position.set(MathUtils.random(-0.5f, 0.5f) * JamCamera.get().viewportWidth, 500);
        removeable = false;
        consumed = false;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed() {
        this.consumed = true;
    }

    public void setOriginalWidth(float width) {
        originalWidth = width;
        setWidth(width);
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        setHeight(width);
    }
}
