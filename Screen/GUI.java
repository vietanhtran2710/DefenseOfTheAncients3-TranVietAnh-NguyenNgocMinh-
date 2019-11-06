package Screen;

import Utils.*;
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
        Utils.Point topLeft = new Utils.Point(-1.0f, 1.0f); Utils.Point topRight = new Utils.Point(1.0f, 1.0f);
        Utils.Point bottomLeft = new Utils.Point(1.0f, -1.0f); Utils.Point bottomRight = new Utils.Point(-1.0f, -1.0f);
        this.background = new Utils.myTexture(backgroundImageSource, GL_QUADS, topLeft, topRight, bottomLeft, bottomRight);

        // Button
        topLeft = new Utils.Point(-0.25f, 0.1f); topRight = new Utils.Point(0.25f, 0.1f);
        bottomLeft = new Utils.Point(0.25f, -0.1f); bottomRight = new Utils.Point(-0.25f, -0.1f);
        this.testButton = new Utils.myTexture(buttonImageSource, GL_POLYGON, topLeft, topRight, bottomLeft, bottomRight);

//        System.out.println(testButton.getTopLeftCoordinate().getX() + " " + testButton.getTopLeftCoordinate().getY());
//        System.out.println(testButton.getBottomRightCoordinate().getX() + " " + testButton.getBottomRightCoordinate().getY());
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

    public boolean checkMouseHover() {
        double posX = getCursorPosX(this.window);
        double posY = getCursorPosY(this.window);
        Utils.Point topLeft = testButton.getTopLeftCoordinate();
        Utils.Point bottomRight = testButton.getBottomRightCoordinate();
        if ((posX >= topLeft.getX()) && (posX <= bottomRight.getX()))
            if ((posY >= topLeft.getY()) && (posY <= bottomRight.getY()))
                return true;
        return false;
    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        background.bind();
        background.display();

        testButton.bind();
        testButton.display();

        if (glfwGetMouseButton(this.window, GLFW_MOUSE_BUTTON_LEFT) == GLFW_TRUE)
            if (onMouseHover)
                this.isPressed = true;

        if (checkMouseHover()) {
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

        //System.out.println(getCursorPosX(this.window) + " " + getCursorPosY(this.window));

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }


}
