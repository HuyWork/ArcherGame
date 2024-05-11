package com.project.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.project.ui.BaseScreen;
import com.project.ui.screen.MenuScreen;
import com.project.ui.Styles;
import com.project.ui.UiApp;

public class ArcherGame extends UiApp {

	@Override
	protected String atlasPath() {
		return "data/tex.atlas";
	}

	@Override
	protected String skinPath() {
		return null;
	}

	@Override
	protected void styleSkin(Skin skin, TextureAtlas atlas) {
		new Styles().styleSkin(skin, atlas);
	}

	@Override
	protected BaseScreen getFirstScreen() {
		return new MenuScreen(this);
	}
}
