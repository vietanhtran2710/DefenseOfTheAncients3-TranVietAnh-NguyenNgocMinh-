package Entity.Tile;

import Utils.Point;
import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class Target extends Road{

    public Target(int x, int y) {
        super(x, y);
        this.texture = new myTexture("src/res/GFX/Game/Tilemap/Target/Target.png", GL_QUADS, x, y);
    }

    public Point getCoordinate() {
        return texture.getTopLeft();
    }
}
