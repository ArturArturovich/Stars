package ru.geekbrains.archibald;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {

    Texture texture;
    Vector2 position;
    float speed;
    int fireCounter;

    public Hero() {
        texture = new Texture("ship65.png");
        position = new Vector2(100, 328);
        speed = 10.0f;
        fireCounter = 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.L)) {
            fireCounter++;
            if(fireCounter >= 8){
                fireCounter = 0;
                fire();
            }
        }

        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() < position.x + 32) {
                position.x -= speed;
            }
            if (Gdx.input.getX() > position.x + 32) {
                position.x += speed;
            }
        }

        if (position.x < 0) {
            position.x = 0;
        }
        if (position.x > 1280 - texture.getWidth()) {
            position.x = 1280 - texture.getWidth();
        }
        if (position.y > 784 - texture.getWidth()) {
            position.y = texture.getWidth();
        }
        if (position.y < texture.getWidth()) {
            position.y = 784 - texture.getWidth();
        }

    }

    public void fire() {
        for (int i = 0; i < StarGame.bullets.length; i++) {
            if(!StarGame.bullets[i].active){
                StarGame.bullets[i].activate(position.x + 48, position.y + 32);
                break;
            }

        }
    }
}
