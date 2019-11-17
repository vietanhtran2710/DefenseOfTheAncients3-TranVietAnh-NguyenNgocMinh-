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

    private void init () {
        this.width = new HashMap<Character, Integer>();
        this.width.put(' ', 40);
        this.width.put('a', 41);
        this.width.put('b', 39);
        this.width.put('c', 39);
        this.width.put('d', 39);
        this.width.put('e', 41);
        this.width.put('f', 28);
        this.width.put('g', 39);
        this.width.put('h', 37);
        this.width.put('i', 12);
        this.width.put('j', 21);
        this.width.put('k', 37);
        this.width.put('l', 12);
        this.width.put('m', 57);
        this.width.put('n', 37);
        this.width.put('o', 41);
        this.width.put('p', 39);
        this.width.put('q', 38);
        this.width.put('r', 27);
        this.width.put('s', 37);
        this.width.put('t', 25);
        this.width.put('u', 36);
        this.width.put('v', 41);
        this.width.put('w', 58);
        this.width.put('x', 42);
        this.width.put('y', 41);
        this.width.put('z', 39);
        this.width.put('0', 40);
        this.width.put('1', 25);
        this.width.put('2', 40);
        this.width.put('3', 40);
        this.width.put('4', 42);
        this.width.put('5', 41);
        this.width.put('6', 40);
        this.width.put('7', 40);
        this.width.put('8', 40);
        this.width.put('9', 40);
    }

    public void render(char c, double scale, int x, int y) {
        init();
        if (c == ' ') charTexture = new myTexture(this.filepath + "space.png", GL_QUADS, x, y);
        else charTexture = new myTexture(this.filepath + c +".png", GL_QUADS, x, y);

        charTexture.setDisplayHeight((int)(height * scale));
        charTexture.setDisplayWidth((int)(width.get(c) * scale));

        Vertex topLeft = new Vertex(0, 0);
        Vertex topRight = new Vertex(1, 0);
        Vertex bottomLeft = new Vertex(1, 1);
        Vertex bottomRight = new Vertex(0, 1);

        charTexture.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
    }

}