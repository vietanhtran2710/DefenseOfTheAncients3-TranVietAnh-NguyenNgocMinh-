package Entity.Enemies;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class TankerEnemy extends Enemy{
    public TankerEnemy(int initDirection, int x, int y) {
        super(initDirection, x, y);
        this.health = 700;
        this.currentHealth = 700;
        this.damage = 300;
        this.movementSpeed = 1;
        this.bounty = 20;
        this.amor = 2;
        this.animationPartLength = 6;
        this.animationPart = 1;
        this.texture = new myTexture(
                "src/res/GFX/Game/Enemy/Tanker Enemy/TankerEnemy_Walk.png",
                GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);
    }
}
