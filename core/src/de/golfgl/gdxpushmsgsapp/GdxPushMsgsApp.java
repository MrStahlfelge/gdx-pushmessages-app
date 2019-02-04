package de.golfgl.gdxpushmsgsapp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GdxPushMsgsApp extends ApplicationAdapter {
	Skin skin;
	Stage stage;
	private TextureAtlas atlas;

	@Override
	public void create() {
		stage = new Stage(new ExtendViewport(800, 450));
		Gdx.input.setInputProcessor(stage);

		prepareSkin();

		prepareUI();
	}

	private void prepareUI() {

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
