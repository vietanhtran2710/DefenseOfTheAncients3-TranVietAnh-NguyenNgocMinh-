package Utils;

public class myText {
    private String text;
    private double scale;

    public myText(String text, double scale) {
        this.text = text;
        this.scale = scale;
    }

    public void render(int x, int y) {
        int extraSpace = 0;
        int tmp_y = y;

        for (int i = 0; i < this.text.length(); i++) {
            if (text.charAt(i) != '\n') {
                myCharacter c = new myCharacter();
                c.render(text.charAt(i), this.scale, x + extraSpace, tmp_y, CharacterWidth.getWidth(text.charAt(i)));
                extraSpace += (int) (CharacterWidth.getWidth(text.charAt(i)) * this.scale);
            }
            else {
                extraSpace = 0;
                tmp_y += 62;
            }
        }
    }
}