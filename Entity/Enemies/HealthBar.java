package Entity.Enemies;

import Utils.Point;
import Utils.Vertex;
import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class HealthBar {
    private myTexture green;
    private myTexture red;
    private myTexture emptyBar;

    private String color;


    public HealthBar(String color) {
        this.color = color;
        red = new myTexture("src/res/GFX/Game/Enemy/HealthBar/HealthBar_red.png", GL_QUADS);
        green = new myTexture("src/res/GFX/Game/Enemy/HealthBar/HealthBar_green.png", GL_QUADS);
        emptyBar = new myTexture("src/res/GFX/Game/Enemy/HealthBar/HealthBar_empty.png", GL_QUADS);
    }

    public void render(int health, int currentHealth, int x, int y) {

        Point currentPos = new Point(x, y);

        Vertex topLeft = new Vertex(0, 0);
        Vertex topRight = new Vertex(1, 0);
        Vertex bottomLeft = new Vertex(1, 1);
        Vertex bottomRight = new Vertex(0, 1);

        if (this.color.equals("red")) {
            red.setTopLeft(currentPos);
            this.red.setDisplayHeight(5);
            this.red.setDisplayWidth((int) (48.0 * ((double) currentHealth / (double) health)));

            red.bind();
            red.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
        }
        else if (this.color.equals("green")) {
            green.setTopLeft(currentPos);
            this.green.setDisplayHeight(5);
            this.green.setDisplayWidth((int) (48.0 * ((double) currentHealth / (double) health)));

            green.bind();
            green.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
        }

        this.emptyBar.bind();
        emptyBar.setTopLeft(currentPos);
        this.emptyBar.setDisplayHeight(7);
        this.emptyBar.setDisplayWidth(50);
        emptyBar.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
    }

}
