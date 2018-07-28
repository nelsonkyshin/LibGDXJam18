package com.happygo.nksy.jam18.input;

import com.badlogic.gdx.input.GestureDetector;

public class MobileController extends GestureDetector.GestureAdapter {

    private IInputProcessor inputProcessor;

    public MobileController(IInputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return inputProcessor.stimulus(x, y);
    }

}
