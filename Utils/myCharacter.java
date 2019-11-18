package Utils;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class myCharacter {
    private String filepath = "src/res/GFX/Font/";
    private int height = 58;
    private Map<Character, Integer> width;
    private myTexture charTexture;

    public myCharacter() {
    }

    public void render(char c, double scale, int x, int y, int width) {
        if (c == ' ') charTexture = new myTexture(this.filepath + "space.png", GL_QUADS, x, y);
        else
            if ((int)c <= 90 && (int)c >= 65) charTexture = new myTexture(this.filepath + c +"(1).png", GL_QUADS, x, y);
                else
                    charTexture = new myTexture(this.filepath + c +".png", GL_QUADS, x, y);

        charTexture.setDisplayHeight((int)(height * scale));
        charTexture.setDisplayWidth((int)(width * scale));

        Vertex topLeft = new Vertex(0, 0);
        Vertex topRight = new Vertex(1, 0);
        Vertex bottomLeft = new Vertex(1, 1);
        Vertex bottomRight = new Vertex(0, 1);

        charTexture.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
    }

}
