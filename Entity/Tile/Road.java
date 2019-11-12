package Entity.Tile;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.*;

public class Road extends GameTile {
    private int x;
    private int y;
    private String imageSource;

    public Road(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Road(int bitMask, int x, int y) {
        this.x = x;
        this.y = y;
        switch (bitMask) {
            case 10:
                imageSource = "src/res/GFX/Game/Tilemap/Road/Road_doc.png";
                break;
            case 5:
                imageSource = "src/res/GFX/Game/Tilemap/Road/Road_ngang.png";
                break;
            case 3:
                imageSource = "src/res/GFX/Game/Tilemap/Road/Road_turn12.png";
                break;
            case 9:
                imageSource = "src/res/GFX/Game/Tilemap/Road/Road_turn14.png";
                break;
            case 6:
                imageSource = "src/res/GFX/Game/Tilemap/Road/Road_turn23.png";
                break;
            case 12:
                imageSource = "src/res/GFX/Game/Tilemap/Road/Road_turn34.png";
                break;
        }
        this.texture = new myTexture(this.imageSource, GL_QUADS, this.x, this.y);
    }

    public void render() {
        this.texture.bind();
        this.texture.display();
    }
}
