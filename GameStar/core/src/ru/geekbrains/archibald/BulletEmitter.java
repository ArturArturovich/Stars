package ru.geekbrains.archibald;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class BulletEmitter {
    private static BulletEmitter ourInstance = new BulletEmitter();

    public static BulletEmitter getInstance() {
        return ourInstance;
    }

    private Stack<Bullet> stack;
    private LinkedList<Bullet> list;
    private Texture texture;
    private final int MAX_BULLETS_COUNT = 10000;

    public LinkedList<Bullet> getBullets() {
        return list;
    }

    private BulletEmitter() {
        this.texture = new Texture("bullet64x32.png");
        this.stack = new Stack<Bullet>();
        this.list = new LinkedList<Bullet>();
        for (int i = 0; i < MAX_BULLETS_COUNT; i++) {
            this.stack.add(new Bullet());
        }
    }

    public void reset() {
        Iterator<Bullet> iterator = list.iterator();
        while (iterator.hasNext()) {
            Bullet b = iterator.next();
            stack.push(b);
        }
        list.clear();
    }

    public void update(float dt) {
        Iterator<Bullet> iterator = list.iterator();
        while (iterator.hasNext()) {
            Bullet b = iterator.next();
            b.update(dt);
            if (!b.isActive()) {
                stack.push(b);
                iterator.remove();
            }
        }
    }

    public void setupAmmo(Hero hero, float x, float y, float vx, float vy) {
        Bullet b = stack.pop();
        b.setup(hero, x, y, vx, vy);
        list.add(b);
    }

    public void render(SpriteBatch batch) {
        Iterator<Bullet> iterator = list.iterator();
        while (iterator.hasNext()) {
            Bullet b = iterator.next();
            batch.draw(texture, b.getPosition().x - 30, b.getPosition().y - 16);
        }
    }
}
