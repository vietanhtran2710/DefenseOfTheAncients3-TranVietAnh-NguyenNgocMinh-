package Entity.Towers;

import Entity.Enemies.Enemy;
import Utils.Point;
import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class Bullet {
    private myTexture texture;
    private int damage;
    private Point target;
    private Enemy enemy;
    private int speed;
    private boolean hit;

    public Bullet(String imageSource, int damage, int speed, int x, int y) {
        this.texture = new myTexture(imageSource, GL_QUADS, x, y);
        this.damage = damage;
        this.speed = speed;
    }

    public Bullet(Bullet prototype, Enemy target) {
        myTexture oldTexture = prototype.texture;
        Point position = oldTexture.getTopLeft();
        this.texture = new myTexture(oldTexture.getSource(), GL_QUADS, position.getX(), position.getY());
        this.damage = prototype.damage;
        Point enemyPosition = target.getCoordinate();
        this.target = new Point(enemyPosition.getX() + 24, enemyPosition.getY() + 24);
        this.enemy = target;
        this.speed = prototype.speed;
        this.hit = false;
    }

    public Point getCoordinate() {
        return this.texture.getTopLeft();
    }

    public void move() {
        Point bulletPosition = this.getCoordinate();

        if (bulletPosition.distanceTo(target) <= this.speed) {
            bulletPosition.setCoordinate(target);
            this.hit = true;
        }
        else {
            double fraction = (double)
                    ((bulletPosition.getY() - target.getY())) /
                    (bulletPosition.getX() - target.getX());
            double angle = Math.atan(fraction);
//            System.out.println(angle);
            if (bulletPosition.getX() <= target.getX())
                angle += Math.PI;
            int newX = (int) Math.round(bulletPosition.getX() - this.speed * Math.cos(angle));
            int newY = (int) Math.round(bulletPosition.getY() - this.speed * Math.sin(angle));
            this.texture.getTopLeft().setCoordinate(newX, newY);
        }
    }

    public void hit() {
        enemy.setCurrentHealth(enemy.getCurrentHealth() - this.damage);
    }

    public void render() {
        this.texture.bind();
        this.texture.display();
    }

    public Enemy getTarget() {
        return enemy;
    }

    public Point getTargetCoordinate() {
        return target;
    }

    public myTexture getTexture() {
        return texture;
    }

    public boolean isHit() {
        return hit;
    }

    public int getSpeed() {
        return speed;
    }
}
