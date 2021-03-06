package com.happygo.nksy.jam18.screen.widget;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.Main;

public class ScoreWidget extends Group {

    private static final float SCALE_DURATION = 0.05f;
    private int previousScore;
    private Label label;

    public ScoreWidget(CharSequence text, Skin skin) {
        super();
        label = new Label(text, skin);
        label.setFontScale(2);
        label.setAlignment(Align.bottom);
        label.setOrigin(Align.center);
        addActor(label);
        setOrigin(Align.center);
        previousScore = 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (previousScore != GameController.platformScore) {
            clearActions();
            label.setText(String.valueOf(GameController.platformScore));
            addAction(Actions.sequence(Actions.scaleBy(1.05f, 1.05f), Actions.scaleTo(1, 1, SCALE_DURATION)));
            previousScore = GameController.platformScore;
        }
    }

    public void reset() {
        previousScore = 0;
        label.setColor(Main.darkerColor);
        label.setText(String.valueOf(0));
    }
}
