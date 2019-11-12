package Entity.Enemies;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class SmallerEnemy extends Enemy{
    public SmallerEnemy(int x, int y) {
        super();
        this.hitPoint = 10;
        this.amor = 1;
        this.damage = 10;
        this.speed = 3;
        this.bounty = 10;
        this.costumes = 4;
        this.texture = new myTexture(
                "src/res/GFX/Game/Enemy/Smaller Enemy/SmallerEnemy_Walk.png",
                GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);
    }
}
