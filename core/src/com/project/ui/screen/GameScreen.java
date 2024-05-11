package com.project.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.project.game.Data;
import com.project.game.game.GameRoot;
import com.project.ui.BaseScreen;
import com.project.ui.UiApp;

public class GameScreen extends BaseScreen {
    private int[] hp;
    private final Table[] healthBar;
    private final Image[][] healthPlayer;
    private final Label[] playerHPPoint;
    private final Data data;

    public GameScreen(UiApp app) {
        super(app);
        Texture imgHeart = new Texture(Gdx.files.internal("sprites/heart.png"));
        healthBar = new Table[] {new Table(), new Table()};

        table.setBackground(app.skin.getDrawable("window1"));
        table.setColor(app.skin.getColor("lt-green"));
        table.addActor(new GameRoot(this).init());

        data = new Data();

        playerHPPoint = new Label[2];
        playerHPPoint[0] = new Label("PLAYER 1: ", app.skin);
        playerHPPoint[0].setTouchable(Touchable.disabled);
        playerHPPoint[1] = new Label("PLAYER 2: ", app.skin);
        playerHPPoint[1].setTouchable(Touchable.disabled);

        healthPlayer = new Image[2][hp[0]];
        for (int i = 0; i < hp[0]; i++) {
            Image image = new Image(imgHeart);
            healthPlayer[0][i] = image;
        }

        for (int i = 0; i < hp[1]; i++) {
            Image image = new Image(imgHeart);
            healthPlayer[1][i] = image;
        }

        table.bottom();
        healthBar[0].add(playerHPPoint[0]);
        for (Image image : healthPlayer[0])
            healthBar[0].add(image).width(20).height(20).pad(5);
        table.add(healthBar[0]);

        healthBar[1].add(playerHPPoint[1]);
        for (Image image : healthPlayer[1])
            healthBar[1].add(image).width(20).height(20).pad(5);
        table.add(healthBar[1]);
    }

    public void heartChange(int hp, int index) {
        healthBar[index].clearChildren();
        healthPlayer[index][hp] = new Image();
        healthBar[index].add(playerHPPoint[index]);
        for (Image image : healthPlayer[index])
            healthBar[index].add(image).width(20).height(20).pad(5);

        if (hp == 0){
            if (index == 0) {
                gameOver(1);
                String s = "Player 02";
                data.postData(s);
            }
            if (index == 1) {
                gameOver(0);
                String s = "Player 01";
                data.postData(s);
            }
        }
    }

    public void gameOver(int index) {
        app.switchScreens(new GameOverScreen(app, index));
    }

    public void setHp(int[] hp) {
        this.hp = hp;
    }

    public void setHp(int hp, int index) {
        this.hp[index] = hp;
    }

    public int[] getHp() {
        return hp;
    }
}
