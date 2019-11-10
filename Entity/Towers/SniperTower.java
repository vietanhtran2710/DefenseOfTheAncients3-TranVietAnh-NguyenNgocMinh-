package Entity.Towers;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class SniperTower extends Tower {
    public SniperTower(int x, int y) {
        super();
        this.texture = new myTexture(
                "src/res/GFX/Game/Tower/Sniper Tower/SniperTower.png",
                GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);
    }

}
