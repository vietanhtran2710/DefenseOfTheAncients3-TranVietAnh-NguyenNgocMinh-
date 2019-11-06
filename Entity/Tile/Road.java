package Entity.Tile;

import Utils.myTexture;

import static org.lwjgl.opengl.GL11.*;

public class Road implements GameTile {
    private int x;
    private int y;
    private String imageSource;
    private myTexture texture;

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
            case 0:
                imageSource = "src/res/GFX/Game/Tilemap/Spawner/EnemyHouse.png";
            case 11:
                imageSource = "src/res/GFX/Game/Tilemap/Target/Target.png";
        }
        this.texture = new myTexture(this.imageSource, GL_QUADS);
    }

    public void render() {
        this.texture.bind();
        this.texture.displayByIntCoordinate(this.x, this.y);
    }
}
