package ru.geekbrains.archibald;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    Vector2 position;
    float speed;
    boolean active;

    public Bullet() {
        position = new Vector2(0, 0);
        speed = 14.0f;
        active = false;
    }

    public void activate(float x, float y){
        position.set(x, y);
        active = true;
    }

    public void diactivate(){
        active = false;
    }

    public void update(){
        position.x += speed;
        if (position.x > 1320){
            diactivate();
        }
    }

}