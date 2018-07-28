package com.happygo.nksy.jam18;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.happygo.nksy.jam18.screen.GameScreen;
import com.happygo.nksy.jam18.screen.JamCamera;

public class Main extends ApplicationAdapter {

	public static ShapeRenderer shapeRenderer;
	public static float gameTime = 0;
	public static float dT;

	private static final float FRAME_RATE = 1/60f;

	private GameScreen gameScreen;
	SpriteBatch batch;

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.setColor(Color.WHITE);
		batch = new SpriteBatch();
		gameScreen = new GameScreen();
		Gdx.input.setInputProcessor(gameScreen.getInputMultiplexer());
	}

	public void update() {
		dT = Math.max(FRAME_RATE, Gdx.graphics.getDeltaTime());
		gameTime += dT;
		gameScreen.update();
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0.1f, 0.6f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(JamCamera.get().combined);
		shapeRenderer.setProjectionMatrix(JamCamera.get().combined);
		batch.begin();
		gameScreen.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
	}
}
