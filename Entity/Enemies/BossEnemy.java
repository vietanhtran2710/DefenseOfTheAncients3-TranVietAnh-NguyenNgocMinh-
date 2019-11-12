package Entity.Enemies;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class BossEnemy extends Enemy{
    public BossEnemy(int initDirection, int x, int y) {
        super(initDirection);
        this.health = 100;
        this.movementSpeed = 8;
        this.animationPartLength = 4;
        this.animationPart = 1;
        this.texture = new myTexture("src/res/GFX/Game/Enemy/Boss Enemy/BossEnemy_Walk.png", GL_QUADS, x, y);
        this.texture.setDisplayWidth(48);
        this.texture.setDisplayHeight(48);
    }
}
