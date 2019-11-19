package Entity.Enemies;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class BossEnemy extends Enemy{
    public BossEnemy(int initDirection, int x, int y) {
        super(initDirection, x, y);
        this.health = 600;
        this.currentHealth = 600;
        this.movementSpeed = 2;
        this.damage = 600;
        this.bounty = 100;
        this.animationPartLength = 4;
        this.animationPart = 1;
        this.texture = new myTexture("src/res/GFX/Game/Enemy/Boss Enemy/BossEnemy_Walk.png", GL_QUADS, x, y);
        this.texture.setDisplayWidth(48);
        this.texture.setDisplayHeight(48);
    }
}
