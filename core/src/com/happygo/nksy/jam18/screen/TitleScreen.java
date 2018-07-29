package com.happygo.nksy.jam18.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.happygo.nksy.jam18.AudioManager;
import com.happygo.nksy.jam18.Main;
import com.happygo.nksy.jam18.assets.Assets;

public class TitleScreen implements IScreen {

    private final Stage stage;
    private final InputMultiplexer inputMultiplexer;
    private final Color clearColor;
    private final Label gameTitle;
    private final Vector2 temp;

    public TitleScreen() {
        stage = new Stage(new ScalingViewport(Scaling.fill, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2));
        inputMultiplexer = new InputMultiplexer(stage);
        clearColor = new Color(0.1f, 0.6f, 0.8f, 1);
        temp = new Vector2();

        gameTitle = new Label("[#"+clearColor.toString()+"]ICEBERGS", Assets.skin());
        gameTitle.setFontScale(2);
        gameTitle.setAlignment(Align.center);
        Label pressAny = new Label("Press any key", Assets.skin());
        pressAny.addAction(Actions.repeat(900000, Actions.sequence(Actions.fadeOut(0.5f), Actions.fadeIn(0.5f))));
        Label info = new Label("www.nelsonyiap.com\nnelson.yiap@gmail.com", Assets.skin());
        info.setAlignment(Align.center);

        Table table = new Table();
        table.padBottom(Main.REFERENCE_UNIT).padTop(Main.REFERENCE_UNIT);
        table.setDebug(Main.DEBUG);
        table.setFillParent(true);

        table.add(gameTitle).center().expand();
        table.row().padTop(Main.REFERENCE_UNIT/2);
        table.add(pressAny).center();
        table.row().padTop(Main.REFERENCE_UNIT/2);
        table.add(info).bottom();

        stage.addActor(table);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenController.get().transitionTo(GameScreen.class);
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
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.end();
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
        Main.shapeRenderer.setColor(Color.WHITE);
        Main.shapeRenderer.circle(temp.x, temp.y, (float)(Math.sin(Main.gameTime)/8 + 0.875f) * Main.REFERENCE_UNIT*5);
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
        return clearColor;
    }

    public void reset() {

    }
}
