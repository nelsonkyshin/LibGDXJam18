package com.happygo.nksy.jam18.assets;

import com.badlogic.gdx.Preferences;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.monetization.ColorChangeController;

public class JamPreferences {

    public static final String COLOR_LEAD = "color.lead";
    public static final String COLOR_WATER = "color.water";
    public static final String COLOR_LIGHTER = "color.lighter";
    public static final String COLOR_DARKER = "color.darker";
    public static final String COLOR_NEXT_CHANGE_TIME = "color.next.change.time";
    public static final String COLOR_LAST_CHANGE_TIME = "color.last.change.time";

    public static void save() {
        Preferences prefs = Assets.prefs();
        Main.savePrefs(prefs);
        ColorChangeController.savePrefs(prefs);
        prefs.flush();
    }

    public static void load() {
        Preferences prefs = Assets.prefs();
        if (!prefs.contains(COLOR_LEAD) || !prefs.contains(COLOR_WATER) || !prefs.contains(COLOR_LIGHTER) || !prefs.contains(COLOR_DARKER)) {
            ColorChangeController.rerollColor();
            return;
        }
        Main.loadPrefs(prefs);
        ColorChangeController.loadPrefs(prefs);
    }

}
