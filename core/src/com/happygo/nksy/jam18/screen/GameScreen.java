package com.happygo.nksy.jam18.screen;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.entities.EntityController;
import com.happygo.nksy.jam18.input.DesktopController;
import com.happygo.nksy.jam18.input.GameInputProcessor;
import com.happygo.nksy.jam18.input.MobileController;
import com.happygo.nksy.jam18.screen.camera.JamCamera;
import com.happygo.nksy.jam18.screen.widget.JamHUD;

public class GameScreen implements IScreen {

    private final EntityController controller;
    private final JamHUD hud;
    private final InputMultiplexer inputMultiplexer;

    public GameScreen() {
        controller = new EntityController();
        hud = new JamHUD();
        GameInputProcessor gameInputProcessor = new GameInputProcessor(EntityController.playerController);
        DesktopController desktopController = new DesktopController(gameInputProcessor);
        MobileController mobileController = new MobileController(gameInputProcessor);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(desktopController);
        inputMultiplexer.addProcessor(new GestureDetector(mobileController));
        inputMultiplexer.addProcessor(hud.getStage());
        reset();
    }

    @Override
    public void update() {
        GameController.update();
        controller.update();
        JamCamera.get().update();
        hud.update();
    }

    @Override
    public void onExit() {

    }

    @Override
    public void onEnter() {
        reset();
    }

    @Override
    public void render(SpriteBatch batch) {
        controller.render(batch);
        hud.render(batch);
    }

    @Override
    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    @Override
    public void dispose() {
        hud.dispose();
    }

    @Override
    public Color getClearColor() {
        return Main.waterColor;
    }

    public void reset() {
        Main.rerollColor();
        controller.reset();
        GameController.reset();
        hud.reset();
    }
}
