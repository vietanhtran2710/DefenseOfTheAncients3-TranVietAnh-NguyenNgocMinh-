package Entity.Towers;

import Entity.Enemies.Enemy;
import Utils.Point;
import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class Bullet {
    private myTexture texture;
    private int damage;
    private Enemy target;
    private int speed;

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
        this.target = target;
        this.speed = prototype.speed;
    }

    public void move() {
        Point bulletPosition = this.texture.getTopLeft();
        Point enemyPosition = this.target.getTexture().getTopLeft();
        double fraction = (double)
                ((bulletPosition.getY() - enemyPosition.getY() + 24)) /
                (bulletPosition.getX() - enemyPosition.getX() + 24);
        double angle = Math.atan(fraction);
        System.out.println(angle);
        if (bulletPosition.getX() < enemyPosition.getX())
            angle += Math.PI;
        int newX = (int) Math.round(bulletPosition.getX() - this.speed * Math.cos(angle));
        int newY = (int) Math.round(bulletPosition.getY() - this.speed * Math.sin(angle));
        this.texture.getTopLeft().setCoordinate(newX, newY);
    }

    public void hit() {
        target.setCurrentHealth(target.getCurrentHealth() - this.damage);
    }

    public void render() {
        this.texture.bind();
        this.texture.display();
    }

    public Enemy getTarget() {
        return target;
    }

    public myTexture getTexture() {
        return texture;
    }

    public int getSpeed() {
        return speed;
    }
}
