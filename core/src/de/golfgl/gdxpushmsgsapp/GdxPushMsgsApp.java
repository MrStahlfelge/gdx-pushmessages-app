package de.golfgl.gdxpushmsgsapp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import de.golfgl.gdxpushmessages.IPushMessageListener;
import de.golfgl.gdxpushmessages.IPushMessageProvider;

public class GdxPushMsgsApp extends ApplicationAdapter implements IPushMessageListener {
	IPushMessageProvider pushMessageProvider;
	Skin skin;
	Stage stage;
	private TextureAtlas atlas;
	private Label tokenLabel;

	@Override
	public void create() {
		stage = new Stage(new ExtendViewport(800, 450));
		Gdx.input.setInputProcessor(stage);

		prepareSkin();

		prepareUI();

		if (pushMessageProvider != null)
			pushMessageProvider.initService(this);
		else
			tokenLabel.setText("(no push message provider)");
	}

	private void prepareUI() {
		Table mainTable = new Table();
		tokenLabel = new Label("(no token yet)", skin);

		mainTable.setFillParent(true);

		mainTable.row();
		mainTable.add(new Label("Token:", skin)).padRight(10);
		mainTable.add(tokenLabel);

		stage.addActor(mainTable);
	}


	private void prepareSkin() {
		// A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but
		// strongly
		// recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
		skin = new Skin();
		atlas = new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas"));
		skin.addRegions(atlas);
		skin.load(Gdx.files.internal("skin/uiskin.json"));

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		atlas.dispose();
	}

	@Override
	public void onRegistrationTokenRetrieved(final String token) {
		// a new token was retrieved
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				String displayToken = token;
				if (displayToken.length() > 30)
					displayToken = displayToken.substring(1, 30) + "...";
				tokenLabel.setText(displayToken);
			}
		});
	}

	@Override
	public void onPushMessageArrived(final String payload) {
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				MyDialog dialog = new MyDialog("Message arrived");
				dialog.text("Payload: " + (payload != null ? payload : "(none)"));

				dialog.show(stage);

			}
		});
	}

	public class MyDialog extends Dialog {
		public MyDialog(String title) {
			super(title, skin);
			super.button("OK");
		}

		public void reshow() {
			this.show(stage, Actions.alpha(1)).setPosition(Math.round((stage.getWidth() - this.getWidth()) / 2),
					Math.round((stage.getHeight() - this.getHeight()) / 2));

		}
	}
}
