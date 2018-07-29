package com.happygo.nksy.jam18.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.assets.Assets;

public class SplashScreen implements IScreen {

    private final float WAIT_DURATION = 5;
    private final Stage stage;
    private final InputMultiplexer inputMultiplexer;
    private float timeTaken;

    public SplashScreen() {
        stage = new Stage(new ScalingViewport(Scaling.fill, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2));
        inputMultiplexer = new InputMultiplexer(stage);

        Label presents = new Label("[BLACK]proudly presents", Assets.skin());
        Label gameJam = new Label("[BLACK]Game Jam 2018", Assets.skin());
        Image libgdx = new Image(Assets.texture(Assets.LIBGDX));
        libgdx.setScaling(Scaling.fit);
        Image hgs = new Image(Assets.texture(Assets.HGS));
        hgs.setScaling(Scaling.fit);

        Table table = new Table();
        table.padBottom(Main.REFERENCE_UNIT).padTop(Main.REFERENCE_UNIT);
        table.setDebug(Main.DEBUG);
        table.setFillParent(true);
        table.add(hgs).center().bottom().size(Main.REFERENCE_UNIT*5, Main.REFERENCE_UNIT*5).expand();
        table.row().padTop(Main.REFERENCE_UNIT/2);
        table.add(presents);
        table.row().expand();
        table.add(libgdx).bottom().size(Main.REFERENCE_UNIT, Main.REFERENCE_UNIT/4);
        table.row().padTop(Main.REFERENCE_UNIT/4);
        table.add(gameJam);

        stage.addActor(table);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenController.get().transitionTo(TitleScreen.class);
            }
        });
    }

    @Override
    public void update() {
        stage.act();
        timeTaken += Main.dT;
        if (timeTaken > WAIT_DURATION) {
            ScreenController.get().transitionTo(TitleScreen.class);
            return;
        }
    }

    @Override
    public void onExit() {

    }

    @Override
    public void onEnter() {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.end();
        stage.getViewport().apply();
        stage.draw();
        batch.begin();
    }

    @Override
    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public Color getClearColor() {
        return Color.WHITE;
    }

    public void reset() {

    }
}
