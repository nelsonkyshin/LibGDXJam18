package com.happygo.nksy.jam18.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.happygo.nksy.jam18.GameController;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.assets.Assets;
import com.happygo.nksy.jam18.screen.widget.ScoreWidget;

public class JamHUD {

    private Stage stage;
    private ScoreWidget score;
    private Table table;
    private Label tryAgain;

    public JamHUD() {
        this.stage = new Stage(new ScalingViewport(Scaling.fill, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4));
        stage.getCamera().update();

        tryAgain = new Label("[BLACK]Game Over!\nPress to try Again", Assets.skin());
        tryAgain.setAlignment(Align.center);
//        tryAgain.setFontScale(3);

        table = new Table();
        table.setDebug(Main.DEBUG);
        table.setFillParent(true);

        score = new ScoreWidget("", Assets.skin());

        table.padTop(50).padBottom(50);
        table.add(score).center().top().expand();
        table.row();
        table.add(tryAgain).bottom();

        stage.addActor(table);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (GameController.isGameOver() && ScreenController.get().getCurrent() instanceof GameScreen) {
                    ((GameScreen) ScreenController.get().getCurrent()).reset();
                }
            }
        });
    }

    public void update() {
        stage.act();
        tryAgain.setVisible(GameController.isGameOver() ? true : false);
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
