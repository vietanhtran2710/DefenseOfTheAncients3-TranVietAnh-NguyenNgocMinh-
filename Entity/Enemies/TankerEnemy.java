package Entity.Enemies;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class TankerEnemy extends Enemy{
    public TankerEnemy(int initDirection, int x, int y) {
        super(initDirection);
//        this.hitPoint = 60;
//        this.amor = 6;
//        this.damage = 60;
//        this.speed = 1;
//        this.bounty = 60;
//        this.costumes = 6;
        this.texture = new myTexture(
                "src/res/GFX/Game/Enemy/Tanker Enemy/TankerEnemy_Walk.png",
                GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);
    }
}
