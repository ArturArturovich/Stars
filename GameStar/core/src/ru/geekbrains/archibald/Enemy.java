package ru.geekbrains.archibald;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
   private Texture texture;
   private Vector2 position;
   private float speed;
   private float angle;
   private int hp;
   private int maxHp;
   private boolean active;
   private Circle circle;
   private float size;
   private int type;

   public Vector2 getPosition() { return position;}

   public Circle getCircle() {return circle;}

   public boolean isActive() {return active;}

   public int getMaxHp() {return maxHp;}

    public Enemy (){
//       if (texture == null){
//           texture = new Texture ("astroship64.png");
//       }
       if (texture == null){
           texture = new Texture("asteroids64.png");
       }
        this.position = new Vector2(0,0);
        this.active =false;
        this.size = 1.5f;
        this.circle = new Circle(position, 24 * size);
    }

    public void setup(){
       position.x = (float) Math.random() * 1920 + 1920;
       position.y = (float) Math.random() * 1080;
       speed = 100.0f + (float) Math.random() * 300.0f;
       angle = (float) Math.random() * 540;
       maxHp = 5 + (int)(Math.random() * 5);
       hp = maxHp;
       size = 0.5f + hp / 10.0f;
       circle.setRadius(24 * size);
       active = true;
       type = (int)(Math.random() * 4);
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 32, position.y - 32, 32, 32, 64, 64, size, size, angle, type * 64, 0, 64, 64, false, false );

    }

    public void disable() {active = false;}

    public boolean takeDamage(int dmg){
       hp -= dmg;
       size = 0.5f + hp / 10.0f;
       circle.setRadius(size * 24);
       if (hp <= 0){
           disable();
           return true;
       }
       return false;
    }

    public  void update(float dt){
        position.x -= speed * dt;
        angle += speed * dt / 2;
        if(position.x < -60){
            disable();
        }
       circle.x = position.x;
       circle.y = position.y;
    }
}
