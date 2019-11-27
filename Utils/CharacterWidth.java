package Utils;

import java.util.HashMap;
import java.util.Map;

public class CharacterWidth {
    private static Map<Character, Integer> width;
    
    public CharacterWidth() {
        width = new HashMap<>();

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
