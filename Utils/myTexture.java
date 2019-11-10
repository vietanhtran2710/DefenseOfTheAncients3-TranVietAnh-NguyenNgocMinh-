package Utils;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class myTexture {
    private int id;
    private int width, height;
    private int displayWidth, displayHeight;
    private int mode;
    private Point topLeft, topRight, bottomLeft, bottomRight;

    public myTexture(String texturePath, int mode) {
        this.mode = mode;
        this.topLeft = new Point(0, 0);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        ByteBuffer data = stbi_load(texturePath, width, height, comp, 4);

        id = glGenTextures();
        this.width = width.get();
        this.height = height.get();
        this.displayWidth = this.width;
        this.displayHeight = this.height;

        this.topRight = new Point(0, this.width);
        this.bottomLeft = new Point(this.height, 0);
        this.bottomRight = new Point(this.height, this.width);

        glBindTexture(GL_TEXTURE_2D, id);

        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
        stbi_image_free(data);
    }

    public myTexture(String texturePath, int mode, int topLeftX, int topLeftY) {
        this.mode = mode;
        this.topLeft = new Point(topLeftX, topLeftY);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        ByteBuffer data = stbi_load(texturePath, width, height, comp, 4);

        id = glGenTextures();
        this.width = width.get();
        this.height = height.get();
        this.displayWidth = this.width;
        this.displayHeight = this.height;

        this.topRight = new Point(topLeftX, this.width);
        this.bottomLeft = new Point(this.height, topLeftY);
        this.bottomRight = new Point(this.height, this.width);

        glBindTexture(GL_TEXTURE_2D, id);

        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
        stbi_image_free(data);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void display() {
        final int midX = 1366 / 2;
        final int midY = 768 / 2;

        float topLeftVertexX = (float)(this.topLeft.getX() - midX) / midX;
        float topLeftVertexY = (float)(midY - this.topLeft.getY()) / midY;

        float topRightVertexX = (float)(this.topLeft.getX() + this.width - midX) / midX;
        float topRightVertexY = (float)(midY - this.topLeft.getY()) / midY;

        float bottomLeftVertexX = (float)(this.topLeft.getX() - midX) / midX;
        float bottomLeftVertexY = (float)(midY - (topLeft.getY() + this.height)) / midY;

        float bottomRightVertexX = (float)(this.topLeft.getX() + this.width - midX) / midX;
        float bottomRightVertexY = (float)(midY - (this.topLeft.getY() + this.height)) / midY;

        glBegin(this.mode);
        {
            // Top left
            glTexCoord2f(0, 0);
            glVertex2f(topLeftVertexX, topLeftVertexY);

            // Top right
            glTexCoord2f(1, 0);
            glVertex2f(topRightVertexX, topRightVertexY);

            // Bottom left
            glTexCoord2f(1, 1);
            glVertex2f(bottomRightVertexX, bottomRightVertexY);

            // Bottom right
            glTexCoord2f(0, 1);
            glVertex2f(bottomLeftVertexX, bottomLeftVertexY);
        }
        glEnd();
    }

    public void displayByOtherCoordinate(int x, int y) {
        final int midX = 1366 / 2;
        final int midY = 768 / 2;

        float topLeftVertexX = (float)(x - midX) / midX;
        float topLeftVertexY = (float)(midY - y) / midY;

        float topRightVertexX = (float)(x + this.width - midX) / midX;
        float topRightVertexY = (float)(midY - y) / midY;

        float bottomLeftVertexX = (float)(x - midX) / midX;
        float bottomLeftVertexY = (float)(midY - (y + this.height)) / midY;

        float bottomRightVertexX = (float)(x + this.width - midX) / midX;
        float bottomRightVertexY = (float)(midY - (y + this.height)) / midY;

        glBegin(this.mode);
        {
            // Top left
            glTexCoord2f(0, 0);
            glVertex2f(topLeftVertexX, topLeftVertexY);

            // Top right
            glTexCoord2f(1, 0);
            glVertex2f(topRightVertexX, topRightVertexY);

            // Bottom left
            glTexCoord2f(1, 1);
            glVertex2f(bottomRightVertexX, bottomRightVertexY);

            // Bottom right
            glTexCoord2f(0, 1);
            glVertex2f(bottomLeftVertexX, bottomLeftVertexY);
        }
        glEnd();
    }

    public void displayByVertex(Vertex topLeft, Vertex topRight, Vertex bottomLeft, Vertex bottomRight) {
        glBegin(this.mode);
        {
            // Top left
            glTexCoord2f(0, 0);
            glVertex2f(topLeft.getX(), topLeft.getY());

            // Top right
            glTexCoord2f(1, 0);
            glVertex2f(topRight.getX(), topRight.getY());

            // Bottom left
            glTexCoord2f(1, 1);
            glVertex2f(bottomLeft.getX(), bottomLeft.getY());

            // Bottom right
            glTexCoord2f(0, 1);
            glVertex2f(bottomRight.getX(), bottomRight.getY());
        }
        glEnd();
    }

    public void displayByPartitionVertex(
            Vertex topLeft, Vertex topRight, Vertex bottomLeft, Vertex bottomRight
    ) {
        final int midX = 1366 / 2;
        final int midY = 768 / 2;

        float topLeftVertexX = (float)(this.topLeft.getX() - midX) / midX;
        float topLeftVertexY = (float)(midY - this.topLeft.getY()) / midY;

        float topRightVertexX = (float)(this.topLeft.getX() + this.displayWidth - midX) / midX;
        float topRightVertexY = (float)(midY - this.topLeft.getY()) / midY;

        float bottomLeftVertexX = (float)(this.topLeft.getX() - midX) / midX;
        float bottomLeftVertexY = (float)(midY - (this.topLeft.getY() + this.displayHeight)) / midY;

        float bottomRightVertexX = (float)(this.topLeft.getX() + this.displayWidth - midX) / midX;
        float bottomRightVertexY = (float)(midY - (this.topLeft.getY() + this.displayHeight)) / midY;

        glBegin(this.mode);
        {
            // Top left
            glTexCoord2f(topLeft.getX(), topLeft.getY());
            glVertex2f(topLeftVertexX, topLeftVertexY);

            // Top right
            glTexCoord2f(topRight.getX(), topRight.getY());
            glVertex2f(topRightVertexX, topRightVertexY);

            // Bottom left
            glTexCoord2f(bottomLeft.getX(), bottomLeft.getY());
            glVertex2f(bottomRightVertexX, bottomRightVertexY);

            // Bottom right
            glTexCoord2f(bottomRight.getX(), bottomRight.getY());
            glVertex2f(bottomLeftVertexX, bottomLeftVertexY);
        }
        glEnd();
    }

    public void displayByOtherCoordinatePartitionVertex(
            int x, int y,
            Vertex topLeft, Vertex topRight, Vertex bottomLeft, Vertex bottomRight
    ) {
        final int midX = 1366 / 2;
        final int midY = 768 / 2;

        float topLeftVertexX = (float)(x - midX) / midX;
        float topLeftVertexY = (float)(midY - y) / midY;

        float topRightVertexX = (float)(x + this.displayWidth - midX) / midX;
        float topRightVertexY = (float)(midY - y) / midY;

        float bottomLeftVertexX = (float)(x - midX) / midX;
        float bottomLeftVertexY = (float)(midY - (y + this.displayHeight)) / midY;

        float bottomRightVertexX = (float)(x + this.displayWidth - midX) / midX;
        float bottomRightVertexY = (float)(midY - (y + this.displayHeight)) / midY;

        glBegin(this.mode);
        {
            // Top left
            glTexCoord2f(topLeft.getX(), topLeft.getY());
            glVertex2f(topLeftVertexX, topLeftVertexY);

            // Top right
            glTexCoord2f(topRight.getX(), topRight.getY());
            glVertex2f(topRightVertexX, topRightVertexY);

            // Bottom left
            glTexCoord2f(bottomLeft.getX(), bottomLeft.getY());
            glVertex2f(bottomRightVertexX, bottomRightVertexY);

            // Bottom right
            glTexCoord2f(bottomRight.getX(), bottomRight.getY());
            glVertex2f(bottomLeftVertexX, bottomLeftVertexY);
        }
        glEnd();
    }

    public void changeImage(String imagePath) {
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        ByteBuffer data = stbi_load(imagePath, width, height, comp, 4);

        id = glGenTextures();
        this.width = width.get();
        this.height = height.get();

        glBindTexture(GL_TEXTURE_2D, id);

        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
        stbi_image_free(data);
        this.bind();
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getTopRight() {
        return topRight;
    }

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
        this.bottomLeft.setY(this.topLeft.getY() + this.displayHeight);
        this.bottomRight.setY(this.topLeft.getY() + this.displayHeight);
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
        this.topRight.setX(this.topLeft.getX() + this.displayWidth);
        this.bottomRight.setX(this.topLeft.getX() + this.displayWidth);
    }
}
