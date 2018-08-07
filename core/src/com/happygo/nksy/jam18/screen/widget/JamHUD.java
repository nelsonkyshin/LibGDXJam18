package com.happygo.nksy.jam18.screen.widget;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.happygo.nksy.jam18.AudioManager;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.assets.Assets;
import com.happygo.nksy.jam18.screen.GameScreen;
import com.happygo.nksy.jam18.screen.ScreenController;
import com.happygo.nksy.jam18.screen.TitleScreen;

public class JamHUD {

    private Stage stage;
    private ScoreWidget score;
    private Table table;
    private Label tryAgain;
    private TextButton retry;
    private TextButton title;
    private Label bonusLabel;
    private StringBuilder builder = new StringBuilder();

    public JamHUD() {
        this.stage = new Stage(new ScalingViewport(Scaling.fill, Main.REFERENCE_WIDTH *10, Main.REFERENCE_HEIGHT*10));
        stage.getCamera().update();
        retry = new TextButton("Retry", Assets.skin());
        retry.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (GameController.isGameOver() && ScreenController.get().getCurrent() instanceof GameScreen) {
                    ((GameScreen) ScreenController.get().getCurrent()).reset();
                    AudioManager.playSfx(Assets.SFX_SUPER_SUCCESS);
                }
            }
        });
        title = new TextButton("Title", Assets.skin());
        title.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (GameController.isGameOver()) {
                    ScreenController.get().transitionTo(TitleScreen.class);
                }
            }
        });
        bonusLabel = new Label("", Assets.skin());
        bonusLabel.setFontScale(0.8f);
        bonusLabel.getColor().a = 0;
        bonusLabel.setAlignment(Align.center);

        tryAgain = new Label("Game Over", Assets.skin());
        tryAgain.setAlignment(Align.center);
        tryAgain.setFontScale(1.5f);

        table = new Table();
        table.setDebug(Main.DEBUG);
        table.setFillParent(true);

        score = new ScoreWidget("", Assets.skin());

        table.pad(Main.REFERENCE_HEIGHT);
        table.add(score).center().height(Main.REFERENCE_HEIGHT);
        table.row().padTop(10);
        table.add(bonusLabel).center().top().expand();
        table.row();
        table.add(tryAgain).center().expand();
        table.row().padTop(20).width(Main.REFERENCE_WIDTH *5);
        table.add(retry);
        table.row().padTop(5).width(Main.REFERENCE_WIDTH *5);
        table.add(title);

//        Container<Label> bonusContainer = new Container<Label>(bonusLabel);
//        bonusContainer.pad(5);
//        bonusContainer.setActor(bonusLabel);
//        bonusContainer.align(Align.bottomRight);

        Stack stack = new Stack();
        stack.setDebug(Main.DEBUG);
        stack.setFillParent(true);
        stack.add(table);

        stage.addActor(stack);
    }

    public void update() {
        stage.act();
        tryAgain.setVisible(GameController.isGameOver() ? true : false);
        title.setVisible(GameController.isGameOver() ? true : false);
        retry.setVisible(GameController.isGameOver() ? true : false);
        retry.setTouchable(GameController.isGameOver() ? Touchable.enabled : Touchable.disabled);
        if (GameController.bonusQueue.size > 0) {
            builder.setLength(0);
            while (GameController.bonusQueue.size > 0) {
                builder.append(GameController.bonusQueue.pop()).append("\n");
            }
            bonusLabel.setText(builder.toString());
            bonusLabel.clearActions();
            bonusLabel.addAction(Actions.sequence(Actions.fadeIn(0.05f), Actions.fadeOut(0.8f)));
        }
    }

    public void render(SpriteBatch batch) {
        batch.end();
        stage.getViewport().apply();
        stage.draw();
        batch.begin();
    }

    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public void reset() {
        score.reset();
        tryAgain.setColor(Main.darkerColor);
        bonusLabel.clearActions();
        bonusLabel.setText("");
        bonusLabel.getColor().a = 0;
        bonusLabel.setColor(Main.darkerColor);
    }
}
