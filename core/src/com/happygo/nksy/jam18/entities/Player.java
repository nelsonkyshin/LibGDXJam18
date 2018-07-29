package com.happygo.nksy.jam18.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.Main;

public class Player extends AbstractEntity {

    private Vector2 destination;

    public Player() {
        super(Vector2.Zero);
        destination = new Vector2();
        setWidth(50);
        setHeight(50);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch) {
        // player state renders
    }

    public void setDestination(float x, float y) {
        destination.set(x - getWidth()/2, y - getHeight()/2);
    }

    public Vector2 getDestination() {
        return destination;
    }

    public Vector2 getMid() {
        return temp.set(position).add(getWidth()/2, getHeight()/2);
    }
}
