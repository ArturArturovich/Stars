package ru.geekbrains.archibald;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGame extends ApplicationAdapter {
//    private SpriteBatch batch;
//    private Background background;
//    private Hero hero1;
//    private float timer;
//    private BitmapFont font;
//    public static int level = 1;
//
//
//    public void create() {
//        timer = 0.0f;
//        batch = new SpriteBatch();
//        background = new Background();
//        hero1 = new Hero(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.P);
//        BotsEmitter.getInstance().emitMany(10);
//        font = new BitmapFont();
//
//    }
//
//
//    public void render() {
//        float dt = Gdx.graphics.getDeltaTime();
//        update(dt);
//        Gdx.gl.glClearColor(0f, 0f, 0f, 1.01f);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.enableBlending();
//        batch.begin();
//        background.render(batch);
//        hero1.render(batch);
//        BotsEmitter.getInstance().render(batch);
//        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
//        BulletEmitter.getInstance().render(batch);
//        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//
//        if (hero1.isAlive()) {
//            font.draw(batch, "Level: " + level + "   INFO: WASD - Move, P(press) - Fire", 40, 40);
//            hero1.renderHUD(batch, font, 40, 680);
//        } else {
//            font.draw(batch, "PRESS SPACE TO RESTART", 500, 60);
//            font.draw(batch, "YOUR SCORE: " + hero1.getScore(), 530, 40);
//        }
//
//        batch.end();
//    }
//
//    public void startNewGame() {
//        hero1 = new Hero(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.P);
//        BotsEmitter.getInstance().reset();
//        BulletEmitter.getInstance().reset();
//        BotsEmitter.getInstance().emitMany(10);
//    }
//
//    public void update(float dt) {
//        timer += Gdx.graphics.getDeltaTime();
//        if (timer > 40.0f) {
//            timer = 0.0f;
//            level++;
//        }
//        background.update(dt);
//        hero1.update(dt);
//        BotsEmitter.getInstance().update(dt);
//        BulletEmitter.getInstance().update(dt);
//        LogicHelper.checkIntersection();
//        LogicHelper.checkHeroAsteroidIntersection(hero1);
//        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
//            if (hero1.getHp() <= 0) startNewGame();
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
//
//    }
//
//    public void dispose() { batch.dispose();}
//}

    private SpriteBatch batch;
    private Background background;
    private Hero hero1;
    private float timer;
    private BitmapFont font;
    public static int level = 1;

    @Override
    public void create() {
        timer = 0.0f;
        batch = new SpriteBatch();
        background = new Background();
        hero1 = new Hero(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.P);
        BotsEmitter.getInstance().emitMany(10);
        font = new BitmapFont();
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1.01f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.enableBlending();
        batch.begin();
        background.render(batch);
        hero1.render(batch);
        BotsEmitter.getInstance().render(batch);
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        BulletEmitter.getInstance().render(batch);
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        if(hero1.isAlive()) {
            font.draw(batch, "Level: " + level + "   INFO: WASD - Move, P(press) - Fire", 40, 40);
            hero1.renderHUD(batch, font, 40, 680);
        } else {
            font.draw(batch, "PRESS SPACE TO RESTART", 500, 60);
            font.draw(batch, "YOUR SCORE: " + hero1.getScore(), 530, 40);
        }


        batch.end();
    }

    public void startNewGame() {
        hero1 = new Hero(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.P);
        BotsEmitter.getInstance().reset();
        BulletEmitter.getInstance().reset();
        BotsEmitter.getInstance().emitMany(10);
    }

    public void update(float dt) {
        timer += Gdx.graphics.getDeltaTime();
        if (timer > 40.0f) {
            timer = 0.0f;
            level++;
        }
        background.update(dt);
        hero1.update(dt);
        BotsEmitter.getInstance().update(dt);
        BulletEmitter.getInstance().update(dt);
        LogicHelper.checkIntersection();
        LogicHelper.checkHeroAsteroidIntersection(hero1);
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if(hero1.getHp() <= 0) startNewGame();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}