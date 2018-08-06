package com.happygo.nksy.jam18;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
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
	public static final Color leadColor = new Color();
	public static final Color darkerColor = new Color();
	public static final Color lighterColor = new Color();
	public static final Color waterColor = new Color();

	private static final float FRAME_RATE = 1/60f;

	private SpriteBatch batch;

	@Override
	public void create() {
		Assets.loadAll();
		while (!Assets.isReady()) {} // synch wait
		Assets.skin().getFont("font").getData().markupEnabled = true;
		float factor = 0.1f;
		if (isMobile()) {
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

	public static boolean isMobile() {
		return Gdx.app.getType().equals(Application.ApplicationType.Android) || Gdx.app.getType().equals(Application.ApplicationType.iOS);
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

	public static void rerollColor() {
		leadColor.set(MathUtils.random(0.4f, 0.9f), MathUtils.random(0.4f, 0.9f), MathUtils.random(0.3f, 0.5f), 1);
		waterColor.set(leadColor).mul(MathUtils.random(1.08f, 1.12f));
		waterColor.b += MathUtils.random(0.15f, 0.3f);
		waterColor.a = 1;
		lighterColor.set(leadColor).mul(MathUtils.random(1.3f, 1.5f));
		lighterColor.a = 1;
		darkerColor.set(leadColor).mul(MathUtils.random(0.5f, 0.7f));
		darkerColor.a = 1;
	}
}
