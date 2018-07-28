package com.happygo.nksy.jam18.entities.player.state;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.entities.Player;

public class Jumping implements PlayerState {

    private static float JUMP_DURATION = 1f;
    private float startTime;
    private Vector2 initial;
    private Vector2 temp;

    public Jumping() {
        initial = new Vector2();
        temp = new Vector2();
    }

    @Override
    public void enterState(Player entity) {
        startTime = Main.gameTime;
        initial.set(entity.getPosition());
    }

    @Override
    public void actUpon(Player entity) {
        float i = MathUtils.clamp((Interpolation.circle.apply((Main.gameTime - startTime)/JUMP_DURATION)), 0, 1);
        temp.set(entity.getDestination()).sub(initial).scl(i).add(initial);
        entity.getPosition().set(temp);
    }

    @Override
    public void exitState(Player entity) {

    }

    @Override
    public boolean isComplete() {
        return Main.gameTime - startTime > JUMP_DURATION;
    }
}
