package com.happygo.nksy.jam18.screen;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.happygo.nksy.jam18.AudioManager;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.assets.Assets;
import com.happygo.nksy.jam18.monetization.ColorChangeController;

public class TitleScreen implements IScreen {

    private final Stage stage;
    private final InputMultiplexer inputMultiplexer;
    private final TextButton colorButton;
    private final Label gameTitle;
    private final Label pressAny;
    private final Label leaderboard;
    private final Vector2 temp;

    public TitleScreen() {
        stage = new Stage(new ScalingViewport(Scaling.stretch, Main.REFERENCE_WIDTH *10, Main.REFERENCE_HEIGHT *10));
        inputMultiplexer = new InputMultiplexer(stage);
        temp = new Vector2();
        colorButton = new TextButton("Change Colors", Assets.skin());
        colorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ColorChangeController.rerollColor();
            }
        });

        gameTitle = new Label("ICEBERGS", Assets.skin());
        gameTitle.setFontScale(2);
        gameTitle.setAlignment(Align.center);
        pressAny = new Label(Main.isMobile() ? "Tap to begin" : "Press any key", Assets.skin());
        leaderboard = new Label("Leaderboard", Assets.skin());
        leaderboard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Main.service.ShowLeaderboard();
            }
        });

        Table table = new Table();
        table.padBottom(Main.REFERENCE_HEIGHT).padTop(Main.REFERENCE_HEIGHT);
        table.setDebug(Main.DEBUG);
        table.setFillParent(true);

        table.add(gameTitle).center().expand();
        table.row().padTop(Main.REFERENCE_WIDTH /2);
        table.add(pressAny).center();
        table.row().padTop(Main.REFERENCE_WIDTH /2);
        table.add(leaderboard).center();
        table.row().padTop(Main.REFERENCE_WIDTH /2).padBottom(20);
        table.add(colorButton);
        table.row().padTop(Main.REFERENCE_WIDTH /2);

        stage.addActor(table);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (!event.isHandled()) {
                    ScreenController.get().transitionTo(GameScreen.class);
                }
            }
        });
    }

    @Override
    public void update() {
        stage.act();
        gameTitle.localToStageCoordinates(temp.set(gameTitle.getWidth()/2, gameTitle.getHeight()/2));
    }

    @Override
    public void onExit() {

    }

    @Override
    public void onEnter() {
        AudioManager.playMusic(Assets.MUSIC_ICE);
        gameTitle.setColor(Main.darkerColor);
        pressAny.setColor(Main.darkerColor);
        pressAny.clearActions();
        leaderboard.setColor(Main.darkerColor);
        pressAny.addAction(Actions.repeat(900000, Actions.sequence(Actions.fadeOut(0.5f), Actions.fadeIn(0.5f))));
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.end();
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
        Main.shapeRenderer.setColor(Color.WHITE);
        Main.shapeRenderer.circle(temp.x, temp.y, (float)(Math.sin(Main.gameTime)/8 + 0.875f) * Main.REFERENCE_WIDTH * 5);
        Main.shapeRenderer.end();
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
        return Main.waterColor;
    }
}
