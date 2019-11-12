package Entity.Enemies;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class BossEnemy extends Enemy{
<<<<<<< HEAD
    public BossEnemy(int initDirection, int x, int y) {
        super(initDirection);
        this.health = 100;
        this.movementSpeed = 8;
        this.animationPartLength = 4;
        this.animationPart = 1;
        this.texture = new myTexture("src/res/GFX/Game/Enemy/Boss Enemy/BossEnemy_Walk.png", GL_QUADS, x, y);
        this.texture.setDisplayWidth(48);
        this.texture.setDisplayHeight(48);
=======
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
>>>>>>> 11980fe4d18fd81c7a05c34d7a1696f0b5dc42cc
    }
}
