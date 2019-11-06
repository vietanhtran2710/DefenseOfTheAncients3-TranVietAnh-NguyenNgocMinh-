package Screen;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.openvr.Texture;
import org.lwjgl.system.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class ShowScreen {
    protected long window;

    public void run() throws FileNotFoundException {

        System.out.print("Game Start!");

        init();
        new GUI().loop(this.window);
        new GameScreen().loop(this.window);
        destroy();

    }

    public void init() {

        // Setup an error callback. The default implementation
        // will print the error messenger in System.err
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize game window
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure game window
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window won't be resizable

        // Create the game window
        window = glfwCreateWindow(1366, 768, "Defense of the Ancients 3", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the game window");

        // Setup a key callback.It will be called every time a key is pressed, repeated or released
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            }
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // The stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        // Enable Vsync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        //Make Texture be able to use
        GL.createCapabilities();

        // Enable for using texture 2D
        glEnable(GL_TEXTURE_2D);

    }

    public void destroy() {
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }
}
