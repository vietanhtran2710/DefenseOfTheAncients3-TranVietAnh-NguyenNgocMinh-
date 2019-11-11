package Entity.Towers;

import Utils.Vertex;
import Utils.myTexture;

public class Tower {
    protected myTexture texture;
    protected int level;
    protected Bullet bullet;
    protected int range;

    public Tower() {
        this.level = 1;
    }

    public void render() {
        texture.bind();
        Vertex topLeft = new Vertex((this.level - 1) * 0.2f, 0);
        Vertex topRight = new Vertex(this.level * 0.2f, 0);
        Vertex bottomLeft = new Vertex(this.level * 0.2f, 1);
        Vertex bottomRight = new Vertex((this.level - 1) * 0.2f, 1);
        texture.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
    }

    public myTexture getTexture() {
        return texture;
    }
}
