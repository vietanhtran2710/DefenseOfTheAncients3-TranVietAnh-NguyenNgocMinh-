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

public class GUI extends Screen{
    private myTexture background;
    private myTexture StartButton;
    private myTexture LoadButton;
    private myTexture road;
    public boolean load = false;
    private long window;
    public boolean isPressed;
    public boolean onMouseStartHover = false;
    public Music backgroundMusic;
    public boolean onMouseLoadHover = false;

    public void initLoop () {
        String backgroundImageSource = "src/res/GFX/GUI/Background/Background_main_screen.jpg";
        String buttonImageSource = "src/res/GFX/GUI/Button/button.png";
        // Background
        this.background = new Utils.myTexture(backgroundImageSource, GL_QUADS);

        // Button
        this.StartButton = new Utils.myTexture(buttonImageSource, GL_POLYGON, 541, 343);
        this.LoadButton = new Utils.myTexture("src/res/GFX/GUI/Button/LoadButton.png", GL_POLYGON, 530, 500);
        StartButton.setDisplayHeight(110);
        StartButton.setDisplayWidth(300);

        LoadButton.setDisplayHeight(110);
        LoadButton.setDisplayWidth(300);

        backgroundMusic = new Music("src/res/SFX/Underground_Prep.ogg");

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
        while ( !glfwWindowShouldClose(this.window) && !this.isPressed) {
            render();
        }

        this.backgroundMusic.delete();
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

        StartButton.bind();
        StartButton.displayByVertex(
                new Vertex(-0.25f, 0.1f),
                new Vertex(0.25f, 0.1f),
                new Vertex(0.25f, -0.2f),
                new Vertex(-0.25f, -0.2f)
        );

        LoadButton.bind();
        LoadButton.display();

        if (glfwGetMouseButton(this.window, GLFW_MOUSE_BUTTON_LEFT) == GLFW_TRUE) {
            if (onMouseStartHover) {
                this.isPressed = true;
            }
            else if (onMouseLoadHover) {
                this.isPressed = true;
                this.load = true;
            }
        }

        if (checkMouseHover(StartButton, this.window)) {
            if (!onMouseStartHover) {
                StartButton.changeImage("src/res/GFX/GUI/Button/button-selected.png");
                onMouseStartHover = true;
            }
        }
        else
            if (onMouseStartHover) {
                StartButton.changeImage("src/res/GFX/GUI/Button/button.png");
                onMouseStartHover = false;
            }


        if (checkMouseHover(LoadButton, this.window)) {
            if (!onMouseLoadHover) {
                LoadButton.changeImage("src/res/GFX/GUI/Button/LoadButton_selected.png");
                onMouseLoadHover = true;
            }
        }
        else
        if (onMouseLoadHover) {
            LoadButton.changeImage("src/res/GFX/GUI/Button/LoadButton.png");
            onMouseLoadHover = false;
        }

        backgroundMusic.playFor(52, false);
        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }


}
