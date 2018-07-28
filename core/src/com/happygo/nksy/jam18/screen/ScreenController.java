package com.happygo.nksy.jam18.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

public class ScreenController {

    private static ScreenController instance;
    private IScreen current;
    private Map<Class<? extends IScreen>, IScreen> screens;

    private ScreenController() {
        screens = new HashMap<Class<? extends IScreen>, IScreen>();
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
        if (current != null) {
            current.onExit();
        }
        current = get(clazz);
        current.onEnter();
        Gdx.input.setInputProcessor(current.getInputMultiplexer());
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
        return null;
    }

    public void render(SpriteBatch batch) {
        if (current == null) {
            return;
        }
        current.render(batch);
    }

    public void update() {
        if (current == null) {
            return;
        }
        current.update();
    }

    public void dispose() {
        for (IScreen screen : screens.values()) {
            screen.dispose();
        }
    }
}
