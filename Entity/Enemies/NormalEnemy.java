package Entity.Enemies;

import Utils.myTexture;
import static org.lwjgl.opengl.GL11.GL_QUADS;

public class NormalEnemy extends Enemy{
    public NormalEnemy(int initDirection ,int x, int y) {
        super(initDirection, x, y);
        this.health = 200;
        this.currentHealth = 200;
        this.damage = 100;
        this.movementSpeed = 2;
        this.bounty = 5;
        this.amor = 1;
        this.animationPartLength = 6;
        this.animationPart = 1;
        this.texture = new myTexture(
                "src/res/GFX/Game/Enemy/Normal Enemy/NormalEnemy_Walk.png",
                GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);
    }
}
