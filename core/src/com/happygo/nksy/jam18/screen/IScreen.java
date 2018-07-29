package com.happygo.nksy.jam18.screen;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IScreen {

    void onExit();

    void onEnter();

    void render(SpriteBatch batch);

    void update();

    void dispose();

    Color getClearColor();

    InputMultiplexer getInputMultiplexer();
}
