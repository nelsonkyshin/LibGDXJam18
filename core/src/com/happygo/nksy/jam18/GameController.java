package com.happygo.nksy.jam18;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class GameController {

    private static float timeAlive;
    private static boolean gameOver;
    private static boolean justLanded;
    public static int platforms;
    public static Array<String> bonusQueue = new Array<String>();

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void reset() {
        timeAlive = 0;
        gameOver = false;
        justLanded = false;
        platforms = 0;
        bonusQueue.clear();
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

    public static void setJustLanded(boolean landed) {
        justLanded = landed;
    }

    public static boolean isJustLanded() {
        return justLanded;
    }
}
