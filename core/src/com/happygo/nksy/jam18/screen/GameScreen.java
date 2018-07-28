package com.happygo.nksy.jam18.screen;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.entities.EntityController;
import com.happygo.nksy.jam18.input.DesktopController;
import com.happygo.nksy.jam18.input.GameInputProcessor;
import com.happygo.nksy.jam18.input.MobileController;

public class GameScreen implements IScreen {

    private EntityController controller;
    private InputMultiplexer inputMultiplexer;

    public GameScreen() {
        controller = new EntityController();
        GameInputProcessor gameInputProcessor = new GameInputProcessor(EntityController.playerController);
        DesktopController desktopController = new DesktopController(gameInputProcessor);
        MobileController mobileController = new MobileController(gameInputProcessor);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(desktopController);
        inputMultiplexer.addProcessor(new GestureDetector(mobileController));
        reset();
    }

    @Override
    public void render(SpriteBatch batch) {
        controller.render(batch);
    }

    @Override
    public void update() {
        GameController.update();
        controller.update();
        JamCamera.get().update();
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public void reset() {
        controller.reset();
        GameController.reset();
    }
}
