package com.project.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.project.ui.screen.GameScreen;

public class GameRoot extends Group {
    private final GameScreen screen;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    Game game;

    Texture gameBg;

    public GameRoot(GameScreen screen) {
        this.screen = screen;
    }

    public GameRoot init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        gameBg = new Texture(Gdx.files.internal("sprites/game_bg.png"));

        game = new Game(screen);
        return this;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        camera.update();
        this.batch.setProjectionMatrix(camera.combined);

        this.batch.begin();
        this.batch.draw(gameBg, 0, 0, 800, 480);
        game.dawn(this.batch);
        this.batch.end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        game.update(delta);
    }
    }
