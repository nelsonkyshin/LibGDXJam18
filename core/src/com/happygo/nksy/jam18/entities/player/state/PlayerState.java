package com.happygo.nksy.jam18.entities.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.entities.Player;

public abstract class PlayerState {

    protected Color color;
    protected Color shadow;
    protected Rectangle drawBounds;
    protected Rectangle shadowBounds;

    public PlayerState() {
        color = new Color(Color.ORANGE);
        shadow = new Color(0, 0, 0, 0.2f);
        drawBounds = new Rectangle();
        shadowBounds = new Rectangle();
    }

    public void enterState(Player entity) {
        actUpon(entity);
    }

    public void actUpon(Player entity) {
        drawBounds.set(entity.getPosition().x, entity.getPosition().y, entity.getWidth(), entity.getHeight());
        shadowBounds.set(drawBounds);
        shadowBounds.x -= drawBounds.width/4;
    }

    public abstract void exitState(Player entity);

    public abstract boolean isComplete();

    public void render(Player player) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.setColor(shadow);
        Main.shapeRenderer.rect(shadowBounds.x, shadowBounds.y, shadowBounds.width, shadowBounds.height);
        Main.shapeRenderer.end();

        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.setColor(color);
        Main.shapeRenderer.rect(drawBounds.x, drawBounds.y, drawBounds.getWidth(), drawBounds.getHeight());
        Main.shapeRenderer.end();
    }
}
