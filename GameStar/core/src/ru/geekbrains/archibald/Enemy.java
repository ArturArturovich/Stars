package ru.geekbrains.archibald;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {

    Texture texture;
    Vector2 position;
    float speed;
    float speedY;
    Rectangle hitBox;

    public Enemy (){
        texture = new Texture ("astroship64.png");
        position = new Vector2(MathUtils.random(1280, 2560), MathUtils.random(0, 720));
        speed = MathUtils.random(3.0f, 6.0f);
        speedY = MathUtils.random(-1.0f, 1.0f);
        hitBox = new Rectangle(position.x, position.y, 64, 64);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);

    }
    public void recreate(){
        position.set(MathUtils.random(1280, 2560), MathUtils.random(0, 720));
        speed = MathUtils.random(3.0f, 6.0f);


    }

    public  void update(){
        position.x -= speed;
        position.y += speedY;
        if(position.x < -100){
            recreate();
        }
        hitBox.setPosition(position);
    }
}
