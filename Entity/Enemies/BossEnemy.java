package Entity.Enemies;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class BossEnemy extends Enemy{
    public BossEnemy(int x, int y) {
        super();
        this.hitPoint = 100;
        this.amor = 10;
        this.damage = 100;
        this.speed = 1;
        this.bounty = 100;
        this.costumes = 4;
        this.texture = new myTexture(
                "src/res/GFX/Game/Enemy/Boss Enemy/BossEnemy_Walk.png",
                GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);
    }
}
