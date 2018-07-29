package com.happygo.nksy.jam18.screen.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    public JamHUD() {
        this.stage = new Stage(new ScalingViewport(Scaling.fill, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/3));
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

        tryAgain = new Label("[BLACK]Game Over!", Assets.skin());
        tryAgain.setAlignment(Align.center);
        tryAgain.setFontScale(1.5f);

        table = new Table();
        table.setDebug(Main.DEBUG);
        table.setFillParent(true);

        score = new ScoreWidget("", Assets.skin());

        table.padTop(50).padBottom(50);
        table.add(score).center().top().expand();
        table.row();
        table.add(tryAgain).center().expand();
        table.row().padTop(20).width(100);
        table.add(retry);
        table.row().padTop(5).width(100);
        table.add(title);

        stage.addActor(table);
    }

    public void update() {
        stage.act();
        tryAgain.setVisible(GameController.isGameOver() ? true : false);
        title.setVisible(GameController.isGameOver() ? true : false);
        retry.setVisible(GameController.isGameOver() ? true : false);
        retry.setTouchable(GameController.isGameOver() ? Touchable.enabled : Touchable.disabled);
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
    }
}
