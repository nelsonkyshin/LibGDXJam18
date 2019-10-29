package com.happygo.nksy.jam18.screen.widget;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.happygo.nksy.jam18.assets.Assets;
import com.happygo.nksy.jam18.monetization.ColorChangeController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ChangeColorButton extends TextButton {

    private static final DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

    public ChangeColorButton() {
        super("", Assets.skin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                event.handle();
                if (ColorChangeController.canChange()) {
                    ColorChangeController.rerollColor();
                }
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (ColorChangeController.canChange()) {
            setText("Change Color NOW!");
        }
        else {
            setText(dateFormat.format(ColorChangeController.getRemaining()));
        }
    }
}
