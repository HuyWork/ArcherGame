package com.project.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.project.ui.BaseScreen;
import com.project.ui.UiApp;

public class GameOverScreen extends BaseScreen {
    public GameOverScreen(UiApp app, int index) {
        super(app);

        table.setBackground(app.skin.getDrawable("window1"));
        table.setColor(app.skin.getColor("lt-blue"));
        table.setSkin(app.skin);
        table.add("Game Over!");
        table.row();
        table.add("Player " + (index + 1) + " Victory");

        final TextButton restartButton = new TextButton("Restart", app.skin);
        final TextButton quitButton = new TextButton("quit", app.skin);

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new GameScreen(app));
                restartButton.setChecked(false);
            }
        });
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.row();
        table.add(restartButton);
        table.row();
        table.add(quitButton);
    }
}
