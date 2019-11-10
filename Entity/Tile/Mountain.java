package Entity.Tile;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.*;

public class Mountain extends GameTile {
    private int x;
    private int y;
    private myTexture texture;

    public Mountain(int x, int y) {
        this.x = x;
        this.y = y;
        this.texture = new myTexture("src/res/GFX/Game/Tilemap/Road/mountain.png", GL_QUADS, this.x, this.y);
    }

    public void render() {
        this.texture.bind();
        this.texture.display();
    }

}
