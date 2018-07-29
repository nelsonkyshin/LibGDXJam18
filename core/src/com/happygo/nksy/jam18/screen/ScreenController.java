package com.happygo.nksy.jam18.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.happygo.nksy.jam18.AudioManager;
import com.happygo.nksy.jam18.assets.Assets;

import java.util.HashMap;
import java.util.Map;

public class ScreenController {

    private static final InputMultiplexer EMPTY_MULTI = new InputMultiplexer();
    private static ScreenController instance;
    private IScreen current;
    private TransitionController transitionController;
    private Map<Class<? extends IScreen>, IScreen> screens;

    private ScreenController() {
        screens = new HashMap<Class<? extends IScreen>, IScreen>();
        transitionController = new TransitionController();
    }

    public static ScreenController get() {
        if (instance == null) {
            instance = new ScreenController();
        }
        return instance;
    }

    public IScreen getCurrent() {
        return current;
    }

    public void transitionTo(Class<? extends IScreen> clazz) {
        if (transitionController.transitionTo(clazz)) {
            Gdx.input.setInputProcessor(EMPTY_MULTI);
            AudioManager.playSfx(Assets.SFX_ULTRA_SUCCESS);
        }
    }

    private IScreen get(Class<? extends IScreen> clazz) {
        if (!screens.containsKey(clazz)) {
            screens.put(clazz, create(clazz));
        }
        return screens.get(clazz);
    }

    private IScreen create(Class<? extends IScreen> clazz) {
        if (GameScreen.class.equals(clazz)) {
            return new GameScreen();
        }
        else if (SplashScreen.class.equals(clazz)) {
            return new SplashScreen();
        }
        else if (TitleScreen.class.equals(clazz)) {
            return new TitleScreen();
        }
        return null;
    }

    public void render(SpriteBatch batch) {
        if (current != null) {
            current.render(batch);
        }
        if (transitionController.isTransitioning()) {
            transitionController.render(batch);
        }
    }

    public void update() {
        if (current != null) {
            current.update();
        }
    }

    public void dispose() {
        for (IScreen screen : screens.values()) {
            screen.dispose();
        }
    }

    public void switchScreens(Class<? extends IScreen> clazz) {
        if (current != null) {
            current.onExit();
        }
        current = get(clazz);
        current.onEnter();
        Gdx.input.setInputProcessor(current.getInputMultiplexer());
    }

    public Color getClearColor() {
        if (current != null) {
            return current.getClearColor();
        }
        return Color.WHITE;
    }
}
