package ru.geekbrains.archibald;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BotsEmitter {

    private static BotsEmitter ourInstance = new BotsEmitter();

    public static BotsEmitter getInstance() {
        return ourInstance;
    }

    private float timer;
    private Enemy[] enemies;

    public Enemy[] getEnemies() {
        return enemies;
    }

    private BotsEmitter() {
        this.enemies = new Enemy[100];
        for (int i = 0; i < enemies.length; i++) {
            this.enemies[i] = new Enemy();
        }
    }

    public void reset() {
        for (int i = 0; i < enemies.length; i++) {
            this.enemies[i] = new Enemy();
        }
    }

    public void emitMany(int n) {
        for (int q = 0; q < n; q++) {
            for (int i = 0; i < enemies.length; i++) {
                if (!enemies[i].isActive()) {
                    enemies[i].setup();
                    break;
                }
            }
        }
    }

    public void update(float dt) {
        timer += dt;
        if (timer > 0.2f) {
            for (int i = 0; i < enemies.length; i++) {
                if (!enemies[i].isActive()) {
                    enemies[i].setup();
                    break;
                }
            }
            timer = 0.0f;
        }
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i].isActive())
                enemies[i].update(dt);
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i].isActive())
                enemies[i].render(batch);
        }
    }
}
