package Entity.Enemies;

import Utils.Vertex;
import Utils.myTexture;

public class Enemy {
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
    }
}
