package com.happygo.nksy.jam18.input;

import com.badlogic.gdx.InputAdapter;

public class DesktopController extends InputAdapter {

    private IInputProcessor inputProcessor;

    public DesktopController(IInputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return inputProcessor.stimulus(screenX, screenY);
    }
}
