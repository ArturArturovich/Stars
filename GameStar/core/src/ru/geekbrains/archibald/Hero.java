package ru.geekbrains.archibald;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Hero {

    private Texture texture;
    private Texture textureHpBar;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 weaponSlotPosition;
    private float acc;
    private float maxVelocity;
    private Weapon weapon;
    private Circle circle;
    private int lifes;
    private int hp, hpMax;
    private int score;
    private int keyUp, keyDown, keyRight, keyLeft, keyFire;

    public int getLifes() {
        return lifes;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Circle getCircle() {
        return circle;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getHp() {
        return hp;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int n) {
        score += n;
    }

    public Hero(int keyLeft, int keyRight, int keyUp, int keyDown, int keyFire) {
        this.texture = new Texture("ship65.png");
        this.textureHpBar = new Texture("hpBar.png");
        this.position = new Vector2(60, 330);
        this.weaponSlotPosition = position.cpy().add(24, 24);
        this.weapon = new Weapon(this, weaponSlotPosition);
        this.hpMax = 50;
        this.hp = hpMax;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyFire = keyFire;
        this.velocity = new Vector2(0, 0);
        this.acc = 20.0f;
        this.score = 0;
        this.lifes = 3;
        this.maxVelocity = 40.0f;
        this.circle = new Circle(position, 24);
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            if (lifes > 0) {
                lifes--;
                hp = hpMax;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isAlive()) {
            batch.draw(texture, position.x - 32, position.y - 32);
            //batch.draw(weapon.getTexture(), weaponSlotPosition.x, weaponSlotPosition.y);
        }
    }

    public void renderHUD(SpriteBatch batch, BitmapFont font, int x, int y) {
        font.draw(batch, "Score: " + score, x + 20, y);
        batch.draw(textureHpBar, x, y - 35, 0, 0, 200, 20);
        batch.draw(textureHpBar, x, y - 35, 0, 20, (int) (200 * ((float) hp / (float) hpMax)), 20);
        font.draw(batch, "x" + lifes, x + 205, y - 24);
    }

    public void update(float dt) {
        if (isAlive()) {
            if (Gdx.input.isKeyPressed(keyUp)) velocity.y += acc * dt;
            if (Gdx.input.isKeyPressed(keyDown)) velocity.y -= acc * dt;
            if (Gdx.input.isKeyPressed(keyLeft)) velocity.x -= acc * dt;
            if (Gdx.input.isKeyPressed(keyRight)) velocity.x += acc * dt;

            if (position.y > 698) position.y = 698;
            if (position.y < 32) position.y = 32;
            if (position.x < 32) position.x = 32;
            if (position.x > 1248) position.x = 1248;

            velocity.scl(0.96f);
            if (velocity.len() > maxVelocity) velocity.nor().scl(maxVelocity);
            position.add(velocity);
            weaponSlotPosition.x = position.x - 8;
            weaponSlotPosition.y = position.y - 14;
            if (Gdx.input.isKeyPressed(keyFire)) weapon.use();
            circle.x = position.x;
            circle.y = position.y;

        }

    }
}
