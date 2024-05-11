package com.project.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.project.game.entity.Arrow;
import com.project.game.entity.Player;
import com.project.ui.screen.GameScreen;

public class Game {
    private final Player player01;
    private final Player player02;
    private final Array<Arrow> arrows;
    private final Array<Player> players;

    public Texture imgArrow, imgPlayer01, imgPlayer02;
    private final Music moonlightMusic;
    private final Sound shootSound;
    private final Sound damagedSound;
    private final GameScreen screen;

    public Game(GameScreen screen) {
        imgPlayer01 = new Texture(Gdx.files.internal("sprites/archer_01.png"));
        imgPlayer02 = new Texture(Gdx.files.internal("sprites/archer_02.png"));
        imgArrow = new Texture(Gdx.files.internal("sprites/arrow.png"));

        moonlightMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/moonlight.mp3"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("audio/shoot.wav"));
        damagedSound = Gdx.audio.newSound(Gdx.files.internal("audio/damaged.wav"));

        Sound[] sounds = new Sound[] {shootSound, damagedSound};


        // start the playback of the background music immediately
        moonlightMusic.setLooping(true);
        moonlightMusic.play();

        int[] keysPlayer01 = new int[]{Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D, Input.Keys.SPACE};
        int[] keysPlayer02 = new int[]{Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.ENTER};

        player01 = new Player(imgPlayer01, new Vector2(100, 190), 0, keysPlayer01, sounds, 0);
        player02 = new Player(imgPlayer02, new Vector2(600, 190), 180, keysPlayer02, sounds, 1);

        arrows = new Array<Arrow>();
        players = new Array<Player>();
        players.add(player01); players.add(player02);
        this.screen = screen;
        this.screen.setHp(new int[] {player01.getHp(), player02.getHp()});
    }

    public void update(float delta) {
        player01.update(imgArrow, arrows, delta);
        player02.update(imgArrow, arrows, delta);
        for(Arrow arrow: arrows) {
            arrow.update();
        }
        for(Player player : players){
            if(screen.getHp()[player.getIndex()] != player.getHp()){
                screen.setHp(player.getHp(), player.getIndex());
                screen.heartChange(player.getHp(), player.getIndex());
            }
        }
    }

    public void dispose() {
        imgPlayer01.dispose();
        imgPlayer02.dispose();
        moonlightMusic.dispose();
        shootSound.dispose();
        damagedSound.dispose();
        imgArrow.dispose();
    }

    public void dawn(SpriteBatch batch) {
        player01.draw(batch);
        player02.draw(batch);
        for(Arrow arrow: arrows) {
            arrow.draw(batch);
        }
    }
}
