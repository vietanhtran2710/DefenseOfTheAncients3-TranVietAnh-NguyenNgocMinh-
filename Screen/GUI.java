package Screen;

import Utils.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GUI extends Screen{
    private myTexture background;
    private myTexture StartButton;
    private myTexture LoadButton;
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

        initLoop();
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

        glfwSwapBuffers(window);
        glfwPollEvents();
    }


}
