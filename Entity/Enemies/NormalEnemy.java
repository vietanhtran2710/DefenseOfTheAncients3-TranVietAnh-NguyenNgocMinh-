package Entity.Enemies;

import Utils.Vertex;
import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class NormalEnemy extends Enemy{
    public NormalEnemy(int x, int y) {
        super();
        this.hitPoint = 10;
        this.amor = 1;
        this.damage = 1;
        this.speed = 2;
        this.bounty = 10;
        this.costumes = 6;
        this.texture = new myTexture(
                "src/res/GFX/Game/Enemy/Normal Enemy/NormalEnemy_Walk.png",
                GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);
    }
}
