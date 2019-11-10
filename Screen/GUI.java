package Screen;

import Utils.*;
import Utils.Point;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.openvr.Texture;
import org.lwjgl.system.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GUI {
    private myTexture background;
    private myTexture testButton;
    private myTexture road;
    private long window;
    public boolean isPressed;
    public boolean onMouseHover = false;

    public static double getCursorPosX(long windowID) {
        DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(windowID, posX, null);
        return posX.get(0);
    }

    public static double getCursorPosY(long windowID) {
        DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(windowID, null, posY);
        return posY.get(0);
    }

    public void initLoop () {
        String backgroundImageSource = "src/res/GFX/GUI/Background/Background_main_screen.jpg";
        String buttonImageSource = "src/res/GFX/GUI/Button/button.png";
        // Background
        this.background = new Utils.myTexture(backgroundImageSource, GL_QUADS);

        // Button
        this.testButton = new Utils.myTexture(buttonImageSource, GL_POLYGON, 513, 346);
        testButton.setDisplayHeight(768 / 10);
        testButton.setDisplayWidth(1366 / 4);
    }

    public void loop(long window) {
        this.window = window;
        glClearColor( 0.0f, 0.0f, 0.0f, 0.0f);

        // Init attributes before loop
        initLoop();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(this.window) && !this.isPressed) {
            render();
        }
    }

    public boolean checkMouseHover(myTexture texture) {
        double posX = getCursorPosX(this.window);
        double posY = getCursorPosY(this.window);
        if ((posX >= texture.getTopLeft().getX()) && (posX <= texture.getBottomRight().getX()))
            if ((posY >= texture.getTopLeft().getY()) && (posY <= texture.getBottomRight().getY()))
                return true;
        return false;
    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        background.bind();
        background.displayByVertex(
                new Vertex(-1.0f, 1.0f),
                new Vertex(1.0f, 1.0f),
                new Vertex(1.0f, -1.0f),
                new Vertex(-1.0f, -1.0f)
        );

        testButton.bind();
        testButton.displayByVertex(new Vertex(-0.25f, 0.1f),
                new Vertex(0.25f, 0.1f),
                new Vertex(0.25f, -0.1f),
                new Vertex(-0.25f, -0.1f)
        );

        if (glfwGetMouseButton(this.window, GLFW_MOUSE_BUTTON_LEFT) == GLFW_TRUE)
            if (onMouseHover)
                this.isPressed = true;

        if (checkMouseHover(testButton)) {
            if (!onMouseHover) {
                testButton.changeImage("src/res/GFX/GUI/Button/button-selected.png");
                onMouseHover = true;
            }
        }
        else
            if (onMouseHover) {
                testButton.changeImage("src/res/GFX/GUI/Button/button.png");
                onMouseHover = false;
            }

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }


}
