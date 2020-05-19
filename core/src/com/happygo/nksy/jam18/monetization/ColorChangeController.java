package com.happygo.nksy.jam18.monetization;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.assets.JamPreferences;

public class ColorChangeController {

    public static long TIME_TO_WAIT = 8 * 60 * 60 * 1000; // 8 hours between color changes
    public static long lastChange;
    public static long nextChange;
    public static boolean adWatched;

    public static void setAdWatched(boolean adWatched) {
        ColorChangeController.adWatched = adWatched;
    }

    public static void savePrefs(Preferences prefs) {
        prefs.putLong(JamPreferences.COLOR_NEXT_CHANGE_TIME, ColorChangeController.nextChange);
        prefs.putLong(JamPreferences.COLOR_LAST_CHANGE_TIME, ColorChangeController.lastChange);
    }

    public static void loadPrefs(Preferences prefs) {
        lastChange = prefs.getLong(JamPreferences.COLOR_LAST_CHANGE_TIME, 0);
        nextChange = prefs.getLong(JamPreferences.COLOR_NEXT_CHANGE_TIME, 0);
    }

    public static void rerollColor() {
        Main.leadColor.set(MathUtils.random(0.4f, 0.9f), MathUtils.random(0.4f, 0.9f), MathUtils.random(0.4f, 0.7f), 1);
        Main.waterColor.set(Main.leadColor).mul(MathUtils.random(1.08f, 1.12f));
        Main.waterColor.b += MathUtils.random(0.15f, 0.3f);
        Main.waterColor.a = 1;
        Main.lighterColor.set(Main.leadColor).mul(MathUtils.random(1.3f, 1.5f));
        Main.lighterColor.a = 1;
        Main.darkerColor.set(Main.leadColor).mul(MathUtils.random(0.5f, 0.7f));
        Main.darkerColor.a = 1;
        lastChange = System.currentTimeMillis();
        nextChange = lastChange + TIME_TO_WAIT;
        JamPreferences.save();
    }

    public static boolean canChange() {
        return lastChange == 0 || System.currentTimeMillis() >= lastChange + TIME_TO_WAIT;
    }

    public static long getRemaining() {
        return Math.max(0, (lastChange + TIME_TO_WAIT) - System.currentTimeMillis());
    }
}
