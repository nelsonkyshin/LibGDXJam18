package com.happygo.nksy.jam18.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.happygo.nksy.jam18.Main;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(580, 860);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new Main();
        }
}