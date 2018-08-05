package com.happygo.nksy.jam18;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.happygo.nksy.jam18.assets.Assets;
import com.happygo.nksy.jam18.screen.SplashScreen;
import com.happygo.nksy.jam18.screen.camera.JamCamera;
import com.happygo.nksy.jam18.screen.ScreenController;

public class Main extends ApplicationAdapter {

	public static final boolean DEBUG = !true;
	public static ShapeRenderer shapeRenderer;
	public static float REFERENCE_WIDTH;
	public static float REFERENCE_HEIGHT;
	public static float gameTime = 0;
	public static float dT;

	private static final float FRAME_RATE = 1/60f;

	private SpriteBatch batch;

	@Override
	public void create() {
		Assets.loadAll();
		while (!Assets.isReady()) {} // synch wait
		Assets.skin().getFont("font").getData().markupEnabled = true;
		float factor = 0.1f;
		if (Gdx.app.getType().equals(Application.ApplicationType.Android) || Gdx.app.getType().equals(Application.ApplicationType.iOS)) {
			factor = 0.025f;
		}
		REFERENCE_WIDTH = Gdx.graphics.getWidth() * factor;
		REFERENCE_HEIGHT = Gdx.graphics.getHeight() * factor;

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.setColor(Color.WHITE);
		batch = new SpriteBatch();
		ScreenController.get().transitionTo(SplashScreen.class);
	}

	public void update() {
		dT = Math.min(FRAME_RATE, Gdx.graphics.getDeltaTime());
		gameTime += dT;
		ScreenController.get().update();
	}

	@Override
	public void render() {
		update();
		Color clear = ScreenController.get().getClearColor();
		Gdx.gl.glClearColor(clear.r, clear.g, clear.b, clear.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(JamCamera.get().combined);
		shapeRenderer.setProjectionMatrix(JamCamera.get().combined);
		batch.begin();
		ScreenController.get().render(batch);
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
		ScreenController.get().dispose();
	}
}
