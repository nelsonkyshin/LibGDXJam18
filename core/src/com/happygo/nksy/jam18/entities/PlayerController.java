package com.happygo.nksy.jam18.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.entities.player.state.Jumping;
import com.happygo.nksy.jam18.entities.player.state.PlayerState;
import com.happygo.nksy.jam18.entities.player.state.Resting;
import com.happygo.nksy.jam18.entities.player.state.Standing;
import com.happygo.nksy.jam18.screen.JamCamera;

public class PlayerController {

    public static float PLAYER_INITIAL_Y = 10;
    private static PlayerState jumping = new Jumping();
    private static PlayerState standing = new Standing();
    private static PlayerState resting = new Resting();

    private Player player;
    private PlayerState playerState;

    public PlayerController(Player player) {
        this.player = player;
        reset();
    }

    public void jumpTo(Vector2 position) {
        jumpTo(position.x, position.y);
    }

    public void jumpTo(float x, float y) {
        if (standing.equals(playerState)) {
            player.setDestination(x, y);
            changeState(jumping);
        }
    }

    public void update() {
        if (GameController.isGameOver()) {
            return;
        }
        playerState.actUpon(player);
        if (playerState.isComplete()) {
            if (jumping.equals(playerState)) {
                changeState(resting);
            }
            else if (resting.equals(playerState)) {
                changeState(standing);
            }
        }
    }

    public void render(SpriteBatch batch) {
        player.render(batch);
    }

    public void changeState(PlayerState nextState) {
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
        return !isJumping() && player.getPosition().y != PLAYER_INITIAL_Y;
    }

    public float getAmountOff() {
        return player.getPosition().y - PLAYER_INITIAL_Y;
    }

    public boolean isJumping() {
        return jumping.equals(playerState);
    }
}
