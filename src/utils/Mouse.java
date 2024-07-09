package utils;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Mouse extends GLFWCursorPosCallback {
    double xpos;

    public double getXpos() {
        return xpos;
    }

    public double getYpos() {
        return ypos;
    }

    double ypos;
    @Override
    public void invoke(long window, double xpos, double ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }
}
