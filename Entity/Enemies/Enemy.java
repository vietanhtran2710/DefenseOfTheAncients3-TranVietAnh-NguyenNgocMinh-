package Entity.Enemies;

import Utils.Vertex;
import Utils.myTexture;

public class Enemy {
<<<<<<< HEAD
    protected int health;
    protected int movementSpeed;
    protected int animationPart;
    protected float animationPartLength;
    protected int direction;
    protected myTexture texture;

    public Enemy(int initDirection) {
        this.direction = initDirection;
    }

    public void render() {
        float range = (float) 1 / animationPartLength;
        texture.bind();
        Vertex topLeft = new Vertex((this.animationPart - 1) * range, 0);
        Vertex topRight = new Vertex(this.animationPart * range, 0);
        Vertex bottomLeft = new Vertex(this.animationPart * range, 1);
        Vertex bottomRight = new Vertex((this.animationPart - 1) * range, 1);
        texture.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
    }

    public void updateAnimation() {
        this.animationPart++;
        if (this.animationPart > 4)
            this.animationPart = 1;
    }

    public int getHealth() {
        return health;
    }

    public void move() {
        switch (direction) {
            case 0:
                this.texture.getTopLeft().setX(this.texture.getTopLeft().getX() - movementSpeed);
                break;
            case 1:
                this.texture.getTopLeft().setY(this.texture.getTopLeft().getY() - movementSpeed);
                break;
            case 2:
                this.texture.getTopLeft().setX(this.texture.getTopLeft().getX() + movementSpeed);
                break;
            case 3:
                this.texture.getTopLeft().setY(this.texture.getTopLeft().getY() + movementSpeed);
                break;
        }
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public myTexture getTexture() {
        return texture;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
=======
    protected myTexture texture;
    protected int hitPoint;
    protected int amor;
    protected int damage;
    protected int speed;
    protected int bounty;

    protected int animate;
    protected int costumes;

    protected Vertex topLeft;
    protected Vertex topRight;
    protected Vertex bottomLeft;
    protected Vertex bottomRight;

    public Enemy() {
        this.hitPoint = 0;
        this.amor = 0;
        this.damage = 0;
        this.speed = 1;
        this.bounty = 0;
        this.animate = 1;
        this.costumes = 1;
    }

    public void render() {
        if (this.animate > this.costumes) this.animate = 1;
        texture.bind();
        Vertex topLeft = new Vertex((this.animate - 1) * 1f/this.costumes, 0);
        Vertex topRight = new Vertex(this.animate * 1f/this.costumes, 0);
        Vertex bottomLeft = new Vertex(this.animate * 1f/this.costumes, 1);
        Vertex bottomRight = new Vertex((this.animate - 1) * 1f/this.costumes, 1);
        texture.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
        this.animate++;
>>>>>>> 11980fe4d18fd81c7a05c34d7a1696f0b5dc42cc
    }
}
