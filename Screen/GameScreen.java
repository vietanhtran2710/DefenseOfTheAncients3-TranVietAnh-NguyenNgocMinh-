package Screen;

import Entity.GameField;
import Entity.GameStage;
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
    private myTexture background;
    private GameStage gameStage;
    private GameField field;

    public void initLoop () {
        String backgroundImageSource = "src/res/GFX/Game/Tilemap/Ground/Background.png";
        Utils.Point topLeft = new Utils.Point(-1.0f, 1.0f); Utils.Point topRight = new Utils.Point(1.0f, 1.0f);
        Utils.Point bottomLeft = new Utils.Point(1.0f, -1.0f); Utils.Point bottomRight = new Utils.Point(-1.0f, -1.0f);
        this.background = new Utils.myTexture(backgroundImageSource, GL_QUADS, topLeft, topRight, bottomLeft, bottomRight);
        this.gameStage = new GameStage("src/mapInfo.txt");
        this.field = new GameField(gameStage);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
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

//        road.bind();
//        road.displayByIntCoordinate(0, 0);
        background.bind();
        background.display();
        field.render();

        glfwSwapBuffers(window); // swap the c  olor buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }
}