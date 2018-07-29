package com.happygo.nksy.jam18;

import com.badlogic.gdx.math.MathUtils;

public class GameController {

    private static float timeAlive;
    private static boolean gameOver;
    public static int platforms;

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void reset() {
        timeAlive = 0;
        gameOver = false;
        platforms = 0;
    }

    public static void update() {
        timeAlive += Main.dT;
    }

    public static float getTimeAlive() {
        return timeAlive;
    }

    public static float getDifficultyMultiplier() {
        return MathUtils.clamp(1+platforms/5f, 1f, 2f);
    }

    public static void setGameOver() {
        gameOver = true;
    }
}
