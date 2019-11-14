package Entity.Enemies;

import Utils.Vertex;
import Utils.myTexture;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class HealthBar {
    private myTexture green;
    private myTexture red;
    private myTexture emptyBar;


    public HealthBar() {
    }

    public void render(String color, int health, int currentHealth, int x, int y) {
        red = new myTexture("src/res/GFX/Game/Enemy/HealthBar/HealthBar_red.png", GL_QUADS, x, y);
        green = new myTexture("src/res/GFX/Game/Enemy/HealthBar/HealthBar_green.png", GL_QUADS, x, y);
        emptyBar = new myTexture("src/res/GFX/Game/Enemy/HealthBar/HealthBar_empty.png", GL_QUADS, x, y);

        Vertex topLeft = new Vertex(0, 0);
        Vertex topRight = new Vertex(1, 0);
        Vertex bottomLeft = new Vertex(1, 1);
        Vertex bottomRight = new Vertex(0, 1);

        if (color.equals("red")) {
            this.red.setDisplayHeight(5);
            this.red.setDisplayWidth((int) (48.0 * ((double) currentHealth / (double) health)));

            red.bind();
            red.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
        }
        else if (color.equals("green")) {
            this.green.setDisplayHeight(5);
            this.green.setDisplayWidth((int) (48.0 * ((double) currentHealth / (double) health)));

            green.bind();
            green.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
        }

        this.emptyBar.bind();
        this.emptyBar.setDisplayHeight(7);
        this.emptyBar.setDisplayWidth(50);
        emptyBar.displayByPartitionVertex(topLeft, topRight, bottomLeft, bottomRight);
    }

}
