package Screen;

import Utils.myTexture;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

public class Screen {
    Screen() {

    }

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

    public boolean checkMouseHover(myTexture texture, long window) {
        double posX = getCursorPosX(window);
        double posY = getCursorPosY(window);
        if ((posX >= texture.getTopLeft().getX()) && (posX <= texture.getBottomRight().getX()))
            if ((posY >= texture.getTopLeft().getY()) && (posY <= texture.getBottomRight().getY()))
                return true;
        return false;
    }
}
