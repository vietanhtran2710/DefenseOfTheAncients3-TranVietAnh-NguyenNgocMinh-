package Utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class myText {
    private String text;;
    private Map<Character, Integer> width;
    private double scale;

    public myText(String text, double scale) {
        this.text = text;
        this.scale = scale;
        init();
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

    public void render(int x, int y) {
        int extraSpace = 0;
        for (int i = 0; i < this.text.length(); i++) {
            myCharacter c = new myCharacter();
            c.render(text.charAt(i), this.scale, x + extraSpace, y);
            extraSpace += (int)(this.width.get(text.charAt(i)) * this.scale);
        }
    }
}