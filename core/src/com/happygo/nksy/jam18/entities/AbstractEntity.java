package com.happygo.nksy.jam18.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractEntity {

    protected boolean removeable;
    protected Vector2 position;
    private float width;
    private float height;
    private Circle bounds;
    protected Vector2 temp;

    public AbstractEntity(Vector2 pos) {
        position = new Vector2(pos);
        bounds = new Circle();
        temp = new Vector2();
    }

    public abstract void update();

    public abstract void render(SpriteBatch batch);

    public boolean isRemoveable() {
        return removeable;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Circle getBounds() {
        bounds.set(position, getWidth()/2);
        return bounds;
    }

    public void adjustDownward(float amount) {
        position.y -= amount;
    }
}
