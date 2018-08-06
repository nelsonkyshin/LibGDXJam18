package com.happygo.nksy.jam18.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.screen.camera.JamCamera;

public class Iceberg extends AbstractEntity implements Pool.Poolable {

    private float FLOAT_PERIOD = 1.5f;
    private float PULSE_PERIOD = 0.1f;
    private float originalWidth;
    private float timeToDisappear;
    private float durationRemaining;
    private float consumedTime;
    private boolean consumed;
    private Color shadow;
    private float thickness;

    public Iceberg() {
        super(Vector2.Zero);
        shadow = new Color(0, 0, 0, 0.3f);
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

    public void renderPulse(SpriteBatch batch) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        float interp = Interpolation.exp10Out.apply((Main.gameTime - consumedTime)/ PULSE_PERIOD);
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.setColor(Main.lighterColor);
        Main.shapeRenderer.circle(position.x, position.y, getWidth()/2 * interp);
        Main.shapeRenderer.end();
    }

    public void renderEdge(SpriteBatch batch) {
        Gdx.gl.glLineWidth(8);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        float linear = Interpolation.circleOut.apply(((Main.gameTime + originalWidth) % FLOAT_PERIOD) / FLOAT_PERIOD);
        float sin = MathUtils.sin(linear * MathUtils.PI);
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Main.shapeRenderer.setColor(Color.WHITE);
        Main.shapeRenderer.getColor().a = sin;
        Main.shapeRenderer.circle(position.x, position.y, getWidth()/2 + linear * 60);
        Main.shapeRenderer.end();
    }

    public void renderShadow(SpriteBatch batch) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.setColor(shadow);
        Main.shapeRenderer.circle(position.x - Math.min(thickness, getWidth()/2), position.y, getWidth()/2);
        Main.shapeRenderer.end();
    }

    @Override
    public void render(SpriteBatch batch) {
        boolean pulsing = Main.gameTime - consumedTime <= PULSE_PERIOD;
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.setColor(isConsumed() && !pulsing ? Main.lighterColor : Color.WHITE);
        Main.shapeRenderer.circle(position.x, position.y, getWidth()/2);
        Main.shapeRenderer.end();
        if (pulsing) {
            renderPulse(batch);
        }
    }

    @Override
    public void reset() {
        timeToDisappear = MathUtils.random(5, 15)/ GameController.getDifficultyMultiplier();
        durationRemaining = timeToDisappear;
        setOriginalWidth(MathUtils.random(300f, 500f));
        position.set(MathUtils.random(-0.5f, 0.5f) * JamCamera.get().viewportWidth, 500);
        thickness = MathUtils.random(10, 60);
        removeable = false;
        consumed = false;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed() {
        this.consumed = true;
        setConsumedTime(Main.gameTime);
    }

    public void setConsumedTime(float time) {
        this.consumedTime = time;
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
