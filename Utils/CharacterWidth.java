package Utils;

import java.util.HashMap;
import java.util.Map;

public class CharacterWidth {
    private static Map<Character, Integer> width;
    
    public CharacterWidth() {
        width = new HashMap<Character, Integer>();
        width.put(' ', 40);
        width.put('a', 41);
        width.put('b', 39);
        width.put('c', 39);
        width.put('d', 39);
        width.put('e', 41);
        width.put('f', 28);
        width.put('g', 39);
        width.put('h', 37);
        width.put('i', 12);
        width.put('j', 21);
        width.put('k', 37);
        width.put('l', 12);
        width.put('m', 57);
        width.put('n', 37);
        width.put('o', 41);
        width.put('p', 39);
        width.put('q', 38);
        width.put('r', 27);
        width.put('s', 37);
        width.put('t', 25);
        width.put('u', 36);
        width.put('v', 41);
        width.put('w', 58);
        width.put('x', 42);
        width.put('y', 41);
        width.put('z', 39);

        width.put('A', 54);
        width.put('B', 44);
        width.put('C', 50);
        width.put('D', 49);
        width.put('E', 44);
        width.put('F', 40);
        width.put('G', 52);
        width.put('H', 46);
        width.put('I', 12);
        width.put('J', 33);
        width.put('K', 48);
        width.put('L', 37);
        width.put('M', 54);
        width.put('N', 46);
        width.put('O', 54);
        width.put('P', 45);
        width.put('Q', 53);
        width.put('R', 51);
        width.put('S', 45);
        width.put('T', 46);
        width.put('U', 45);
        width.put('V', 53);
        width.put('W', 72);
        width.put('X', 53);
        width.put('Y', 53);
        width.put('Z', 46);

        width.put('0', 40);
        width.put('1', 25);
        width.put('2', 40);
        width.put('3', 40);
        width.put('4', 42);
        width.put('5', 41);
        width.put('6', 40);
        width.put('7', 40);
        width.put('8', 40);
        width.put('9', 40);
    }
    
    public static int getWidth (char c) {
        return width.get(c);
    }
}
