package ru.geekbrains.archibald;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class Background {
   private class Star{
       private Vector2 position;
       private float speed;

        public Star(){
            position = new Vector2((float) Math.random() * 1920, (float) Math.random() * 1080);
            speed = 5.0f + (float) Math.random() * 60.0f;
        }

         public void update(float dt){
            position.x -= speed * dt;
            if (position.x < -20){
                position.x = 1920;
                position.y = (float) Math.random() * 1080;
                speed = 5.0f + (float) Math.random() * 60.0f;
            }
         }
    }

   private Texture texture;
   private Texture starTexture;
   private Star[] stars;
   private final int STARS_COUNT = 400;

    public Background(){
        this.texture = new Texture("bg.png");
        this.starTexture = new Texture("star12.tga");
        this.stars = new Star[STARS_COUNT];
        for (int i = 0; i < stars.length; i++) {
            this.stars[i] = new Star();

        }
    }

    float t = 0;

    public void render(SpriteBatch batch){
        batch.draw(texture, -t,0);
        batch.draw(texture, -t + 1920, 0);
        for (int i = 0; i < stars.length ; i++) {
            batch.draw(starTexture, stars[i].position.x, stars[i].position.y, 6, 6, 12, 12, stars[i].speed / 100.0f + 0.2f, stars[i].speed / 100.0f + 0.2f, 0, 0, 0, 12, 12, false, false);

        }
    }

    public void update(float dt){
        t += dt * 40.0f;
        if (t > 1920) t -= 1920;
        for (int i = 0; i < stars.length ; i++) {
            stars[i]. update(dt);
        }

    }

}
