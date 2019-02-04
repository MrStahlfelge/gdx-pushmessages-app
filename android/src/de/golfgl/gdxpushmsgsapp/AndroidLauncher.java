package de.golfgl.gdxpushmsgsapp;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import de.golfgl.gdxpushmessages.FcmMessageProvider;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		GdxPushMsgsApp game = new GdxPushMsgsApp();
		game.pushMessageProvider = new FcmMessageProvider();
		initialize(game, config);
	}
}
