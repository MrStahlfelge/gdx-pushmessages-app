package de.golfgl.gdxpushmsgsapp.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import de.golfgl.gdxpushmsgsapp.GdxPushMsgsApp;

public class HtmlLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig() {
		GwtApplicationConfiguration config = new GwtApplicationConfiguration(800, 600);
		config.preferFlash = false;
		return config;
	}

	@Override
	public ApplicationListener createApplicationListener() {
		GdxPushMsgsApp game = new GdxPushMsgsApp();
		return game;
	}
}