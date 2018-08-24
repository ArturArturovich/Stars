package ru.geekbrains.archibald;


import com.badlogic.gdx.math.Vector2;

public class Bullet {
   private Vector2 position;
   private Vector2 speed;
   private boolean active;
    private Hero owner;
    private int damage;
    private float time;
    private float maxTime;

    public  Hero getOwner() { return owner;}

    public Vector2 getPosition() { return position;}

    public boolean isActive() {return active;}

    public Bullet() {
        this.position = new Vector2(0, 0);
        this.speed = new Vector2(1080, 0);
        this.active = false;
        this.damage = 20;
        this.maxTime = 2.0f;
    }

    public void destroy() {active = false;}

    public void setup(Hero owner, float x, float y, float vx, float vy){
        this.owner = owner;
        this.position.x = x;
        this.position.y = y;
        this.speed.x = vx;
        this.speed.y = vy;
        this.time = 0.0f;
        this.active = true;
    }

    public void activate(float x, float y){
        position.set(x, y);
        active = true;
    }

    public void update(float dt){
        position.x += speed.x * dt;
        position.y += speed.y * dt;
        time += dt;
        if (time > maxTime || position.x > 1920)
            destroy();
        }
    }

