package com.project.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.project.ui.BaseScreen;
import com.project.ui.UiApp;

public class MenuScreen extends BaseScreen {
    public MenuScreen(final UiApp app) {
        super(app);

        final TextButton startButton = new TextButton("Start", app.skin);
        final TextButton quitButton = new TextButton("Quit", app.skin);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new GameScreen(app));
                startButton.setChecked(false);
            }
        });
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.defaults().pad(6f);
        table.setBackground(app.skin.getDrawable("window1"));
        table.setColor(app.skin.getColor("lt-blue"));
        table.add(label("ARCHER GAME", Color.GREEN));
        table.row();
        table.add(startButton);
        table.row();
        table.add(quitButton);
        table.row();
        table.add(label("To play:\nclick the objects moving around\nbefore they turn fully red.", Color.LIGHT_GRAY));
        table.row();
        table.add(label("If you don't and one turns red,\nyou will get a strike.", Color.LIGHT_GRAY));
        table.row();
        table.add(label("3 strikes and you are out!", Color.RED));
    }

    private Label label(String text, Color color) {
        Label label = new Label(text, app.skin);
        label.setAlignment(Align.center, Align.center);
        label.setColor(color);
        return label;
    }
}
