package com.happygo.nksy.jam18.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.entities.player.state.Dead;
import com.happygo.nksy.jam18.entities.player.state.Jumping;
import com.happygo.nksy.jam18.entities.player.state.PlayerState;
import com.happygo.nksy.jam18.entities.player.state.Resting;
import com.happygo.nksy.jam18.entities.player.state.Standing;
import com.happygo.nksy.jam18.screen.camera.JamCamera;

public class PlayerController {

    private static final Color RANGE_COLOR = new Color(0, 0.5f, 0.1f, 0.1f);
    public static float PLAYER_INITIAL_Y = 80;
    public static float MAX_JUMP_LENGTH = 10000;//Gdx.graphics.getHeight()*2/5;
    private static PlayerState jumping = new Jumping();
    private static PlayerState standing = new Standing();
    private static PlayerState resting = new Resting();
    private static PlayerState dead = new Dead();

    private Vector2 temp;
    private Player player;
    private PlayerState playerState;

    public PlayerController(Player player) {
        this.player = player;
        this.temp = new Vector2();
        reset();
    }

    public void jumpTo(float x, float y) {
        if (standing.equals(playerState) && y > player.getMid().y) {
            // clip destination
            temp.set(x, y).sub(player.position);
            float maxLength = Math.min(MAX_JUMP_LENGTH, temp.len());
            temp.setLength(maxLength).add(player.position);

            player.setDestination(temp.x, temp.y);
            changeState(jumping);
        }
    }

    public void update(boolean panning) {
        playerState.actUpon(player);
        if (GameController.isGameOver()) {
            changeState(dead);
            return;
        }
        if (playerState.isComplete() && !panning) {
            if (jumping.equals(playerState)) {
                changeState(resting);
            }
            else if (resting.equals(playerState)) {
                changeState(standing);
            }
        }
    }

    public void render(SpriteBatch batch) {
        playerState.render(player);
        if (GameController.isGameOver()) {
            return;
        }
//        RANGE_COLOR.a = (float)(Math.sin(Main.gameTime * 2)/2f + 0.5f) * 0.05f;
//        Gdx.gl.glEnable(GL20.GL_BLEND);
//        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        Main.shapeRenderer.setColor(RANGE_COLOR);
//        Main.shapeRenderer.circle(player.getMid().x, player.getMid().y, MAX_JUMP_LENGTH);
//        Main.shapeRenderer.end();
    }

    public void changeState(PlayerState nextState) {
        if (nextState != null && nextState.equals(playerState)) {
            return;
        }
        if (playerState != null) {
            playerState.exitState(player);
        }
        playerState = nextState;
        playerState.enterState(player);
    }

    public void reset() {
        player.getPosition().set(JamCamera.get().viewportWidth/2 - player.getWidth()/2, PLAYER_INITIAL_Y);
        changeState(standing);
    }

    public boolean readyToPan() {
        return !GameController.isGameOver() && !isJumping() && player.getPosition().y != PLAYER_INITIAL_Y;
    }

    public float getAmountOff() {
        return player.getPosition().y - PLAYER_INITIAL_Y;
    }

    public boolean isJumping() {
        return jumping.equals(playerState);
    }
}
