package com.happygo.nksy.jam18.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.happygo.nksy.jam18.Main;

public class TransitionController {

    private static float TRANSITION_DURATION = 0.4f;
    private float transitionElapsed = Float.MAX_VALUE;
    private Class<? extends IScreen> clazz;
    private boolean first;

    public void render(SpriteBatch batch) {
        boolean switchScreen = transitionElapsed == 0 || transitionElapsed < 0 && transitionElapsed + Main.dT > 0;
        transitionElapsed += Main.dT;
        if (!isTransitioning()) {
            return;
        }
        if (switchScreen) {
            ScreenController.get().switchScreens(clazz);
        }
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.setColor(1, 1, 1, 1f - Math.abs(transitionElapsed)/TRANSITION_DURATION);
        Main.shapeRenderer.rect(-100, -100, Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight()*2);
        Main.shapeRenderer.end();
    }

    public boolean isTransitioning() {
        return transitionElapsed <= TRANSITION_DURATION && transitionElapsed >= -TRANSITION_DURATION;
    }

    public boolean transitionTo(Class<? extends IScreen> clazz) {
        if (isTransitioning()) {
            return false;
        }
        transitionElapsed = first ? 0 : -TRANSITION_DURATION;
        first = false;
        this.clazz = clazz;
        return true;
    }

}
