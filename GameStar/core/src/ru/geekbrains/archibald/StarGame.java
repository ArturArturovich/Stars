package ru.geekbrains.archibald;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Hero hero;
	Enemy[] enemies;
	static Bullet[] bullets;
	Texture textureBullet;
	

	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		textureBullet = new Texture("bullet64x32.png");
		hero = new Hero();
		enemies = new Enemy[20];
		for (int i = 0; i < enemies.length; i++) {
			enemies[i] = new Enemy();
		}
		bullets = new Bullet[100];
		for (int i = 0; i < bullets.length ; i++) {
			bullets[i] = new Bullet();
		}

	}


	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        background.render(batch);
        hero.render(batch);
		for (int i = 0; i < enemies.length ; i++) {
			enemies[i].render(batch);
		}
		for (int i = 0; i < bullets.length ; i++) {
			if(bullets[i].active){
				batch.draw(textureBullet, bullets[i].position.x - 32, bullets[i]. position.y - 16);
			}
		}
		batch.end();
	}

	public void update(){
    	background.update();
    	hero.update();
		for (int i = 0; i < enemies.length; i++) {
			enemies[i].update();
		}
		for (int i = 0; i < bullets.length; i++) {
			if(bullets[i].active){
				bullets[i].update();
				for (int j = 0; j < enemies.length; j++) {
					if(enemies[j].hitBox.contains(bullets[i].position)){
						bullets[i].diactivate();
						enemies[j].recreate();
					}
					
				}
			}
		}
	}




	public void dispose () {
		batch.dispose();

	}
}
