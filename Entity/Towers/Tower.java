package Entity.Towers;

import Entity.Enemies.Enemy;
import Utils.Vertex;
import Utils.myTexture;
import Utils.Point;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    protected myTexture texture;
    protected int level;
    protected Bullet bulletPrototype;
    protected int range;
    protected List<Bullet> bulletList;
    protected Enemy target;
    protected int shootCooldown, cooldownTime;
    protected int damage;

    public Tower() {
        this.level = 1;
        this.bulletList = new ArrayList<>();
        this.target = null;
    }

    public void shoot() {
        this.bulletList.add(new Bullet(bulletPrototype, this.target));
    }

    public void render() {
        texture.bind();
        Vertex topLeft = new Vertex((this.level - 1) * 0.2f, 0);
        Vertex topRight = new Vertex(this.level * 0.2f, 0);
        Vertex bottomLeft = new Vertex(this.level * 0.2f, 1);
        Vertex bottomRight = new Vertex((this.level - 1) * 0.2f, 1);
        texture.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);

        for (int i = 0; i < bulletList.size(); i++)
            bulletList.get(i).render();
    }

    public int getLevel() {
        return level;
    }

    public Enemy getTarget() {
        return target;
    }

    public int getRange() {
        return range;
    }

    public int getShootCooldown() {
        return shootCooldown;
    }

    public int getDamage() {
        return damage;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setCooldown() {
        this.shootCooldown = this.cooldownTime;
    }

    public void decreaseCooldown() {
        this.shootCooldown--;
    }

    public void setLevel() {
        this.level++;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public Point getCoordinate() {
        return this.texture.getTopLeft();
    }

    public myTexture getTexture() {
        return texture;
    }
}
