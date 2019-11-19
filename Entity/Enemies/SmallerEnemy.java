package Entity.Enemies;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class SmallerEnemy extends Enemy{
    public SmallerEnemy(int initDirection, int x, int y) {
        super(initDirection, x, y);
        this.health = 70;
        this.currentHealth = 70;
        this.damage = 50;
        this.movementSpeed = 4;
        this.bounty = 5;
        this.animationPartLength = 4;
        this.animationPart = 1;
        this.texture = new myTexture(
                "src/res/GFX/Game/Enemy/Smaller Enemy/SmallerEnemy_Walk.png",
                GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);
    }
}
