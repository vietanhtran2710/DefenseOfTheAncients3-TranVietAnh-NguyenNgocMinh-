package Screen;

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

public class GameScreen {
    private long window;

    public void initLoop () {

    }

    public void loop(long window) {
        this.window = window;
        glClearColor( 0.0f, 0.0f, 0.0f, 0.0f);

        // Init attributes before loop
        initLoop();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(this.window)) {
            render();
        }
    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glBegin(GL_QUADS);
        {
            // Top left
            glVertex2f(-1.5f, 1.5f);

            // Top right
            glVertex2f(1.5f, 1.5f);

            // Bottom left
            glVertex2f(1.5f, -1.5f);

            // Bottom right
            glVertex2f(-1.5f, -1.5f);
        }
        glEnd();

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }


}