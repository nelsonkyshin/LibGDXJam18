package com.happygo.nksy.jam18.entities.player.state;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.happygo.nksy.jam18.AudioManager;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.assets.Assets;
import com.happygo.nksy.jam18.entities.Player;

public class Jumping extends PlayerState {

    private Color shadow;
    private static float JUMP_DURATION = 0.4f;
    private float startTime;
    private Vector2 initial;
    private Vector2 temp;
    private float jumpLength;

    public Jumping() {
        super();
        shadow = new Color(0, 0, 0, 0.4f);
        initial = new Vector2();
        temp = new Vector2();
    }

    @Override
    public void enterState(Player entity) {
        startTime = Main.gameTime;
        initial.set(entity.getPosition());
        jumpLength = temp.set(entity.getDestination()).sub(initial).len();
        AudioManager.playSfx(Assets.SFX_JUMP);
    }

    @Override
    public void actUpon(Player entity) {
        float i = MathUtils.clamp((Interpolation.circle.apply((Main.gameTime - startTime)/JUMP_DURATION)), 0, 1);
        temp.set(entity.getDestination()).sub(initial).scl(i).add(initial);
        entity.getPosition().set(temp);
        float z = getZSin() * 30 * jumpLength/800;
        drawBounds.set(temp.x - z, temp.y - z, entity.getWidth() + z*2, entity.getHeight() + z*2);
        shadowBounds.set(drawBounds);
        shadowBounds.x -= shadowBounds.getHeight()/4 + z*8;
    }

    @Override
    public void exitState(Player entity) {

    }

    @Override
    public boolean isComplete() {
        return Main.gameTime - startTime > JUMP_DURATION;
    }

    public float getZSin() {
        return MathUtils.sin((Main.gameTime - startTime)/JUMP_DURATION * MathUtils.PI);
    }
}
