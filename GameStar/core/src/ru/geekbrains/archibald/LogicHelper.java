package ru.geekbrains.archibald;

public class LogicHelper {
    public static void checkIntersection() {
        for (Bullet b : BulletEmitter.getInstance().getBullets()) {
            for (Enemy a : BotsEmitter.getInstance().getEnemies()) {
                if (a.isActive() && a.getCircle().contains(b.getPosition())) {
                    if(a.takeDamage(1)) b.getOwner().addScore(a.getMaxHp() * 10);
                    b.destroy();
                    break;
                }
            }
        }
    }

    public static void checkHeroAsteroidIntersection(Hero hero) {
        for (Enemy o : BotsEmitter.getInstance().getEnemies()) {
            if (o.isActive() && hero.isAlive() && hero.getCircle().overlaps(o.getCircle())) {
                o.takeDamage(4);
                hero.takeDamage(4);
                hero.setVelocity((o.getPosition().cpy().sub(hero.getPosition())).nor().scl(-5));
            }
        }
    }
}
