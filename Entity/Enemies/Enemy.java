package Entity.Enemies;

import Utils.Vertex;
import Utils.myTexture;

public class Enemy {
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
    }
}
