package ru.geekbrains.archibald;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Weapon {
    private int fireRate;
    private int fireCounter;
    private Vector2 slotPosition;
    private Texture texture;
    private Sound sound;
    private Hero owner;

    public Texture getTexture() {
        return texture;
    }

    public Weapon(Hero owner, Vector2 slotPosition) {
        this.owner = owner;
        this.texture = new Texture("weapon.png");
        this.slotPosition = slotPosition;
        this.fireRate = 3;
        this.sound = Gdx.audio.newSound(Gdx.files.internal("fr2.wav"));
    }

    public void use() {
        fireCounter++;
        if (fireCounter >= fireRate) {
            fireCounter = 0;
            fire(0, 0, 1080, 0);
            sound.play();
        }
    }

    public void fire(float dx, float dy, float vx, float vy) {
        BulletEmitter.getInstance().setupAmmo(owner, slotPosition.x + 24 + dx, slotPosition.y + 8 + dy, vx, vy);
    }
}
