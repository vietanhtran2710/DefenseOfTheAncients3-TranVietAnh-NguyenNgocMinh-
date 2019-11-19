package Entity.Towers;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class MachineGunTower extends Tower {
    public MachineGunTower(int x, int y) {
        super();
        this.texture = new myTexture(
                "src/res/GFX/Game/Tower/Machine Gun Tower/MachineGunTower.png",
                GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);

        this.bulletPrototype = new Bullet(
                "src/res/GFX/Game/Tower/Machine Gun Tower/MachineGunBullet.png",
                10, 100, x, y
        );

        this.shootCooldown = 0;
        this.cooldownTime = 10;
        this.range = 200;
    }
}
