package Entity.Tile;

import Entity.Enemies.Enemy;
import Entity.GameField;
import Utils.Vertex;
import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class Spawner extends Road{
    private int animationPart;

    public Spawner(int x, int y) {
        super(x, y);
        this.texture = new myTexture("src/res/GFX/Game/Tilemap/Spawner/EnemyHouse.png", GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);
        this.animationPart = 1;
    }

    @Override
    public void render() {
        texture.bind();
        Vertex topLeft = new Vertex((this.animationPart - 1) * 0.25f, 0);
        Vertex topRight = new Vertex(this.animationPart * 0.25f, 0);
        Vertex bottomLeft = new Vertex(this.animationPart * 0.25f, 1);
        Vertex bottomRight = new Vertex((this.animationPart - 1) * 0.25f, 1);
        texture.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
    }

    public int getAnimationPart() {
        return animationPart;
    }

    public void setAnimationPart(int animationPart) {
        this.animationPart = animationPart;
    }

    public void spawnEnemy(GameField field, Enemy enemy) {
        field.getEnemies().add(enemy);
    }

    public void updateAnimation() {
        this.animationPart++;
        if (this.animationPart > 4)
            this.animationPart = 1;
    }
}
