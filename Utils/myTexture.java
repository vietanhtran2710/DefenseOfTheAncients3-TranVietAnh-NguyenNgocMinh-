package Utils;

import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class myTexture {
    private int id;
    private int width;
    private int height;
    private int mode;
    private Point topLeftVertex, topRightVertex, bottomLeftVertex, bottomRightVertex;
    private Point topLeftCoordinate, bottomRightCoordinate;

    public myTexture(String texturePath, int mode, Point topLeft, Point topRight, Point bottomLeft, Point bottomRight) {
        this.mode = mode;
        this.topLeftVertex = topLeft;
        this.topRightVertex = topRight;
        this.bottomLeftVertex = bottomLeft;
        this.bottomRightVertex = bottomRight;

        float midX = (float)(1366) / 2; float midY = (float)(768) / 2;
        this.topLeftCoordinate = new Point(midX + midX * topLeftVertex.getX(), midY - midY * topLeftVertex.getY());
        this.bottomRightCoordinate = new Point(midX + midX * bottomLeftVertex.getX(), midY - midY * bottomRightVertex.getY());

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        ByteBuffer data = stbi_load(texturePath, width, height, comp, 4);

        id = glGenTextures();
        this.width = width.get();
        this.height = height.get();

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
        glBegin(this.mode);
        {
            // Top left
            glTexCoord2f(0, 0);
            glVertex2f(this.topLeftVertex.getX(), this.topLeftVertex.getY());

            // Top right
            glTexCoord2f(1, 0);
            glVertex2f(this.topRightVertex.getX(), this.topRightVertex.getY());

            // Bottom left
            glTexCoord2f(1, 1);
            glVertex2f(this.bottomLeftVertex.getX(), this.bottomLeftVertex.getY());

            // Bottom right
            glTexCoord2f(0, 1);
            glVertex2f(this.bottomRightVertex.getX(), this.bottomRightVertex.getY());
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

    public Point getTopLeftCoordinate() {
        return topLeftCoordinate;
    }

    public Point getBottomRightCoordinate() {
        return bottomRightCoordinate;
    }
}
