package Entity.Enemies;

import Entity.GameEntity;
import Utils.Point;
import Utils.Vertex;
import Utils.myTexture;

public class Enemy implements GameEntity {
    protected int health;
    protected int currentHealth;
    protected int movementSpeed;
    protected int bounty;
    protected int damage;
    protected int amor;
    protected int animationPart;
    protected float animationPartLength;
    protected int direction;
    protected myTexture texture;
    protected HealthBar bar;

    protected int x;
    protected int y;

    public Enemy(int initDirection, int x, int y) {
        this.direction = initDirection;
        bar = new HealthBar("red");

        this.x = x;
        this.y = y;
    }

    public void render() {
        float range = (float) 1 / animationPartLength;
        texture.bind();
        Vertex topLeft = new Vertex((this.animationPart - 1) * range, 0);
        Vertex topRight = new Vertex(this.animationPart * range, 0);
        Vertex bottomLeft = new Vertex(this.animationPart * range, 1);
        Vertex bottomRight = new Vertex((this.animationPart - 1) * range, 1);
        texture.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);

        bar.render(this.health, this.currentHealth, this.x, this.y - 15);
    }

    public void updateAnimation() {
        this.animationPart++;
        if (this.animationPart > 4)
            this.animationPart = 1;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void move() {
        switch (direction) {
            case 0:
                this.texture.getTopLeft().setX(this.texture.getTopLeft().getX() - movementSpeed);
                this.x = this.x - movementSpeed;
                break;
            case 1:
                this.texture.getTopLeft().setY(this.texture.getTopLeft().getY() - movementSpeed);
                this.y = this.y - movementSpeed;
                break;
            case 2:
                this.texture.getTopLeft().setX(this.texture.getTopLeft().getX() + movementSpeed);
                this.x = this.x + movementSpeed;
                break;
            case 3:
                this.texture.getTopLeft().setY(this.texture.getTopLeft().getY() + movementSpeed);
                this.y = this.y + movementSpeed;
                break;
        }
    }

    public void setCurrentHealth(int health) {
        this.currentHealth = health;
    }

    public myTexture getTexture() {
        return texture;
    }

    public int getDirection() {
        return direction;
    }

    public int getX() { return x;}

    public int getY() {
        return y;
    }

    public Point getCoordinate() {
        return this.texture.getTopLeft();
    }

    public int getBounty() {
        return bounty;
    }

    public int getDamage() {
        return damage;
    }

    public int getAmor() {
        return amor;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
