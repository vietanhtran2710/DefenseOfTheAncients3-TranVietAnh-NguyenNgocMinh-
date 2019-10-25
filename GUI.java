import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.openvr.Texture;
import org.lwjgl.system.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GUI {
    private myTexture background;
    private myTexture testButton;
    private long window;
    public  boolean isPressed;

    public static double getCursorPosX(long windowID) {
        DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(windowID, posX, null);
        return posX.get(0);
    }


    public void initLoop () {
        // Background
        this.background = new myTexture("src/res/GFX/Background/Background_main_screen.jpg");

        // Button
        this.testButton = new myTexture("src/res/GFX/Button/button.png");
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

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        background.bind();

        glBegin(GL_QUADS);
        {
            // Top left
            glTexCoord2f(0, 0);
            glVertex2f(-1.5f, 1.5f);

            // Top right
            glTexCoord2f(1, 0);
            glVertex2f(1.5f, 1.5f);

            // Bottom left
            glTexCoord2f(1, 1);
            glVertex2f(1.5f, -1.5f);

            // Bottom right
            glTexCoord2f(0, 1);
            glVertex2f(-1.5f, -1.5f);
        }
        glEnd();

        testButton.bind();

        glBegin(GL_POLYGON);
        {
            // Top left
            glTexCoord2f(0, 0);
            glVertex2f(-0.25f, 0.1f);

            // Top right
            glTexCoord2f(1f, 0);
            glVertex2f(0.25f, 0.1f);

            // Bottom right
            glTexCoord2f(1f, 1f);
            glVertex2f(0.25f, -0.1f);

            // Bottom left
            glTexCoord2f(0, 1f);
            glVertex2f(-0.25f, -0.1f);
        }
        glEnd();

        if(glfwGetKey(this.window, GLFW_KEY_SPACE)== GLFW_TRUE) this.isPressed = true;

        System.out.println(getCursorPosX(this.window));

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }


}
