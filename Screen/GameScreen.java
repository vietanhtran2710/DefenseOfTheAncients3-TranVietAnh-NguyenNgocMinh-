package Screen;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.openvr.Texture;
import org.lwjgl.system.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.*;
import Utils.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GameScreen {
    private long window;
    private myTexture road;

    public void initLoop () {
        String roadImageSource = "src/res/GFX/Game/Tilemap/Road/Road_ngang.png";
        Utils.Point topLeft = new Utils.Point(0, 0); Utils.Point topRight = new Utils.Point(128, 0);
        Utils.Point bottomLeft = new Utils.Point(0, 128); Utils.Point bottomRight = new Utils.Point(128, 128);
        this.road = new Utils.myTexture(roadImageSource, GL_QUADS, topLeft, topRight, bottomLeft, bottomRight);
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

        road.bind();
        road.displayByIntCoordinate(100, 100);

        glfwSwapBuffers(window); // swap the c  olor buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }
}