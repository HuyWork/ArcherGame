package com.project.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public abstract class UiApp implements ApplicationListener {
    public Stage stage;
    public TextureAtlas atlas;
    public Skin skin;

    public static InputMultiplexer multiplexer = new InputMultiplexer();

    public float width, height;

    private BaseScreen currentScreen, nextScreen;

    public float defaultDur = .333f;
    private float durAccumulo = -420f;

    private final Color clearColor = new Color(Color.BLACK);

    @Override
    public void create() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        stage = new Stage(new StretchViewport(width, height));
        atlas = new TextureAtlas(atlasPath());
        skin = new Skin(atlas);
        String skinPath = skinPath();
        if (skinPath != null)
            skin.load(Gdx.files.internal(skinPath));
        styleSkin(skin, atlas);

        Gdx.input.setInputProcessor(stage);
        currentScreen = getFirstScreen().show();
        stage.addActor(currentScreen);
        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(stage);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        if (durAccumulo > 0f) {
            durAccumulo -= delta;
            if (durAccumulo <= 0f) {
                currentScreen.hide();
                currentScreen.remove();
                currentScreen = nextScreen;
                currentScreen.setTouchable(Touchable.enabled);
                currentScreen.setPosition(0f, 0f);
                nextScreen = null;
            }
        }
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getWidth();
        stage.getViewport().setWorldSize(this.width, this.height);
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
    }

    public void switchScreens(BaseScreen screen) {
        durAccumulo = currentScreen.dur;
        nextScreen = screen;
        nextScreen.setTouchable(Touchable.disabled);
        nextScreen.show();
        stage.addActor(nextScreen);
        if (currentScreen != null) {
            currentScreen.screenOut();
            currentScreen.setTouchable(Touchable.disabled);
            currentScreen.toFront();
        }
    }

    /** provide the path to the atlas */
    protected abstract String atlasPath();

    /** provide the path to the skin file (optional). If no path is provided, an empty skin is created. */
    protected abstract String skinPath();

    /** add the skin styles here (optional, can be null) */
    protected abstract void styleSkin(Skin skin, TextureAtlas atlas);

    /** specify the screen to be loaded at the beginning */
    protected abstract BaseScreen getFirstScreen();
}
