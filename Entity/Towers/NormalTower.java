package Entity.Towers;

import Utils.Vertex;
import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class NormalTower extends Tower {
    public NormalTower(int x, int y) {
        super();
        this.texture = new myTexture(
                "src/res/GFX/Game/Tower/Normal Tower/NormalTower.png",
                GL_QUADS, x, y);
        this.texture.setDisplayHeight(48);
        this.texture.setDisplayWidth(48);
    }

}
