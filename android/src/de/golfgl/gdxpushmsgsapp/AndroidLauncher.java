package de.golfgl.gdxpushmsgsapp;

import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import de.golfgl.gdxpushmessages.FcmMessageProvider;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		GdxPushMsgsApp game = new GdxPushMsgsApp();
		FcmMessageProvider pushMessageProvider = new FcmMessageProvider(this);
		game.pushMessageProvider = pushMessageProvider;
		initialize(game, config);
		Gdx.app.addLifecycleListener(pushMessageProvider);
	}
}
