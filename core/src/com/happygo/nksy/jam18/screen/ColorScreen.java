package com.happygo.nksy.jam18.screen;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.assets.Assets;
import com.happygo.nksy.jam18.screen.widget.ChangeColorButton;

public class ColorScreen implements IScreen {

    private final Stage stage;
    private final InputMultiplexer inputMultiplexer;
    private final ChangeColorButton changeColor;
    private final TextButton shareColors;
    private final TextButton back;
    private final Label colorTitle;

    public ColorScreen() {
        stage = new Stage(new ScalingViewport(Scaling.stretch, Main.REFERENCE_WIDTH *10, Main.REFERENCE_HEIGHT *10));
        inputMultiplexer = new InputMultiplexer(stage);
        changeColor = new ChangeColorButton();
        shareColors = new TextButton("Share Colors", Assets.skin());
        shareColors.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Main.service.share();
            }
        });
        back = new TextButton("Back", Assets.skin());
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenController.get().transitionTo(TitleScreen.class);
            }
        });

        colorTitle = new Label("Colors", Assets.skin());
        colorTitle.setFontScale(2);
        colorTitle.setAlignment(Align.center);

        Table table = new Table();
        table.padBottom(Main.REFERENCE_HEIGHT).padTop(Main.REFERENCE_HEIGHT);
        table.setDebug(Main.DEBUG);
        table.setFillParent(true);

        table.add(colorTitle).center().expand();
        table.row().padTop(Main.REFERENCE_WIDTH /2);
        table.add(changeColor);
        table.row().padTop(Main.REFERENCE_WIDTH /2);
        table.add(shareColors);
        table.row().padTop(Main.REFERENCE_WIDTH /2);
        table.add(back);

        stage.addActor(table);
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
    public void update() {
        stage.act();
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
        return Main.waterColor;
    }
}
