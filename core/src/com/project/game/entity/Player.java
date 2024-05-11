package com.project.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Player {
    private final Sprite sprite;
    private final Vector2 position;
    private float degrees;
    private final int[] keys;
    private final Sound[] sounds;
    private final int index;
    private int hp = 4;
    private float currentTime = 0;
    private boolean isShoot;

    public Player(Texture texture, Vector2 position, float degrees, int[] keys, Sound[] sounds, int index) {
        this.sprite = new Sprite(texture);
        this.position = position;
        this.degrees = degrees;
        this.keys = keys;
        this.sounds = sounds;
        this.index = index;
        sprite.setPosition(position.x, position.y);
        sprite.setRotation(degrees);
    }

    public void handleInput() {
        float velocity = 4;
        if (Gdx.input.isKeyPressed(keys[0])) {
            position.y += velocity;
            degrees = 90;
        }
        if (Gdx.input.isKeyPressed(keys[1])) {
            position.y -= velocity;
            degrees = -90;
        }
        if (Gdx.input.isKeyPressed(keys[2])) {
            position.x -= velocity;
            degrees = 180;
        }
        if (Gdx.input.isKeyPressed(keys[3])) {
            position.x += velocity;
            degrees = 0;
        }
        if (Gdx.input.isKeyJustPressed(keys[4])) {
            if (currentTime <= 0) {
                isShoot = true;
                currentTime = 1;
            }
        }
    }

    public void move() {
        if(position.x <= 0) position.x = 0;
        if(position.x >= 700) position.x = 700;
        if(position.y <= 0) position.y = 0;
        if(position.y >= 380) position.y = 380;

        sprite.setPosition(position.x, position.y);
        sprite.setRotation(degrees);
    }

    public void shoot(Texture texture, Array<Arrow> arrows) {
        if (isShoot) {
            Arrow arrow = new Arrow(texture,
                    new Vector2(position.x, position.y), degrees);
            arrows.add(arrow);
            sounds[0].play();
            isShoot = false;
        }

    }

    public int getHp() {
        return hp;
    }

    public int getIndex() {
        return index;
    }

    public void detectCollision(Array<Arrow> arrows) {
        Rectangle playerRect = sprite.getBoundingRectangle();

        Iterator<Arrow> iterator = arrows.iterator();

        while (iterator.hasNext()) {
            Arrow arrow = iterator.next();
            Rectangle arrowRect = arrow.getSprite().getBoundingRectangle();
            if (playerRect.overlaps(arrowRect)) {
                sounds[1].play();
                hp -= 1;
                iterator.remove();
            }
        }
    }

    public void update(Texture texture, Array<Arrow> arrows, float deltaTime) {
        currentTime -= deltaTime;

        handleInput();
        detectCollision(arrows);
        shoot(texture, arrows);
        move();
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }


}
