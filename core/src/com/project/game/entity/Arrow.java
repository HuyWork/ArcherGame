package com.project.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Arrow {
    private final Sprite sprite;
    private final Vector2 position;
    private final float degrees;

    public Arrow(Texture texture, Vector2 position, float degrees) {
        this.sprite = new Sprite(texture);
        this.position = position;
        this.degrees = degrees;

        if(degrees == 0) {
            position.x += 100; position.y += 25;
        }
        if(degrees == 180) {
            position.x -= 85; position.y += 35;
        }
        if(degrees == 90) {
            position.x += 15; position.y += 130;
        }
        if(degrees == -90) {
            position.y -= 60;
        }

        sprite.setPosition(position.x, position.y);
        sprite.setRotation(degrees);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void impulses() {
        float speed = 7;
        if(degrees == 0) position.x += speed;
        if(degrees == 180) position.x -= speed;
        if(degrees == 90) position.y += speed;
        if(degrees == -90) position.y -= speed;
    }

    public void move() {
        sprite.setPosition(position.x, position.y);

    }

    public void update() {
        move();
        impulses();
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
